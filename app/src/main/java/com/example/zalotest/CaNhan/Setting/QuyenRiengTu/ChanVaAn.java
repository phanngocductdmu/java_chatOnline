package com.example.zalotest.CaNhan.Setting.QuyenRiengTu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.zalotest.CaNhan.Setting.QuyenRiengTu.pChanVaAn.pAnKhoiNhatKy;
import com.example.zalotest.CaNhan.Setting.QuyenRiengTu.pChanVaAn.pChanCuocGoi;
import com.example.zalotest.CaNhan.Setting.QuyenRiengTu.pChanVaAn.pChanTinNhan;
import com.example.zalotest.CaNhan.Setting.QuyenRiengTu.pChanVaAn.pChanXemKhoanhKhac;
import com.example.zalotest.CaNhan.Setting.QuyenRiengTu.pChanVaAn.pChanXemNhatKy;
import com.example.zalotest.R;

public class ChanVaAn extends AppCompatActivity {
    ImageButton imgBack;
    ConstraintLayout ctChanTinNhan, ctChanCuocGoi, ctChanXemNhatKy, ctChanXemKhoanhKhac, ctAnKhoiNhatKy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chan_va_an);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        anhXa();
        QuayLai();
        chanTinNhan();
        chanCuocGoi();
        chanXemNhatKy();
        anKhoiNhatKy();
        chanXemKhoanhKhac();
    }

    private void chanXemKhoanhKhac() {
        ctChanXemKhoanhKhac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChanVaAn.this, pChanXemKhoanhKhac.class));
            }
        });
    }

    private void anKhoiNhatKy() {
        ctAnKhoiNhatKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChanVaAn.this, pAnKhoiNhatKy.class));
            }
        });
    }

    private void chanXemNhatKy() {
        ctChanXemNhatKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChanVaAn.this, pChanXemNhatKy.class));
            }
        });
    }

    private void chanCuocGoi() {
        ctChanCuocGoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChanVaAn.this, pChanCuocGoi.class));
            }
        });
    }

    private void chanTinNhan() {
        ctChanTinNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChanVaAn.this, pChanTinNhan.class));
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

    private void anhXa() {
        imgBack = findViewById(R.id.imgBack);
        ctChanTinNhan = findViewById(R.id.ctChanTinNhan);
        ctChanCuocGoi = findViewById(R.id.ctChanCuocGoi);
        ctChanXemNhatKy = findViewById(R.id.ctChanXemNhatKy);
        ctChanXemKhoanhKhac = findViewById(R.id.ctChanXemKhoanhKhac);
        ctAnKhoiNhatKy = findViewById(R.id.ctAnKhoiNhatKy);
    }
}