package com.example.zalotest.CaNhan.Setting.TaiKhoanVaBaoMat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.zalotest.R;

public class MatKhau extends AppCompatActivity {
    ImageButton imgBack;
    EditText edtMkHienTai, edtNhapMKMoi, edtNhapLaiMKMoi;
    Button btnCapNhat;
    TextView txtHienMk;
    ImageView imgClear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mat_khau);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        anhXa();
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

    private void anhXa() {
        imgBack = findViewById(R.id.imgBack);
        edtMkHienTai = findViewById(R.id.edtMkHienTai);
        edtNhapMKMoi = findViewById(R.id.edtNhapMKMoi);
        edtNhapLaiMKMoi = findViewById(R.id.edtNhapLaiMKMoi);
        btnCapNhat = findViewById(R.id.btnCapNhat);
        txtHienMk = findViewById(R.id.txtHienMk);
        imgClear = findViewById(R.id.imgClear);
    }
}