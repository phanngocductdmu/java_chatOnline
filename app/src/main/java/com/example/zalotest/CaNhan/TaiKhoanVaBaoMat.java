package com.example.zalotest.CaNhan;

import android.content.Intent;
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

import com.example.zalotest.CaNhan.Setting.TaiKhoanVaBaoMat.DinhDanhTaiKhoan;
import com.example.zalotest.CaNhan.Setting.TaiKhoanVaBaoMat.KhoaZalotest;
import com.example.zalotest.CaNhan.Setting.TaiKhoanVaBaoMat.KiemTraBaoMat;
import com.example.zalotest.CaNhan.Setting.TaiKhoanVaBaoMat.MaQRCuaToi;
import com.example.zalotest.CaNhan.Setting.TaiKhoanVaBaoMat.MatKhau;
import com.example.zalotest.CaNhan.Setting.TaiKhoanVaBaoMat.SoDienThoai;
import com.example.zalotest.CaNhan.Setting.TaiKhoanVaBaoMat.ThietBiDangNhap;
import com.example.zalotest.CaNhan.Setting.TaiKhoanVaBaoMat.ThongTinCaNhan;
import com.example.zalotest.CaNhan.Setting.TaiKhoanVaBaoMat.XoaTaiKhoan;
import com.example.zalotest.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class TaiKhoanVaBaoMat extends AppCompatActivity {
    ImageButton imgBack;
    CircleImageView imgAVT;
    ConstraintLayout ctThongTinCaNhan, ctSoDienThoai, ctDinhDanhTaiKhoan, ctMaQRCuaToi, ctKiemTraBaoMat, ctKhoaZalotest, ctThietBiDangNhap, ctMatKhau, ctXoaTaiKhoan;
    TextView txtSoDienThoai, txtDinhDanh, txtVanDe, txtTrangThai;
    Switch swBaoMat2Lop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tai_khoan_va_bao_mat);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        anhXa();
        quayLai();
        thongTinCaNhan();
        soDienThoai();
        dinhDanhTaiKhoan();
        maQRCuaToi();
        kiemTraBaoMat();
        khoaZalotest();
        thietBiDangNhap();
        matKhau();
        xoaTaiKhoan();
    }

    private void xoaTaiKhoan() {
        ctXoaTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TaiKhoanVaBaoMat.this, XoaTaiKhoan.class));
            }
        });
    }

    private void matKhau() {
        ctMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TaiKhoanVaBaoMat.this, MatKhau.class));
            }
        });
    }

    private void thietBiDangNhap() {
        ctThietBiDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TaiKhoanVaBaoMat.this, ThietBiDangNhap.class));
            }
        });
    }

    private void khoaZalotest() {
        ctKhoaZalotest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TaiKhoanVaBaoMat.this, KhoaZalotest.class));
            }
        });
    }

    private void kiemTraBaoMat() {
        ctKiemTraBaoMat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TaiKhoanVaBaoMat.this, KiemTraBaoMat.class));
            }
        });
    }

    private void maQRCuaToi() {
        ctMaQRCuaToi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TaiKhoanVaBaoMat.this, MaQRCuaToi.class));
            }
        });
    }

    private void dinhDanhTaiKhoan() {
        ctDinhDanhTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TaiKhoanVaBaoMat.this, DinhDanhTaiKhoan.class));
            }
        });
    }

    private void soDienThoai() {
        ctSoDienThoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TaiKhoanVaBaoMat.this, SoDienThoai.class));
            }
        });
    }

    private void thongTinCaNhan() {
        ctThongTinCaNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TaiKhoanVaBaoMat.this, ThongTinCaNhan.class));
            }
        });
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
        imgAVT = findViewById(R.id.imgAVT);
        ctThongTinCaNhan = findViewById(R.id.ctThongTinCaNhan);
        ctSoDienThoai = findViewById(R.id.ctSoDienThoai);
        txtSoDienThoai = findViewById(R.id.txtSoDienThoai);
        ctDinhDanhTaiKhoan = findViewById(R.id.ctDinhDanhTaiKhoan);
        txtDinhDanh = findViewById(R.id.txtDinhDanh);
        ctMaQRCuaToi = findViewById(R.id.ctMaQRCuaToi);
        ctKiemTraBaoMat = findViewById(R.id.ctKiemTraBaoMat);
        txtVanDe = findViewById(R.id.txtVanDe);
        ctKhoaZalotest = findViewById(R.id.ctKhoaZalotest);
        txtTrangThai = findViewById(R.id.txtTrangThai);
        swBaoMat2Lop = findViewById(R.id.swBaoMat2Lop);
        ctThietBiDangNhap = findViewById(R.id.ctThietBiDangNhap);
        ctMatKhau = findViewById(R.id.ctMatKhau);
        ctXoaTaiKhoan = findViewById(R.id.ctXoaTaiKhoan);
    }
}