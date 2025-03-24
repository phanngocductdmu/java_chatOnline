package com.example.zalotest.CaNhan.Setting.TaiKhoanVaBaoMat;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.zalotest.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class MaQRCuaToi extends AppCompatActivity {
    ImageButton imgBack, imgMenu;
    CircleImageView imgAVT;
    TextView txtTenNguoiDung;
    ImageView imgQR, imgChiaSe, imgTaiXuong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ma_qrcua_toi);
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
        imgMenu = findViewById(R.id.imgMenu);
        imgAVT = findViewById(R.id.imgAVT);
        txtTenNguoiDung = findViewById(R.id.txtTenNguoiDung);
        imgQR = findViewById(R.id.imgQR);
        imgChiaSe = findViewById(R.id.imgChiaSe);
        imgTaiXuong = findViewById(R.id.imgTaiXuong);
    }
}