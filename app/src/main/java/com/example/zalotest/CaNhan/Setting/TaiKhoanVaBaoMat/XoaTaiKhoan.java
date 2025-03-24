package com.example.zalotest.CaNhan.Setting.TaiKhoanVaBaoMat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.zalotest.CaNhan.Setting.TaiKhoanVaBaoMat.pXoaTaiKhoan.XoaTK;
import com.example.zalotest.R;

public class XoaTaiKhoan extends AppCompatActivity {
    ImageButton imgBack;
    Button btnXoaTaiKhoan, btnDoiSoDT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_xoa_tai_khoan);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        anhXa();
        quayLai();
        xoaTK();
        doiSoDT();
    }

    private void doiSoDT() {
        btnDoiSoDT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(XoaTaiKhoan.this, SoDienThoai.class));
            }
        });
    }

    private void xoaTK() {
        btnXoaTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(XoaTaiKhoan.this, XoaTK.class));
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

    private void anhXa() {
        imgBack = findViewById(R.id.imgBack);
        btnXoaTaiKhoan = findViewById(R.id.btnXoaTaiKhoan);
        btnDoiSoDT = findViewById(R.id.btnDoiSoDT);
    }
}