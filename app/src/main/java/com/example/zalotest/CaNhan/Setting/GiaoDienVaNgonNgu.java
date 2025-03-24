package com.example.zalotest.CaNhan.Setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.zalotest.CaNhan.CaiDat;
import com.example.zalotest.CaNhan.Setting.pGiaoDienVaNgonNgu.DoiCoChu;
import com.example.zalotest.R;

public class GiaoDienVaNgonNgu extends AppCompatActivity {
    ImageButton imgBack;
    ImageView imgSang, imgToi, imgHeThong;
    RadioButton rdoSang, rdoToi, rdoHeThong;
    ConstraintLayout ctDoiCoChu, ctNgonNgu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_giao_dien_va_ngon_ngu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        AnhXa();
        QuayLai();
        doiCoChu();
        ngonNgu();
    }

    private void ngonNgu() {
        ctNgonNgu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GiaoDienVaNgonNgu.this, "click", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void doiCoChu() {
        ctDoiCoChu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GiaoDienVaNgonNgu.this, DoiCoChu.class));
            }
        });
    }

    private void QuayLai() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(GiaoDienVaNgonNgu.this, CaiDat.class));
                finish();
            }
        });
    }

    private void AnhXa() {
        imgBack = findViewById(R.id.imgBack);
        imgSang = findViewById(R.id.imgSang);
        imgToi = findViewById(R.id.imgToi);
        imgHeThong = findViewById(R.id.imgHeThong);
        rdoSang = findViewById(R.id.rdoSang);
        rdoToi = findViewById(R.id.rdoToi);
        rdoHeThong = findViewById(R.id.rdoHeThong);
        ctDoiCoChu = findViewById(R.id.ctDoiCoChu);
        ctNgonNgu = findViewById(R.id.ctNgonNgu);
    }
}