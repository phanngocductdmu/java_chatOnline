package com.example.zalotest.CaNhan.Setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.zalotest.CaNhan.Setting.QuyenRiengTu.pChanVaAn.pChanTinNhan;
import com.example.zalotest.CaNhan.Setting.pTinNhan.AnTroChuyen;
import com.example.zalotest.CaNhan.Setting.pTinNhan.PhanLoaiTroChuyen;
import com.example.zalotest.CaNhan.Setting.pTinNhan.QuanLyTNNhanh;
import com.example.zalotest.CaNhan.Setting.pTinNhan.TuDongTaiVe;
import com.example.zalotest.R;

public class sTinNhan extends AppCompatActivity {
    ImageButton imgBack;
    ConstraintLayout ctQuanLytinNhan, ctPhanLoaiTroChuyen, ctChanTinNhan, ctAnTroChuyen, ctTuDongTaiVe;
    Switch swGOiYAnhNhanh, swTicker, swGhiNhoChatLuongHinhAnh, swThaBieuTuongCamXuc, swPhatTNThoai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_stin_nhan);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        AnhXa();
        QuayLai();
        quanLyTinNhan();
        phanLoaiTroChuyen();
        chanTinNhan();
        anTroChuyen();
        tuDongTaiVe();
    }

    private void tuDongTaiVe() {
        ctTuDongTaiVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(sTinNhan.this, TuDongTaiVe.class));
            }
        });
    }

    private void anTroChuyen() {
        ctAnTroChuyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(sTinNhan.this, AnTroChuyen.class));
            }
        });
    }

    private void chanTinNhan() {
        ctChanTinNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(sTinNhan.this, pChanTinNhan.class));
            }
        });
    }

    private void phanLoaiTroChuyen() {
        ctPhanLoaiTroChuyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(sTinNhan.this, PhanLoaiTroChuyen.class));
            }
        });
    }

    private void quanLyTinNhan() {
        ctQuanLytinNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(sTinNhan.this, QuanLyTNNhanh.class));
            }
        });
    }

    private void QuayLai() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void AnhXa() {
        imgBack = findViewById(R.id.imgBack);
        ctQuanLytinNhan = findViewById(R.id.ctQuanLytinNhan);
        ctPhanLoaiTroChuyen = findViewById(R.id.ctPhanLoaiTroChuyen);
        ctChanTinNhan = findViewById(R.id.ctChanTinNhan);
        ctAnTroChuyen = findViewById(R.id.ctAnTroChuyen);
        ctTuDongTaiVe = findViewById(R.id.ctTuDongTaiVe);
        swGOiYAnhNhanh = findViewById(R.id.swGOiYAnhNhanh);
        swTicker = findViewById(R.id.swTicker);
        swGhiNhoChatLuongHinhAnh = findViewById(R.id.swGhiNhoChatLuongHinhAnh);
        swThaBieuTuongCamXuc = findViewById(R.id.swThaBieuTuongCamXuc);
        swPhatTNThoai = findViewById(R.id.swPhatTNThoai);
    }
}