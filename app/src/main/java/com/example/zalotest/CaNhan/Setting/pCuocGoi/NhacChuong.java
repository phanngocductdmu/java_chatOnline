package com.example.zalotest.CaNhan.Setting.pCuocGoi;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.zalotest.R;

public class NhacChuong extends AppCompatActivity {
    ImageButton imgBack;
    ConstraintLayout ctMacDinh, ctHeThong, ctNhacChuongKhac;
    CheckBox cbMacDinh, cbHeThong, cbNhacChuongKhac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_nhac_chuong);
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
        ctMacDinh = findViewById(R.id.ctMacDinh);
        ctHeThong = findViewById(R.id.ctHeThong);
        ctNhacChuongKhac = findViewById(R.id.ctNhacChuongKhac);
        cbMacDinh = findViewById(R.id.cbMacDinh);
        cbHeThong = findViewById(R.id.cbHeThong);
        cbNhacChuongKhac = findViewById(R.id.cbNhacChuongKhac);
    }
}