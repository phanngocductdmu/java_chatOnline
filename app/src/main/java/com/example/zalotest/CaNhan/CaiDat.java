package com.example.zalotest.CaNhan;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.zalotest.CaNhan.Setting.CuocGoi;
import com.example.zalotest.CaNhan.Setting.DanhBa;
import com.example.zalotest.CaNhan.Setting.GiaoDienVaNgonNgu;
import com.example.zalotest.CaNhan.Setting.LienHeHoTro;
import com.example.zalotest.CaNhan.Setting.NhatKy;
import com.example.zalotest.CaNhan.Setting.SaoLuuVaKhoiPhuc;
import com.example.zalotest.CaNhan.Setting.ThongBao;
import com.example.zalotest.CaNhan.Setting.ThongTinVeZalotest;
import com.example.zalotest.CaNhan.Setting.sTinNhan;
import com.example.zalotest.DangNhapZalo;
import com.example.zalotest.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class CaiDat extends AppCompatActivity {
    private ImageButton imgQuayLai;
    private ConstraintLayout ctTaiKhoanVaBaoMat, ctQuyenRiengTu, ctDulieuTrenMay, ctSaoLuuVaKhoiPhuc, ctThongBao, ctTinNhan, ctCuocGoi, ctNhatKy, ctDanhBa, ctGiaoDienVaNgonNgu, ctThongTinZalotest, ctLienHeHoTro, ctChuyenTaiKhoan, ctDangXuat;
    private FirebaseDatabase mdata = FirebaseDatabase.getInstance();
    private DatabaseReference Account = mdata.getReference("Account");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cai_dat);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
                AnhXa();
                QuayLai();
                taiKhoanVaBaoMat();
                quyenRiengTu();
                duLieuTrenMay();
                saoLuuVaKhoiPhuc();
                thongBao();
                tinNhan();
                cuocGoi();
                nhatKy();
                danhBa();
                giaoDienVaNgonNgu();
                thongTinZalotest();
                lienHeHoTro();
                chuyenTaiKhoan();
                DangXuat();
    }

    private void DangXuat() {
        ctDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences s = getSharedPreferences("IdUser", Context.MODE_PRIVATE);
                String IdUser = s.getString("IdUser", "");

                Account.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot da : snapshot.getChildren()){
                            String id = da.getKey();
                            if(id.equals(IdUser)){
                                Map<String, Object> m = new HashMap<>();
                                m.put("Token", null);
                                Account.child(id).updateChildren(m);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                SharedPreferences.Editor editor = s.edit();
                editor.clear();
                editor.apply();
                startActivity(new Intent(CaiDat.this, DangNhapZalo.class));
                finish();
            }
        });
    }


    private void chuyenTaiKhoan() {
        ctChuyenTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CaiDat.this, ChuyenDoiTaiKhaon.class));
            }
        });
    }

    private void lienHeHoTro() {
        ctLienHeHoTro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CaiDat.this, LienHeHoTro.class));
            }
        });
    }

    private void thongTinZalotest() {
        ctThongTinZalotest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CaiDat.this, ThongTinVeZalotest.class));
            }
        });
    }

    private void giaoDienVaNgonNgu() {
        ctGiaoDienVaNgonNgu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CaiDat.this, GiaoDienVaNgonNgu.class));
            }
        });
    }

    private void danhBa() {
        ctDanhBa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CaiDat.this, DanhBa.class));
            }
        });
    }

    private void nhatKy() {
        ctNhatKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ctNhatKy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(CaiDat.this, NhatKy.class));
                    }
                });
            }
        });
    }

    private void cuocGoi() {
        ctCuocGoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CaiDat.this, CuocGoi.class));
            }
        });
    }

    private void tinNhan() {
        ctTinNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CaiDat.this, sTinNhan.class));
            }
        });
    }

    private void thongBao() {
        ctThongBao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CaiDat.this, ThongBao.class));
            }
        });
    }

    private void saoLuuVaKhoiPhuc() {
        ctSaoLuuVaKhoiPhuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CaiDat.this, SaoLuuVaKhoiPhuc.class));
            }
        });
    }

    private void duLieuTrenMay() {
        ctDulieuTrenMay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CaiDat.this, DuLieuTrenMay.class));
            }
        });
    }

    private void quyenRiengTu() {
        ctQuyenRiengTu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CaiDat.this, QuyenRiengTu.class));
            }
        });
    }

    private void taiKhoanVaBaoMat() {
        ctTaiKhoanVaBaoMat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CaiDat.this, TaiKhoanVaBaoMat.class));
            }
        });
    }

    private void QuayLai() {
        imgQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(CaiDat.this, CaNhanFragment.class));
                finish();
            }
        });
    }

    private void AnhXa() {
        imgQuayLai = findViewById(R.id.imgQuayLai);
        ctTaiKhoanVaBaoMat = findViewById(R.id.ctTaiKhoanVaBaoMat);
        ctQuyenRiengTu = findViewById(R.id.ctQuyenRiengTu);
        ctDulieuTrenMay = findViewById(R.id.ctDuLieuTrenMay);
        ctSaoLuuVaKhoiPhuc = findViewById(R.id.ctSaoLuuVaKhoiPhuc);
        ctThongBao = findViewById(R.id.ctThongBao);
        ctTinNhan = findViewById(R.id.ctTinNhan);
        ctCuocGoi = findViewById(R.id.ctCuocGoi);
        ctNhatKy = findViewById(R.id.ctNhatKy);
        ctDanhBa = findViewById(R.id.ctDanhDa);
        ctGiaoDienVaNgonNgu = findViewById(R.id.ctGiaoDienVaNgonNgu);
        ctThongTinZalotest = findViewById(R.id.ctThongTinZalotest);
        ctLienHeHoTro = findViewById(R.id.ctLienHeHoTro);
        ctChuyenTaiKhoan = findViewById(R.id.ctChuyenDoiTaiKhoan);
        ctDangXuat = findViewById(R.id.ctDangXuat);
    }
}