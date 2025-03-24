package com.example.zalotest.CaNhan.Setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.zalotest.CaNhan.Setting.SaoLuu.CaiDatSaoLuu;
import com.example.zalotest.CaNhan.Setting.SaoLuu.ChiTietSaoLuu;
import com.example.zalotest.R;

public class SaoLuuVaKhoiPhuc extends AppCompatActivity {
    ImageView imgBack, imgMenu;
    TextView txtDienThoai, txtThoiGian, txtGmail;
    ConstraintLayout ctTaiKhoanGG, ctCaiDatSaoLuu, ctChiTietDuLieu;
    Switch swTuDongSaoLuu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sao_luu_va_khoi_phuc);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        AnhXa();
        QuayLai();
        menu();
        taiKhoanGG();
        caiDatSaoLuu();
        chiTietSaoLuu();
    }

    private void chiTietSaoLuu() {
        ctChiTietDuLieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SaoLuuVaKhoiPhuc.this, ChiTietSaoLuu.class));
            }
        });
    }

    private void caiDatSaoLuu() {
        ctCaiDatSaoLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SaoLuuVaKhoiPhuc.this, CaiDatSaoLuu.class));
            }
        });
    }

    private void taiKhoanGG() {
        ctTaiKhoanGG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SaoLuuVaKhoiPhuc.this, "Click gg", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void menu() {
        imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SaoLuuVaKhoiPhuc.this, "Click menu", Toast.LENGTH_SHORT).show();
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
        imgMenu = findViewById(R.id.imgMenu);
        txtDienThoai = findViewById(R.id.txtDienThoai);
        txtThoiGian = findViewById(R.id.txtThoiGian);
        txtGmail = findViewById(R.id.txtGmail);
        ctTaiKhoanGG = findViewById(R.id.ctTaiKhoanGG);
        ctCaiDatSaoLuu = findViewById(R.id.ctCaiDatSaoLuu);
        ctChiTietDuLieu = findViewById(R.id.ctChiTietDuLieu);
        swTuDongSaoLuu = findViewById(R.id.swTuDongSaoLuu);
    }
}