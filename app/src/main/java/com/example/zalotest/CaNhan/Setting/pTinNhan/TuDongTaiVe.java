package com.example.zalotest.CaNhan.Setting.pTinNhan;

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

import com.example.zalotest.CaNhan.DuLieuTrenMay;
import com.example.zalotest.R;

public class TuDongTaiVe extends AppCompatActivity {
    ImageButton imgBack;
    Switch swTuDongTaiVe;
    ConstraintLayout ctDonDepDuLieu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tu_dong_tai_ve);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        AnhXa();
        quayLai();
        donDepDuLieu();
    }

    private void donDepDuLieu() {
        ctDonDepDuLieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TuDongTaiVe.this, DuLieuTrenMay.class));
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
        swTuDongTaiVe = findViewById(R.id.swTuDongTaiVe);
        ctDonDepDuLieu = findViewById(R.id.ctDonDepDuLieu);
    }
}