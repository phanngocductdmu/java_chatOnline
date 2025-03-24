package com.example.zalotest.CaNhan.Setting.TaiKhoanVaBaoMat.pKiemTraBaoMat;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zalotest.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class TrinhTrangFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    CircleImageView imgKhien;
    TextView txtTrinhTrang, txtVanDe, txtHoTroZalotest;
    ConstraintLayout ctDinhDanhTaiKhoan, ctThietBiKhacDangDangNhap, ctBaoMat2Lop, ctSoDienThoai, ctPhienBan, ctAnToan, ctKiemTraBaoMat, ctXuLyCanhBao;

    public TrinhTrangFragment() {
    }

    public static TrinhTrangFragment newInstance(String param1, String param2) {
        TrinhTrangFragment fragment = new TrinhTrangFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trinh_trang, container, false);
        AnhXa(view);
        return view;
    }

    private void AnhXa(View v) {
        imgKhien = v.findViewById(R.id.imgKhien);
        txtTrinhTrang = v.findViewById(R.id.txtTrinhTrang);
        txtVanDe = v.findViewById(R.id.txtVanDe);
        txtHoTroZalotest = v.findViewById(R.id.txtHoTroZalotest);
        ctDinhDanhTaiKhoan = v.findViewById(R.id.ctDinhDanhTaiKhoan);
        ctThietBiKhacDangDangNhap = v.findViewById(R.id.ctThietBiKhacDangDangNhap);
        ctBaoMat2Lop = v.findViewById(R.id.ctBaoMat2Lop);
        ctSoDienThoai = v.findViewById(R.id.ctSoDienThoai);
        ctPhienBan = v.findViewById(R.id.ctPhienBan);
        ctAnToan = v.findViewById(R.id.ctAnToan);
        ctKiemTraBaoMat = v.findViewById(R.id.ctKiemTraBaoMat);
        ctXuLyCanhBao = v.findViewById(R.id.ctXuLyCanhBao);
    }
}