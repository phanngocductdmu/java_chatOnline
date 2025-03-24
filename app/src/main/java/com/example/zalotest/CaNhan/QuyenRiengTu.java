package com.example.zalotest.CaNhan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.zalotest.CaNhan.Setting.QuyenRiengTu.ChanVaAn;
import com.example.zalotest.CaNhan.Setting.QuyenRiengTu.ChoPhepXemVaBinhLuan;
import com.example.zalotest.CaNhan.Setting.QuyenRiengTu.QuanLyNguonTimKiemVaKetBan;
import com.example.zalotest.CaNhan.Setting.QuyenRiengTu.SinhNhat;
import com.example.zalotest.CaNhan.Setting.QuyenRiengTu.TienIch;
import com.example.zalotest.R;

public class QuyenRiengTu extends AppCompatActivity {
    ImageButton imgBack;
    ConstraintLayout ctSinhNhat, ctChoPhepXemVaBinhLuan, ctQuanLyNguonTimKiem, ctTienIch, ctChanVaAn;
    TextView txtTrangThaiTruyCap, txtTrangThaiDaXem, txtTrangThaiNhanTin, txtTrangThaiGoiDien;
    Switch swTuDongKetBanTuDanhBaMay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quyen_rieng_tu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        anhXa();
        quayLai();
        sinhNhat();
        choPhepXemVaBinhLuan();
        quanLyNguonTimKiem();
        tienIch();
        chanVaAn();
    }

    private void chanVaAn() {
        ctChanVaAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QuyenRiengTu.this, ChanVaAn.class));
            }
        });
    }

    private void tienIch() {
        ctTienIch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QuyenRiengTu.this, TienIch.class));
            }
        });
    }

    private void quanLyNguonTimKiem() {
        ctQuanLyNguonTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QuyenRiengTu.this, QuanLyNguonTimKiemVaKetBan.class));
            }
        });
    }

    private void choPhepXemVaBinhLuan() {
        ctChoPhepXemVaBinhLuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QuyenRiengTu.this, ChoPhepXemVaBinhLuan.class));
            }
        });
    }

    private void sinhNhat() {
        ctSinhNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QuyenRiengTu.this, SinhNhat.class));
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

    private void anhXa() {
        imgBack = findViewById(R.id.imgBack);
        ctSinhNhat = findViewById(R.id.ctSinhNhat);
        txtTrangThaiTruyCap = findViewById(R.id.txtTrangThaiTruyCap);
        txtTrangThaiDaXem = findViewById(R.id.txtTrangThaiDaXem);
        txtTrangThaiNhanTin = findViewById(R.id.txtTrangThaiNhanTin);
        txtTrangThaiGoiDien = findViewById(R.id.txtTrangThaiGoiDien);
        ctChoPhepXemVaBinhLuan = findViewById(R.id.ctChoPhepXemVaBinhLuan);
        swTuDongKetBanTuDanhBaMay = findViewById(R.id.swTuDongKetBanTuDanhBaMay);
        ctQuanLyNguonTimKiem = findViewById(R.id.ctQuanLyNguonTimKiem);
        ctTienIch = findViewById(R.id.ctTienIch);
        ctChanVaAn = findViewById(R.id.ctChanVaAn);
    }
}