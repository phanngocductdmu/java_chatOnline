package com.example.zalotest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TaoTaiKhoan extends AppCompatActivity {
    private ImageButton imgBack;
    private EditText edtNhapSo;
    private CheckBox cb1, cb2;
    private Button btnTiepTuc;
    private TextView txt1, txt2, txtDangNhapNgay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tao_tai_khoan);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        AnhXa();
        QuayLai();
        DangNhapNgay();
        TiepTuc();
        DieuKhoanMot();
        DieuKhoanHai();
    }

    private void DieuKhoanHai() {
        txt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TaoTaiKhoan.this, DieuKhoan2.class));
            }
        });
    }

    private void DieuKhoanMot() {
        txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TaoTaiKhoan.this, DieuKhoan1.class));
            }
        });
    }

    private void showToast(String mess){
        Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
    }

    private void TiepTuc() {
        btnTiepTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String soDT = edtNhapSo.getText().toString().trim();
                    //////////////////////////////////////////////////////////////////////////////////////////////////
                    SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("soDT", soDT);
                    editor.apply();
                    //////////////////////////////////////////////////////////////////////////////////////////////////
                    int SDT = Integer.parseInt(soDT);
                    boolean checkBox1 = cb1.isChecked();
                    boolean checkBox2 = cb2.isChecked();
                    if (soDT.isEmpty() || SDT < 99999999) {
                        showToast("Vui lòng nhập lại số điện thoại");
                    }else if(!checkBox1 || !checkBox2){
                        showToast("Vui lòng chấp nhận điều khoản");
                    }else {
                        startActivity(new Intent(TaoTaiKhoan.this, DangKy.class));
                    }
                }catch (NumberFormatException e){
                    showToast("số điện thoại không hợp lệ");
                }
            }
        });
    }

    private void DangNhapNgay() {
        txtDangNhapNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TaoTaiKhoan.this, DangNhapZalo.class));
            }
        });
    }

    private void QuayLai() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void AnhXa() {
        imgBack = findViewById(R.id.imgBack);
        edtNhapSo = findViewById(R.id.edtNhapSo);
        cb1 = findViewById(R.id.cb1);
        cb2 = findViewById(R.id.cb2);
        btnTiepTuc = findViewById(R.id.btnTiepTuc);
        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        txtDangNhapNgay = findViewById(R.id.txtDangNhapNgay);
    }
}