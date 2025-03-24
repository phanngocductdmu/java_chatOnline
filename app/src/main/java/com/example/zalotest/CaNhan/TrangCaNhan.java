package com.example.zalotest.CaNhan;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zalotest.CaNhan.pTrangCaNhan.*;
import com.example.zalotest.R;

import java.util.ArrayList;
import java.util.List;

public class TrangCaNhan extends AppCompatActivity {
    ImageView imgQuayLai, imgPlus, imgBia, imgAVT, imgChonAnh;
    TextView txtTenNguoiDung, txtTieuSu;
    RecyclerView recyclerView;
    EditText editText;
    private List<String> dataList = new ArrayList<>();
    private canhanAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_trang_ca_nhan);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        AnhXa();
        quayLai();
        item();
        anhBia();
        anhAVT();
    }

    private void anhAVT() {
        imgAVT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnhAVTBottomDiaLog anhAVTBottomDiaLog = new AnhAVTBottomDiaLog();
                anhAVTBottomDiaLog.show(getSupportFragmentManager(),"MyBottomSheetDialog");
            }
        });
    }

    private void anhBia() {
        imgBia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnhBiaBottomDialog anhBiaBottomDialog = new AnhBiaBottomDialog();
                anhBiaBottomDialog.show(getSupportFragmentManager(),"MyBottomSheetDialog");
            }
        });
    }

    private void item() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        dataList.add("Nhạc chờ");
        dataList.add("Nhập từ Facebook");
        dataList.add("Ảnh của tôi 1");
        dataList.add("Kho khoãnh khắc");
        dataList.add("Kỷ niệm xưa");
        dataList.add("Yêu thích nhất");
        dataList.add("Bình luận nhiều");
        dataList.add("Video của tôi 1");

        // Khởi tạo và gắn Adapter cho RecyclerView
        adapter = new canhanAdapter(dataList);
        recyclerView.setAdapter(adapter);
    }

    private void quayLai() {
        imgQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void AnhXa() {
        imgQuayLai = findViewById(R.id.imgQuayLai);
        imgPlus = findViewById(R.id.imgPlus);
        imgBia = findViewById(R.id.imgAnhBia);
        imgAVT = findViewById(R.id.imgAVT);
        txtTenNguoiDung = findViewById(R.id.txtTenNguoiDung);
        txtTieuSu = findViewById(R.id.txtTieuSu);
        recyclerView = findViewById(R.id.recyclerView);
        editText = findViewById(R.id.editText);
        imgChonAnh = findViewById(R.id.imgChonAnh);
    }
}