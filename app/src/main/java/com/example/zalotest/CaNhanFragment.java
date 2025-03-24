package com.example.zalotest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zalotest.CaNhan.CaiDat;
import com.example.zalotest.CaNhan.ChuyenDoiTaiKhaon;
import com.example.zalotest.CaNhan.CloudCuaToi;
import com.example.zalotest.CaNhan.DuLieuTrenMay;
import com.example.zalotest.CaNhan.NhacChoZalotest;
import com.example.zalotest.CaNhan.QuyenRiengTu;
import com.example.zalotest.CaNhan.TaiKhoanVaBaoMat;
import com.example.zalotest.CaNhan.TrangCaNhan;
import com.example.zalotest.CaNhan.ViQR;

public class CaNhanFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    ImageButton imgbtnTimkiem;
    TextView txtTimKiem, txtTenNguoiDung;
    ImageView imgAVT, imgChuyenTaiKhoan, imgSetting;
    ConstraintLayout ctTrangCaNhan, ctNhacCho, ctViQR, ctCloadCuaToi, ctDulieuTrenMay, ctTaiKhoanVaMatKhau,ctQuyenRiengTu;


    private String mParam1;
    private String mParam2;

    public CaNhanFragment() {

    }

    public static CaNhanFragment newInstance(String param1, String param2) {
        CaNhanFragment fragment = new CaNhanFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ca_nhan, container, false);
        AnhXa(view);
        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtTenNguoiDung = view.findViewById(R.id.txtTenNguoiDung);
        SharedPreferences s = requireContext().getSharedPreferences("SDT", Context.MODE_PRIVATE);
        String SoDienThoai = s.getString("SoDTUser", "");
        //fetchUserData(SoDienThoai);
    }

    private void AnhXa(View v){
        imgbtnTimkiem = v.findViewById(R.id.imgSearch);
        txtTimKiem = v.findViewById(R.id.txtSearch);
        imgSetting = v.findViewById(R.id.imgSetting);
        imgAVT = v.findViewById(R.id.imgAVT);
        txtTenNguoiDung = v.findViewById(R.id.txtTenNguoiDung);
        imgChuyenTaiKhoan = v.findViewById(R.id.imgChuyenDoitaiKhoan);
        ctNhacCho = v.findViewById(R.id.layoutNhacChoZalo);
        ctViQR = v.findViewById(R.id.layoutViQR);
        ctCloadCuaToi = v.findViewById(R.id.layoutCloud);
        ctDulieuTrenMay = v.findViewById(R.id.layoutDulieuTrenMay);
        ctTaiKhoanVaMatKhau = v.findViewById(R.id.layoutTaiKhoanVaBaoMat);
        ctQuyenRiengTu = v.findViewById(R.id.layoutQuyenRiengTu);
        ctTrangCaNhan = v.findViewById(R.id.layoutTrangCaNhan);
        imgbtnTimkiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireContext(), TimKiemBanBe.class));
            }
        });
        txtTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireContext(), TimKiemBanBe.class));
            }
        });
        imgSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireContext(), CaiDat.class));
            }
        });
        imgChuyenTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireContext(), ChuyenDoiTaiKhaon.class));
            }
        });
        ctNhacCho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireContext(), NhacChoZalotest.class));
            }
        });
        ctViQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireContext(), ViQR.class));
            }
        });
        ctCloadCuaToi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireContext(), CloudCuaToi.class));
            }
        });
        ctDulieuTrenMay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireContext(), DuLieuTrenMay.class));
            }
        });
        ctTaiKhoanVaMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireContext(), TaiKhoanVaBaoMat.class));
            }
        });
        ctQuyenRiengTu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireContext(), QuyenRiengTu.class));
            }
        });
        ctTrangCaNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireContext(), TrangCaNhan.class));
            }
        });
    }
}