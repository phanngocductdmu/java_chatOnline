package com.example.zalotest.CaNhan.Setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.zalotest.CaNhan.CaiDat;
import com.example.zalotest.CaNhan.Setting.QuyenRiengTu.QuanLyNguonTimKiemVaKetBan;
import com.example.zalotest.R;

public class DanhBa extends AppCompatActivity {
    ImageButton imgBack;
    ConstraintLayout ctCapNhatDanhBaZalotest, ctHienLienHeTrongDanhBa, ctQuanLyNguonTimKiemVaKetBan;
    Switch swTuDong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_danh_ba);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        AnhXa();
        QuayLai();
        capNhatDanhBaZalotest();
        hienThiLienHeTrongDanhBa();
        quanLyNguonTimKiemVaKetBan();
    }

    private void quanLyNguonTimKiemVaKetBan() {
        ctQuanLyNguonTimKiemVaKetBan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DanhBa.this, QuanLyNguonTimKiemVaKetBan.class));
            }
        });
    }

    private void hienThiLienHeTrongDanhBa() {
        ctHienLienHeTrongDanhBa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DanhBa.this, "click 2", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void capNhatDanhBaZalotest() {
        ctCapNhatDanhBaZalotest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DanhBa.this, "click 1", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void QuayLai() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DanhBa.this, CaiDat.class));
            }
        });
    }

    private void AnhXa() {
        imgBack = findViewById(R.id.imgBack);
        ctCapNhatDanhBaZalotest = findViewById(R.id.ctCapNhatDanhBaZalotest);
        ctHienLienHeTrongDanhBa = findViewById(R.id.ctHienLienHeTrongDanhBa);
        ctQuanLyNguonTimKiemVaKetBan = findViewById(R.id.ctQuanLyNguonTimKiemVaKetBan);
        swTuDong = findViewById(R.id.swTuDong);
    }
}