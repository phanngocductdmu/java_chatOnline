package com.example.zalotest.chat;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.zalotest.DanhSachBanBe;
import com.example.zalotest.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GhiAmBottomSheet extends BottomSheetDialogFragment {
    private static final String ARG_DATA_TIN_NHAN = "dataTinNhan";
    private DanhSachBanBe dataTinNhan;
    private static final String LOG_TAG = "AudioRecordTest";
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private String fileName = null;
    private MediaRecorder recorder = null;
    private MediaPlayer player = null;
    private StorageReference newGhiAm;
    private Button buttonRecord;
    private Button buttonPlay;
    private Button buttonSend;
    private TextView textViewTimer;
    private Handler timerHandler;
    private Runnable timerRunnable;
    private long startTime = 0;
    private boolean permissionToRecordAccepted = false;
    private String[] permissions = {Manifest.permission.RECORD_AUDIO};
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int LENGTH = 10;
    private CountDownTimer countDownTimer;
    private long playbackDuration = 0;
    private FirebaseDatabase mdata = FirebaseDatabase.getInstance();
    private DatabaseReference Messenger = mdata.getReference("Messenger");

    public static GhiAmBottomSheet newInstance(DanhSachBanBe dataTinNhan) {
        GhiAmBottomSheet fragment = new GhiAmBottomSheet();
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATA_TIN_NHAN, dataTinNhan);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_ghi_am, container, false);

        buttonRecord = view.findViewById(R.id.buttonRecord);
        buttonPlay = view.findViewById(R.id.buttonPlay);
        buttonSend = view.findViewById(R.id.buttonSend);
        textViewTimer = view.findViewById(R.id.textViewTimer);

        ActivityCompat.requestPermissions(getActivity(), permissions, REQUEST_RECORD_AUDIO_PERMISSION);

        timerHandler = new Handler();
        timerRunnable = new Runnable() {
            @Override
            public void run() {
                long millis = System.currentTimeMillis() - startTime;
                int seconds = (int) (millis / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;

                textViewTimer.setText(String.format("%02d:%02d", minutes, seconds));

                timerHandler.postDelayed(this, 500);
            }
        };
        if (getArguments() != null) {
            dataTinNhan = (DanhSachBanBe) getArguments().getSerializable(ARG_DATA_TIN_NHAN);
            buttonSend.setOnClickListener(v -> uploadAudio(dataTinNhan.getIDPhongChat()));
        }
        ghiAm();
        LuuGhiAm();
        buttonPlay.setOnClickListener(v -> playAudio());
        return view;
    }

    private void LuuGhiAm() {
        String randomString = generateRandomString(LENGTH);
        fileName = getActivity().getExternalCacheDir().getAbsolutePath() + "/Zalotest" + randomString + ".3gpp";
        newGhiAm = FirebaseStorage.getInstance().getReference();
    }

    private void ghiAm() {
        buttonRecord.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(getActivity(), permissions, REQUEST_RECORD_AUDIO_PERMISSION);
                        } else {
                            startRecording();
                            startTime = System.currentTimeMillis();
                            timerHandler.postDelayed(timerRunnable, 0);
                        }
                        return true;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        stopRecording();
                        timerHandler.removeCallbacks(timerRunnable);
                        textViewTimer.setText("00:00");
                        buttonPlay.setVisibility(View.VISIBLE);
                        buttonSend.setVisibility(View.VISIBLE);
                        return true;
                }
                return false;
            }
        });
    }

    private void startRecording() {
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setOutputFile(fileName);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            recorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        recorder.start();
    }

    private void stopRecording() {
        if (recorder != null) {
            recorder.stop();
            recorder.release();
            recorder = null;
        }
    }

    private void playAudio() {
        player = new MediaPlayer();
        try {
            player.setDataSource(fileName);
            player.prepare();
            playbackDuration = player.getDuration();
            player.start();

            // Set up the countdown timer
            countDownTimer = new CountDownTimer(playbackDuration, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    long seconds = millisUntilFinished / 1000;
                    long minutes = seconds / 60;
                    seconds = seconds % 60;

                    textViewTimer.setText(String.format("%02d:%02d", minutes, seconds));
                }

                @Override
                public void onFinish() {
                    textViewTimer.setText("00:00");
                }
            }.start();

        } catch (IOException e) {
            Log.e(LOG_TAG, "play() failed");
        }
    }

    private void uploadAudio(String Phong) {
        SharedPreferences s = requireContext().getSharedPreferences("IdUser", Context.MODE_PRIVATE);
        String IdUser = s.getString("IdUser", "");

        Uri file = Uri.fromFile(new File(fileName));
        StorageReference audioRef = newGhiAm.child("audios/" + file.getLastPathSegment());
        UploadTask uploadTask = audioRef.putFile(file);

        long time = System.currentTimeMillis();
        uploadTask.addOnFailureListener(exception -> {
            Log.e(LOG_TAG, "Upload failed: " + exception.getMessage());
        }).addOnSuccessListener(taskSnapshot -> {
            audioRef.getDownloadUrl().addOnSuccessListener(uri -> {
                String downloadUrl = uri.toString();
                DatabaseReference newMessenger = Messenger.push();
                Map<String, Object> m = new HashMap<>();
                m.put("IDPhongChats", Phong);
                m.put("IDUser", IdUser);
                m.put("Chats", downloadUrl);
                m.put("Times", String.valueOf(time));
                m.put("TrangThai", "Đã xem");
                m.put("TypeChats", "ghiAm");
                newMessenger.setValue(m).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        dismiss();
                    }
                });
            }).addOnFailureListener(exception -> {
                Log.e(LOG_TAG, "Failed to get download URL: " + exception.getMessage());
            });
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_RECORD_AUDIO_PERMISSION:
                permissionToRecordAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                if (!permissionToRecordAccepted) {
                    dismiss();
                }
                break;
        }
    }

    public static String generateRandomString(int length) {
        StringBuilder result = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(CHARACTERS.length());
            result.append(CHARACTERS.charAt(index));
        }
        return result.toString();
    }
}
