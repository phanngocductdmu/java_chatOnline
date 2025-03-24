package com.example.zalotest.CaNhan.Setting.TaiKhoanVaBaoMat;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.zalotest.R;

public class KhoaZalotest extends AppCompatActivity {
    ImageButton imgBack;
    Switch swDatMatKhau;
    ConstraintLayout ctDoiMK;
    TextView txtDoiMK, txtTuDongKhoa, txtTruongHop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_khoa_zalotest);
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
        swDatMatKhau = findViewById(R.id.swDatMatKhau);
        ctDoiMK = findViewById(R.id.ctDoiMK);
        txtDoiMK = findViewById(R.id.txtDoiMK);
        txtTuDongKhoa = findViewById(R.id.txtTuDongKhoa);
        txtTruongHop = findViewById(R.id.txtTruongHop);
    }
}