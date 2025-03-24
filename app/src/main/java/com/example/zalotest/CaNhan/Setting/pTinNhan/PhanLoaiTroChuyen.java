package com.example.zalotest.CaNhan.Setting.pTinNhan;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.zalotest.R;

public class PhanLoaiTroChuyen extends AppCompatActivity {
    ImageButton imgBack;
    ImageView imgPhanLoai;
    Switch swChiaMucUuTien, swSuDungBoLoc;
    ConstraintLayout ctHienThePhanLoai, ctQuanLyThePhanLoai;
    CheckBox cbHienThePhanLoai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_phan_loai_tro_chuyen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        AnhXa();
        quayLai();
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
        imgPhanLoai = findViewById(R.id.imgPhanLoai);
        swChiaMucUuTien = findViewById(R.id.swChiaMucUuTien);
        swSuDungBoLoc = findViewById(R.id.swSuDungBoLoc);
        ctHienThePhanLoai = findViewById(R.id.ctHienThePhanLoai);
        cbHienThePhanLoai = findViewById(R.id.cbHienThePhanLoai);
        ctQuanLyThePhanLoai = findViewById(R.id.ctQuanLyThePhanLoai);
    }
}