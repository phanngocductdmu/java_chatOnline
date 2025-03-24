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
import com.example.zalotest.CaNhan.NhacChoZalotest;
import com.example.zalotest.CaNhan.Setting.QuyenRiengTu.pChanVaAn.pChanCuocGoi;
import com.example.zalotest.CaNhan.Setting.pCuocGoi.NhacChuong;
import com.example.zalotest.R;

public class CuocGoi extends AppCompatActivity {

    ImageButton imgBack;
    ConstraintLayout ctNhacChuong, ctNhacCho, ctChoPhepGoiDien, ctChanCuocGoi;
    Switch swThuNho, swHienLichSu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cuoc_goi);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        AnhXa();
        QuayLai();
        nhacChuong();
        nhacCho();
        choPhepGoiDien();
        chanCuocGoi();
    }

    private void chanCuocGoi() {
        ctChanCuocGoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CuocGoi.this, pChanCuocGoi.class));
            }
        });
    }

    private void choPhepGoiDien() {
        ctChoPhepGoiDien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CuocGoi.this, "click cho phep goi dien", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void nhacCho() {
        ctNhacCho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CuocGoi.this, NhacChoZalotest.class));
            }
        });
    }

    private void nhacChuong() {
        ctNhacChuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CuocGoi.this, NhacChuong.class));
            }
        });
    }

    private void QuayLai() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CuocGoi.this, CaiDat.class));
            }
        });
    }

    private void AnhXa() {
        imgBack = findViewById(R.id.imgBack);
        ctNhacChuong = findViewById(R.id.ctNhacChuong);
        ctNhacCho = findViewById(R.id.ctNhacCho);
        ctChoPhepGoiDien = findViewById(R.id.ctChoPhepGoiDien);
        ctChanCuocGoi = findViewById(R.id.ctChanCuocGoi);
        swThuNho = findViewById(R.id.swThuNho);
        swHienLichSu = findViewById(R.id.swHienLichSu);
    }
}