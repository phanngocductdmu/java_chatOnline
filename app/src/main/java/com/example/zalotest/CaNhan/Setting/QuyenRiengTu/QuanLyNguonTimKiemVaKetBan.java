package com.example.zalotest.CaNhan.Setting.QuyenRiengTu;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.zalotest.R;

public class QuanLyNguonTimKiemVaKetBan extends AppCompatActivity {
    ImageButton imgBack;
    Switch swKetBan;
    CheckBox rdGoiY, rdMaQR, rdNhomChung, rdDanhThiep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quan_ly_nguon_tim_kiem_va_ket_ban);
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
        swKetBan = findViewById(R.id.swKetBan);
        rdMaQR = findViewById(R.id.rdMaQR);
        rdNhomChung = findViewById(R.id.rdNhomChung);
        rdDanhThiep = findViewById(R.id.rdDanhThiep);
        rdGoiY = findViewById(R.id.rdGoiY);
    }
}