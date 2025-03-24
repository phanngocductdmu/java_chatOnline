package com.example.zalotest.MenuTrangChinh;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.zalotest.R;

public class ThemBan extends AppCompatActivity {
    ImageButton imgQuaylai, imgTiepTuc;
    TextView txtTen;
    ImageView imgQR;
    EditText edtNhap;
    LinearLayout lnQuet, lnDanhBaMay, lnBanBeCoTheQuanBiet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_them_ban);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        AnhXa();
        QuayLai();
        TiepTuc();
        QuetQr();
        DanhBaMay();
        BanBeCoTheQuen();
        MaQR();
    }
    private void MaQR() {
        String qrCodeBase64 = "";

        // Chuyển đổi chuỗi Base64 thành hình ảnh Bitmap
        Bitmap bitmap = decodeBase64(qrCodeBase64);
        imgQR.setImageBitmap(bitmap);
    }

    // Phương thức chuyển đổi Base64 thành hình ảnh Bitmap
    public static Bitmap decodeBase64(String input) {
        byte[] decodedBytes = android.util.Base64.decode(input, android.util.Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }

    private void BanBeCoTheQuen() {
        lnBanBeCoTheQuanBiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("click ban be co the quen");
            }
        });
    }

    private void DanhBaMay() {
        lnDanhBaMay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("click DanhBaMay");
            }
        });
    }

    private void QuetQr() {
        lnQuet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ThemBan.this, QuetQR.class));
            }
        });
    }

    private void TiepTuc() {
        imgTiepTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Click Tiep Tuc");
            }
        });
    }

    private void QuayLai() {
        imgQuaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void showToast(String mess){
        Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
    }
    private void AnhXa() {
        imgQuaylai = findViewById(R.id.imgQuayLai);
        txtTen = findViewById(R.id.txtTenNguoiDung);
        imgQR = findViewById(R.id.idQR);
        edtNhap = findViewById(R.id.edtNhapSo);
        imgTiepTuc = findViewById(R.id.imgNext);
        lnQuet = findViewById(R.id.linearQuetQR);
        lnDanhBaMay = findViewById(R.id.linearDanhBaMay);
        lnBanBeCoTheQuanBiet = findViewById(R.id.linearBanBeCoTheQuen);
    }
}