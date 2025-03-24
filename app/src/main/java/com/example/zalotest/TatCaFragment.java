package com.example.zalotest;

import android.os.Bundle;

import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class TatCaFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ArrayList<DanhSachBanBe> danhSachBanBeArrayList;
    //private DanhSachBBadapter adapter;

    private String mParam1;
    private String mParam2;

    public TatCaFragment() {

    }

    public static TatCaFragment newInstance(String param1, String param2) {
        TatCaFragment fragment = new TatCaFragment();
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
        return inflater.inflate(R.layout.fragment_tat_ca, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        danhSachBanBeArrayList = new ArrayList<>();
        /*adapter = new DanhSachBBadapter(TatCaFragment.this, R.layout.danh_sach_ban_be, danhSachBanBeArrayList);
        ListView listView = view.findViewById(R.id.lstTatCa);
        listView.setAdapter(adapter);*/
        //fetchData(cn.urlTatCa);
    }
    /*private void fetchData(String url) {
        RequestQueue queue = Volley.newRequestQueue(requireContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    danhSachBanBeArrayList.clear();
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject object = response.getJSONObject(i);
                            danhSachBanBeArrayList.add(new DanhSachBanBe(
                                    object.getInt("IdTaiKhoan"),
                                    object.getString("SoDienThoai"),
                                    object.getString("TenNguoiDung"),
                                    object.getString("NgaySinh"),
                                    object.getString("GioiTinh"),
                                    object.getString("ImageAnhDaiDien"),
                                    object.getString("ImageBia"),
                                    object.getString("MatKhau")
                            ));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    adapter.notifyDataSetChanged();
                },
                error -> showToast("Error fetching data"));
        queue.add(jsonArrayRequest);
    }*/
    private void showToast(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }
}