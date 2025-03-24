package com.example.zalotest.CaNhan;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.zalotest.R;

public class CloudCuaToi extends AppCompatActivity {
    ImageButton imgBack;
    ImageView imgNhanDanCuaBan, imgGuiTinNhanCuaBan, imgChonMuneCuaBan, imgChonGhiAmCuaBan, imgChonHinhAnhCuaBan;
    ListView lstTinNhan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cloud_cua_toi);
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
        imgNhanDanCuaBan = findViewById(R.id.imgNhanDanCuaBan);
        imgGuiTinNhanCuaBan = findViewById(R.id.imgGuiTinNhanCuaBan);
        imgChonMuneCuaBan = findViewById(R.id.imgChonMuneCuaBan);
        imgChonGhiAmCuaBan = findViewById(R.id.imgChonGhiAmCuaBan);
        imgChonHinhAnhCuaBan = findViewById(R.id.imgChonHinhAnhCuaBan);
        lstTinNhan = findViewById(R.id.lstTinNhan);
    }
}