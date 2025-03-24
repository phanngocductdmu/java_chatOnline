package com.example.zalotest.CaNhan.Setting.TaiKhoanVaBaoMat.pKiemTraBaoMat;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zalotest.R;

public class LichSuFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView txtThangNam, txtLoaiBaoMat, txtDienThoaiThucHien, txtDiaChiThucHien;
    ImageView imgIcon;

    public LichSuFragment() {

    }

    public static LichSuFragment newInstance(String param1, String param2) {
        LichSuFragment fragment = new LichSuFragment();
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
        View view = inflater.inflate(R.layout.fragment_lich_su, container, false);
        AnhXa(view);
        return view;
    }

    private void AnhXa(View v) {
        txtThangNam = v.findViewById(R.id.txtThangNam);
        imgIcon = v.findViewById(R.id.imgIcon);
        txtLoaiBaoMat = v.findViewById(R.id.txtLoaiBaoMat);
        txtDienThoaiThucHien = v.findViewById(R.id.txtDienThoaiThucHien);
        txtDiaChiThucHien = v.findViewById(R.id.txtDiaChiThucHien);
    }
}