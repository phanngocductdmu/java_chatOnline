package com.example.zalotest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

public class DangNhapZalo extends AppCompatActivity {
    private ImageButton imgBack, imgNext;
    private ImageView imgXoaMatKhau;
    private EditText edtSoDienThoai, edtMatKhau;
    private TextView txtLayLaiMatKhau, txtCauHoiThuongGap, txtHienMatKhau;
    private FirebaseDatabase mdata = FirebaseDatabase.getInstance();
    private DatabaseReference acconutRef = mdata.getReference("Account");
    private static final String TAG = "Token";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dang_nhap_zalo);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        AnhXa();
        QuayLai();
        LayLaiMatKhau();
        TiepTuc();
        HienThiMatKhau();
        XoaMatKhau();
        CauHoiThuongGap();
        edtMatKhau.setText("1");
        edtSoDienThoai.setText("0123456789");
        FirebaseMessaging.getInstance().setAutoInitEnabled(true);
    }


    private void CauHoiThuongGap() {
        txtCauHoiThuongGap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Click cau hoi");
            }
        });
    }

    private boolean KiemTra(){
        String SoDienThoai = edtSoDienThoai.getText().toString().trim();
        String MatKhau = edtMatKhau.getText().toString().trim();
        if(SoDienThoai.isEmpty()){
            showToast("Vui lòng nhập số điện thoại");
            return false;
        }if(MatKhau.isEmpty()){
            showToast("Vui lòng nhập mật khẩu");
            return false;
        }
        return true;
    }
    private void showToast(String mess){
        Toast.makeText(DangNhapZalo.this, mess, Toast.LENGTH_SHORT).show();
    }

    private void XoaMatKhau() {
        imgXoaMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtSoDienThoai.setText("");
                edtMatKhau.setText("");
                edtSoDienThoai.findFocus();
            }
        });
    }

    private void HienThiMatKhau() {
        txtHienMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass = txtHienMatKhau.getText().toString().trim();
                if(pass.equals("Hiện")){
                    txtHienMatKhau.setText("Ẩn");
                    edtMatKhau.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }else {
                    txtHienMatKhau.setText("Hiện");
                    edtMatKhau.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                edtMatKhau.setSelection(edtMatKhau.length());
            }
        });
    }
    private void dangNhap() {
        if (edtSoDienThoai == null || edtMatKhau == null) {
            Log.e("DangNhapZalo", "EditText chưa được khởi tạo");
            return;
        }

        String SDT = edtSoDienThoai.getText().toString().trim();
        String MK = edtMatKhau.getText().toString().trim();

        acconutRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean loginSuccess = false;
                for (DataSnapshot account : snapshot.getChildren()){
                    String IdUser = account.getKey();
                    String phone = account.child("SoDienThoai").getValue(String.class);
                    String Pass = account.child("MatKhau").getValue(String.class);
                    if (SDT.equals(phone) && MK.equals(Pass)){
                        loginSuccess = true;
                        SharedPreferences sharedPreferences = getSharedPreferences("IdUser", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        if (!IdUser.isEmpty()){
                            editor.putString("IdUser", IdUser);
                            editor.apply();
                            startActivity(new Intent(DangNhapZalo.this, TrangChinhZalo.class));
                            finish();
                        }
                        break;
                    }
                }
                if (!loginSuccess) {
                    showToast("Đăng nhập không thành công");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("Firebase", "Error reading accounts", error.toException());
            }
        });
    }



    private void TiepTuc() {
        imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(KiemTra()){
                    dangNhap();
                }
            }
        });
    }

    private void LayLaiMatKhau(){
        txtLayLaiMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DangNhapZalo.this, LayLaiMatKhau.class));
            }
        });
    }

    private void QuayLai() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DangNhapZalo.this, MainActivity.class));
            }
        });
    }

    private void AnhXa() {
        imgBack = findViewById(R.id.imgBack);
        imgNext = findViewById(R.id.imgNext);
        edtSoDienThoai = findViewById(R.id.edtSoDienThoai);
        edtMatKhau = findViewById(R.id.edtMatKhau);
        txtLayLaiMatKhau = findViewById(R.id.txtLayLaiMatKhau);
        txtCauHoiThuongGap = findViewById(R.id.txtCauHoiThuongGap);
        txtHienMatKhau = findViewById(R.id.txtHien);
        imgXoaMatKhau = findViewById(R.id.imgXoaMatKhau);

        if (imgBack == null || imgNext == null || edtSoDienThoai == null || edtMatKhau == null ||
                txtLayLaiMatKhau == null || txtCauHoiThuongGap == null || txtHienMatKhau == null ||
                imgXoaMatKhau == null) {
            Log.e("DangNhapZalo", "Một số thành phần giao diện bị null");
        }
    }
}