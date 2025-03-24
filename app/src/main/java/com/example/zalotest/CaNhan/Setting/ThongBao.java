package com.example.zalotest.CaNhan.Setting;

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

import com.example.zalotest.CaNhan.Setting.pThongBao.BaoTNNhom;
import com.example.zalotest.CaNhan.Setting.pThongBao.TatThongBaoCuocGoi;
import com.example.zalotest.R;

public class ThongBao extends AppCompatActivity {
    ImageButton imgBack;
    Switch swTroChuyen2Nguoi, swXemTinNhan2Nguoi, swCuocGoiDen, swSinhNhat, swPhatAmBaoTinNhan, swRung, swXem;
    TextView txtDangBat, txtNgoaiTru;
    ConstraintLayout ctBaoTinNhanMoiTuNhom, ctTatThongBaoCuocGoi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_thong_bao);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        AnhXa();
        QuayLai();
        tatThongBaoCuocGoi();
        baoTNNhom();
    }

    private void baoTNNhom() {
        ctBaoTinNhanMoiTuNhom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ThongBao.this, BaoTNNhom.class));
            }
        });
    }

    private void tatThongBaoCuocGoi() {
        ctTatThongBaoCuocGoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ThongBao.this, TatThongBaoCuocGoi.class));
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
        swTroChuyen2Nguoi = findViewById(R.id.swTroChuyen2Nguoi);
        swXemTinNhan2Nguoi = findViewById(R.id.swXemTinNhan2Nguoi);
        swCuocGoiDen = findViewById(R.id.swCuocGoiDen);
        swSinhNhat = findViewById(R.id.swSinhNhat);
        swPhatAmBaoTinNhan = findViewById(R.id.swPhatAmBaoTinNhan);
        swRung = findViewById(R.id.swRung);
        swXem = findViewById(R.id.swXem);
        txtDangBat = findViewById(R.id.txtDangBat);
        txtNgoaiTru = findViewById(R.id.txtNgoaiTru);
        ctBaoTinNhanMoiTuNhom = findViewById(R.id.ctBaoTinNhanMoiTuNhom);
        ctTatThongBaoCuocGoi = findViewById(R.id.ctTatThongBaoCuocGoi);
    }
}