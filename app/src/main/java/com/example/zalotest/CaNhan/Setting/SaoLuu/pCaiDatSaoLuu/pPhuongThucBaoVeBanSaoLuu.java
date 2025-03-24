package com.example.zalotest.CaNhan.Setting.SaoLuu.pCaiDatSaoLuu;

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

import com.example.zalotest.R;

public class pPhuongThucBaoVeBanSaoLuu extends AppCompatActivity {
    ImageButton imgBack;
    Switch swMKBanSaoLuu;
    ConstraintLayout ctDoiMK, ctQuenMK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pphuong_thuc_bao_ve_ban_sao_luu);
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
        swMKBanSaoLuu = findViewById(R.id.swMKBanSaoLuu);
        ctDoiMK = findViewById(R.id.ctDoiMK);
        ctQuenMK = findViewById(R.id.ctQuenMK);
    }
}