package com.example.zalotest.CaNhan.Setting.SaoLuu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.zalotest.CaNhan.Setting.SaoLuu.pCaiDatSaoLuu.pPhuongThucBaoVeBanSaoLuu;
import com.example.zalotest.R;

public class CaiDatSaoLuu extends AppCompatActivity {
    ImageButton imgBack;
    ConstraintLayout ctDieuKienMang, ctPhuongThucBaoVeSaoLuu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cai_dat_sao_luu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        AnhXa();
        phuongThucBaoVeSaoLuu();
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

    private void phuongThucBaoVeSaoLuu() {
        ctPhuongThucBaoVeSaoLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CaiDatSaoLuu.this, pPhuongThucBaoVeBanSaoLuu.class));
            }
        });
    }

    private void AnhXa() {
        imgBack = findViewById(R.id.imgBack);
        ctDieuKienMang = findViewById(R.id.ctDieuKienMang);
        ctPhuongThucBaoVeSaoLuu = findViewById(R.id.ctPhuongThucBaoVeSaoLuu);
    }
}