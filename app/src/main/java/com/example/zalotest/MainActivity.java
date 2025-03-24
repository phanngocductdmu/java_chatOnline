package com.example.zalotest;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.Manifest;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;


public class MainActivity extends AppCompatActivity {
    private Button btnChonNgonNgu, btnDangNhap, btnTaoTaiKhoan;
    private TextView txtTiengViet, txtTiengAnh;
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private static final int REQUEST_STORAGE_PERMISSION = 300;
    private static final int REQUEST_POST_NOTIFICATIONS_PERMISSION = 400;
    private int currentRequestCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        AnhXa();
        dangNhap();
        taoMoiTaiKhoan();
        chonNgonNgu();
        KiemTraUser();
        //requestRecordAudioPermission();
        getToken();
    }
    private void getToken() {
        FirebaseMessaging.getInstance().getToken().addOnSuccessListener(new OnSuccessListener<String>() {
            @Override
            public void onSuccess(String s) {
                saveTokenToDatabase(s);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    private void saveTokenToDatabase(String token) {
        Toast.makeText(this, token, Toast.LENGTH_SHORT).show();
    }

    private void requestRecordAudioPermission() {
        currentRequestCode = REQUEST_RECORD_AUDIO_PERMISSION;

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.RECORD_AUDIO},
                    REQUEST_RECORD_AUDIO_PERMISSION);
        } else {
            requestStoragePermissions();
        }
    }

    private void requestStoragePermissions() {
        currentRequestCode = REQUEST_STORAGE_PERMISSION;

        boolean readPermissionGranted = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED;
        boolean writePermissionGranted = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED;

        if (!readPermissionGranted || !writePermissionGranted) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_STORAGE_PERMISSION);
        } else {
            requestPostNotificationsPermission();
        }
    }

    private void requestPostNotificationsPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            currentRequestCode = REQUEST_POST_NOTIFICATIONS_PERMISSION;

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.POST_NOTIFICATIONS},
                        REQUEST_POST_NOTIFICATIONS_PERMISSION);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == currentRequestCode) {
            boolean allPermissionsGranted = true;
            switch (requestCode) {
                case REQUEST_RECORD_AUDIO_PERMISSION:
                    if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        requestStoragePermissions();
                    } else {
                        allPermissionsGranted = false;
                    }
                    break;

                case REQUEST_STORAGE_PERMISSION:
                    if (grantResults.length > 0 &&
                            grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                            grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                        requestPostNotificationsPermission();
                    } else {
                        allPermissionsGranted = false;
                    }
                    break;

                case REQUEST_POST_NOTIFICATIONS_PERMISSION:
                    if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    } else {
                        allPermissionsGranted = false;
                    }
                    break;
            }

            if (!allPermissionsGranted) {
                // Xử lý trường hợp quyền bị từ chối
                // Hiển thị thông báo cho người dùng nếu cần thiết
            }
        }
    }

    private void taoMoiTaiKhoan() {
        btnTaoTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TaoTaiKhoan.class));
            }
        });
    }

    private void dangNhap() {
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DangNhapZalo.class));
            }
        });
    }

    private void chonNgonNgu() {
        btnChonNgonNgu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.chon_ngon_ngu);
                TextView txtTiengViet = dialog.findViewById(R.id.txtTiengViet);
                TextView txtTiengAnh = dialog.findViewById(R.id.txtEnglish);
                dialog.show();
            }
        });
    }

    private void KiemTraUser(){
        // Kiểm tra trạng thái đăng nhập
        SharedPreferences sharedPreferences = getSharedPreferences("IdUser", Context.MODE_PRIVATE);
        String soDienThoai = sharedPreferences.getString("IdUser", null);
        if (soDienThoai != null) {
            // Người dùng đã đăng nhập, chuyển đến trang chính
            Intent intent = new Intent(this, TrangChinhZalo.class);
            startActivity(intent);
            finish();
        }
        KiemTraHDUngDUng kiemTraHDUngDUng = new KiemTraHDUngDUng();
        getLifecycle().addObserver(kiemTraHDUngDUng);
    }

    private void AnhXa() {
        btnChonNgonNgu = findViewById(R.id.btnChonNgonNgu);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        btnTaoTaiKhoan = findViewById(R.id.btnTaoTaiKhoan);
    }

}