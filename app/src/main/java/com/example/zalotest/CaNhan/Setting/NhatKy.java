package com.example.zalotest.CaNhan.Setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.zalotest.CaNhan.Setting.QuyenRiengTu.pChanVaAn.pAnKhoiNhatKy;
import com.example.zalotest.CaNhan.Setting.QuyenRiengTu.pChanVaAn.pChanXemKhoanhKhac;
import com.example.zalotest.CaNhan.Setting.QuyenRiengTu.pChanVaAn.pChanXemNhatKy;
import com.example.zalotest.R;

public class NhatKy extends AppCompatActivity {
    ImageButton imgBack;
    ConstraintLayout ctPhanLoaiNhatKy, ctTuDongPhatVideo, ctTuDongPhatBaiHat, ctChanXemNhatKy, ctChanXemKhoanhKhac, ctAnKhoiNhatKy;
    TextView txtDangTat, txtLuonTuDongPhat, txtLuonTuDongPhatBaiHat;
    Switch swGoiYSticker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_nhat_ky);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        AnhXa();
        QuayLai();
        phanLoaiNhatKy();
        tuDongPhatVideo();
        tuDongPhatBaiHat();
        chanXemNhatKy();
        chanXemKhoanhKhac();
        anKhoiNhatKy();
    }

    private void anKhoiNhatKy() {
        ctAnKhoiNhatKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NhatKy.this, pAnKhoiNhatKy.class));
            }
        });
    }

    private void chanXemKhoanhKhac() {
        ctChanXemKhoanhKhac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NhatKy.this, pChanXemKhoanhKhac.class));
            }
        });
    }

    private void chanXemNhatKy() {
        ctChanXemNhatKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NhatKy.this, pChanXemNhatKy.class));
            }
        });
    }

    private void tuDongPhatBaiHat() {
        ctTuDongPhatBaiHat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NhatKy.this, "click bai hat", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void tuDongPhatVideo() {
        ctTuDongPhatVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NhatKy.this, "click tudongphatvodeo", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void phanLoaiNhatKy() {
        ctPhanLoaiNhatKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NhatKy.this, "click 1", Toast.LENGTH_SHORT).show();
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
        ctPhanLoaiNhatKy = findViewById(R.id.ctPhanLoaiNhatKy);
        ctTuDongPhatVideo = findViewById(R.id.ctTuDongPhatVideo);
        ctTuDongPhatBaiHat = findViewById(R.id.ctTuDongPhatBaiHat);
        ctChanXemNhatKy = findViewById(R.id.ctChanXemNhatKy);
        ctChanXemKhoanhKhac = findViewById(R.id.ctChanXemKhoanhKhac);
        ctAnKhoiNhatKy = findViewById(R.id.ctAnKhoiNhatKy);
        txtDangTat = findViewById(R.id.txtDangTat);
        txtLuonTuDongPhat = findViewById(R.id.txtLuonTuDongPhat);
        txtLuonTuDongPhatBaiHat = findViewById(R.id.txtLuonTuDongPhatBaiHat);
        swGoiYSticker = findViewById(R.id.swGoiYSticker);
    }
}