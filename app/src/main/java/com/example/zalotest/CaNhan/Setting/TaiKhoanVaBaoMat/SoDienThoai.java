package com.example.zalotest.CaNhan.Setting.TaiKhoanVaBaoMat;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.zalotest.CaNhan.Setting.TaiKhoanVaBaoMat.DoiSoDienThoai.DoiSoDienThoai;
import com.example.zalotest.R;

public class SoDienThoai extends AppCompatActivity {
    ImageButton imgBack;
    TextView txtSDTmoi, txtLuuY, txtBatDau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_so_dien_thoai);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        AnhXa();
        quayLai();
        batDauDoiSo();
        zsetText();
    }

    private void zsetText() {
        String text1 = "Số điện thoại mới sẽ gắn với thông tin, dữ liệu & nhật ký của tài khoản <b>Phan Ngọc Đức</b>.";
        txtSDTmoi.setText(Html.fromHtml(text1));

        String text2 = "<b>Lưu ý</b>: Một số điện thoại chỉ được phép gắn với một tài khoản Zalotest.";
        txtLuuY.setText(Html.fromHtml(text2));
    }

    private void batDauDoiSo() {
        txtBatDau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SoDienThoai.this, DoiSoDienThoai.class));
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
        txtSDTmoi = findViewById(R.id.txtSDTmoi);
        txtLuuY = findViewById(R.id.txtLuuY);
        txtBatDau = findViewById(R.id.txtBatDau);
    }
}