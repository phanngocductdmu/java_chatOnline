package com.example.zalotest.CaNhan.Setting.QuyenRiengTu;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.zalotest.R;

public class ChoPhepXemVaBinhLuan extends AppCompatActivity {
    ImageButton imgBack;
    TextView txtToanBoBaiDang, txt6Thang, txt1Thang, txt7Thang;
    ConstraintLayout ctTuyChinh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cho_phep_xem_va_binh_luan);
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
        txtToanBoBaiDang = findViewById(R.id.txtToanBoBaiDang);
        txt6Thang = findViewById(R.id.txt6Thang);
        txt1Thang = findViewById(R.id.txt1Thang);
        txt7Thang = findViewById(R.id.txt7Thang);
        ctTuyChinh = findViewById(R.id.ctTuyChinh);
    }
}