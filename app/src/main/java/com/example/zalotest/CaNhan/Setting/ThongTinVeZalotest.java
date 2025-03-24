package com.example.zalotest.CaNhan.Setting;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.zalotest.R;

public class ThongTinVeZalotest extends AppCompatActivity {
    ImageButton imgBack;
    ConstraintLayout ctHuongDan, ctLienHeHoTro, ctGuiQoS, ctDieuKhoanSuDung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_thong_tin_ve_zalotest);
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
        ctHuongDan = findViewById(R.id.ctHuongDan);
        ctLienHeHoTro = findViewById(R.id.ctLienHeHoTro);
        ctGuiQoS = findViewById(R.id.ctGuiQoS);
        ctDieuKhoanSuDung = findViewById(R.id.ctDieuKhoanSuDung);
    }
}