package com.example.zalotest.CaNhan.Setting.QuyenRiengTu;

import android.os.Bundle;
import android.view.View;
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

public class TienIch extends AppCompatActivity {
    ImageButton imgBack;
    Switch sw1, sw2;
    ConstraintLayout ctMoBangZalo, ctMoBangWeb;
    ImageView imgTich1, imgTich2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tien_ich);
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
        sw1 = findViewById(R.id.sw1);
        sw2 = findViewById(R.id.sw2);
        ctMoBangZalo = findViewById(R.id.ctMoBangZalo);
        ctMoBangWeb = findViewById(R.id.ctMoBangWeb);
        imgTich1 = findViewById(R.id.imgTich1);
        imgTich2 = findViewById(R.id.imgTich2);
    }
}