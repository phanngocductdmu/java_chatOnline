package com.example.zalotest.CaNhan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.zalotest.DangNhapZalo;
import com.example.zalotest.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChuyenDoiTaiKhaon extends AppCompatActivity {
    ImageButton imgBack;
    CircleImageView imgAVT;
    TextView txtTenNguoiDung;
    Button btnDangNhap;
    ConstraintLayout layoutThemTaiKhoan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chuyen_doi_tai_khaon);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        AnhXa();
        quayLai();
        themTaiKhoan();
    }

    private void themTaiKhoan() {
        layoutThemTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChuyenDoiTaiKhaon.this, DangNhapZalo.class));
            }
        });
    }

    private void quayLai() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void AnhXa() {
        imgBack = findViewById(R.id.imgBack);
        imgAVT = (CircleImageView) findViewById(R.id.imgAVT);
        txtTenNguoiDung = findViewById(R.id.txtTenNguoiDung);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        layoutThemTaiKhoan = findViewById(R.id.layoutThemTaiKhoan);
    }
}