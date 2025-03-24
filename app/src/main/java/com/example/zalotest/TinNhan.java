package com.example.zalotest;

import static android.app.PendingIntent.getActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zalotest.chat.AdapterChat;
import com.example.zalotest.chat.GhiAmBottomSheet;
import com.example.zalotest.chat.StickerPickerBottomSheet;
import com.example.zalotest.chat.TinNhanBanBe;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;

public class TinNhan extends AppCompatActivity {
    private ImageButton imgbtnBack, imgbtnCall, imgbtnVideoCall, imgbtnMenu;
    private ImageView imgNhanDan, imgMenu, imgGhiAm, imgChonHinhAnh, imgGuiTinNhan;
    private TextView txtTenBanBe;
    private EditText edtTinNhan;
    private RecyclerView RecyclerView;
    private String IDFriends;
    private View rootView;
    private View lyaTinNhan;
    public String IDPhongChat;
    private int STT;
    private static final String ARG_DATA_TIN_NHAN = "dataTinNhan";
    private DanhSachBanBe dataTinNhan;
    private AdapterChat adapterChat;
    private FirebaseDatabase mdata = FirebaseDatabase.getInstance();
    private DatabaseReference Messenger = mdata.getReference("Messenger");
    private DatabaseReference Friends = mdata.getReference("Friends");
    private DatabaseReference inChats = mdata.getReference("inChats");
    List<TinNhanBanBe> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tin_nhan);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        AnhXa();

        Intent intent = getIntent();
        DanhSachBanBe bb = (DanhSachBanBe) intent.getSerializableExtra("dataTinNhan");

        if (bb != null) {
            IDPhongChat = bb.getIDPhongChat();
            IDFriends = bb.getIdTaiKhoanChinh();
            txtTenBanBe.setText(bb.getTenNguoiDung());
            STT = bb.getSTT();
        }

        QuayLai(IDPhongChat);
        RecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapterChat = new AdapterChat(TinNhan.this, list);
        RecyclerView.setAdapter(adapterChat);

        // Set up button click listeners
        GoiDienCall();
        GoiDienVideoCall();
        MenuChucNang();
        NhanDan();
        MenuTinNhan();
        GhiAm();
        ChonHinhAnh();
        GuiTinNhan();
        messenger();

    }

    private void messenger() {
        Messenger.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot da : snapshot.getChildren()){
                    String IDPhongChats = da.child("IDPhongChats").getValue(String.class);
                    String IDUsers = da.child("IDUser").getValue(String.class);
                    String Chats = da.child("Chats").getValue(String.class);
                    String Time = da.child("Times").getValue(String.class);
                    String TrangThai = da.child("TrangThai").getValue(String.class);
                    String TypeChats = da.child("TypeChats").getValue(String.class);
                    if(IDPhongChat.equals(IDPhongChats)){
                        list.add(new TinNhanBanBe(IDPhongChats, IDUsers, Chats, Time, TrangThai, TypeChats));
                        RecyclerView.setLayoutManager(new LinearLayoutManager(TinNhan.this));
                        adapterChat = new AdapterChat(TinNhan.this, list);
                        adapterChat.notifyDataSetChanged();
                        RecyclerView.setAdapter(adapterChat);
                        RecyclerView.scrollToPosition(list.size() - 1);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void showTinNhanDiaLog(){

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();

                SharedPreferences s = getSharedPreferences("IdUser", Context.MODE_PRIVATE);
                String IdUser = s.getString("IdUser", "");

                list.add(new TinNhanBanBe(IDPhongChat, IdUser, picturePath));
                adapterChat.notifyDataSetChanged();
            }
        }
    }

    private void GuiTinNhan() {
        SharedPreferences s = getSharedPreferences("IdUser", Context.MODE_PRIVATE);
        String IdUser = s.getString("IdUser", "");
        Intent intent = getIntent();
        DanhSachBanBe bb = (DanhSachBanBe) intent.getSerializableExtra("dataTinNhan");
        imgGuiTinNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String chat = edtTinNhan.getText().toString().trim();
                long time = System.currentTimeMillis();
                inChats.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String TrangThai = "Đã nhận";
                        for(DataSnapshot da : snapshot.getChildren()){
                            String idphong = da.child("IDPhongChat").getValue(String.class);
                            String User = da.child("IDUser").getValue(String.class);
                            if (IDPhongChat.equals(idphong) && bb.getIdTaiKhoanChinh().equals(User)){
                                TrangThai = "Đã xem";
                            }
                        }
                        if (!chat.equals("")) {
                            SendChat(IDPhongChat, IdUser, chat, String.valueOf(time), TrangThai);
                            edtTinNhan.setText("");
                            getMaxSTT();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }

    private void SapXepChat(int STT){
        Friends.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot da: snapshot.getChildren()){
                    String ID  = da.getKey();
                    if(ID != null && ID.equals(IDPhongChat)){
                        Map<String, Object> updates = new HashMap<>();
                        updates.put("STT", STT);
                        Friends.child(ID).updateChildren(updates);
                        break;
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void getMaxSTT() {
        SharedPreferences s = getSharedPreferences("IdUser", Context.MODE_PRIVATE);
        String IdUser = s.getString("IdUser", "");
        Friends.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int maxSTT = Integer.MIN_VALUE;
                for (DataSnapshot da : snapshot.getChildren()) {
                    Integer stt = da.child("STT").getValue(Integer.class);
                    String IDUser = da.child("IDUser").getValue(String.class);
                    String IDFriend = da.child("IDFriend").getValue(String.class);
                    if(IdUser.equals(IDUser) || IdUser.equals(IDFriend)){
                        if (stt != null && stt > maxSTT) {
                            maxSTT = stt;
                        }
                    }
                }
                int SLN = maxSTT+1;
                SapXepChat(SLN);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void SendChat(String IDPhongChats, String IDUser, String Chats, String Realtime, String TrangThai) {
        DatabaseReference newMessenger = Messenger.push();
        Map<String, Object> m = new HashMap<>();
        m.put("IDPhongChats", IDPhongChats);
        m.put("IDUser", IDUser);
        m.put("Chats", Chats);
        m.put("Times", Realtime);
        m.put("TrangThai", TrangThai);
        m.put("TypeChats", "tinNhan");
        newMessenger.setValue(m).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

            }
        });
    }

    private void ChonHinhAnh() {
        imgChonHinhAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1);
            }
        });
    }

    private void GhiAm() {
        imgGhiAm.setOnClickListener(v ->
        {
            Intent intent = getIntent();
            DanhSachBanBe bb = (DanhSachBanBe) intent.getSerializableExtra("dataTinNhan");

            GhiAmBottomSheet bottomSheet = GhiAmBottomSheet.newInstance(bb);
            bottomSheet.show(getSupportFragmentManager(), bottomSheet.getTag());
        });
    }

    private void MenuTinNhan() {
        imgMenu.setOnClickListener(v -> showToast("Click MenuTinNhan"));
    }

    private void NhanDan() {
        imgNhanDan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showStickerPicker();
            }
        });
    }

    private void showStickerPicker() {
        StickerPickerBottomSheet stickerPicker = new StickerPickerBottomSheet();
        stickerPicker.setOnStickerSelectedListener(new StickerPickerBottomSheet.OnStickerSelectedListener() {
            @Override
            public void onStickerSelected(int stickerResourceId) {
                String stickerName = stickerPicker.stickerNameMap.get(stickerResourceId);
                SendStick(stickerName);
            }
        });
        stickerPicker.show(getSupportFragmentManager(), "StickerPicker");
    }

    private void SendStick(String stickerName){
        SharedPreferences s = getSharedPreferences("IdUser", Context.MODE_PRIVATE);
        String IdUser = s.getString("IdUser", "");
        long time = System.currentTimeMillis();

        DatabaseReference newMessenger = Messenger.push();
        Map<String, Object> m = new HashMap<>();
        m.put("IDPhongChats", IDPhongChat);
        m.put("IDUser", IdUser);
        m.put("Chats", stickerName);
        m.put("Times", String.valueOf(time));
        m.put("TrangThai", "Đã xem");
        m.put("TypeChats", "Sticker");
        newMessenger.setValue(m).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

            }
        });
    }

    private void MenuChucNang() {
        imgbtnMenu.setOnClickListener(v -> showToast("Click MenuChucNang"));
    }

    private void GoiDienVideoCall() {
        imgbtnVideoCall.setOnClickListener(v -> showToast("Click VideoCall"));
    }

    private void GoiDienCall() {
        imgbtnCall.setOnClickListener(v -> showToast("Click Call"));
    }

    private void QuayLai(String idphong) {
        SharedPreferences s = getSharedPreferences("IdUser", Context.MODE_PRIVATE);
        String IdUser = s.getString("IdUser", "");
        imgbtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                inChats.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot da : snapshot.getChildren()){
                            String id = da.getKey();
                            String IDPhong = da.child("IDPhongChat").getValue(String.class);
                            String IDUser = da.child("IDUser").getValue(String.class);
                            if(idphong.equals(IDPhong) && IdUser.equals(IDUser)){
                                inChats.child(id).removeValue();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }


    private void AnhXa() {
        imgbtnBack = findViewById(R.id.imgBACK);
        imgbtnCall = findViewById(R.id.imgCALL);
        imgbtnVideoCall = findViewById(R.id.imgVIDEOCALL);
        imgbtnMenu = findViewById(R.id.imgMUNE);
        imgGuiTinNhan = findViewById(R.id.imgGuiTinNhanCuaBan);
        imgNhanDan = findViewById(R.id.imgNhanDanCuaBan);
        imgMenu = findViewById(R.id.imgChonMuneCuaBan);
        imgGhiAm = findViewById(R.id.imgChonGhiAmCuaBan);
        imgChonHinhAnh = findViewById(R.id.imgChonHinhAnhCuaBan);
        txtTenBanBe = findViewById(R.id.txtTENBANBE);
        edtTinNhan = findViewById(R.id.edtTinNhanCuaBan);
        RecyclerView = findViewById(R.id.RecyclerView);
    }

    public void showToast(String mess) {
        Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
    }
}
