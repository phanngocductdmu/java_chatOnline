package com.example.zalotest;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BanBeFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;
    private ViewPager mViewpager;
    private BottomNavigationView mBottomNavigationView;
    private View mView;

    private ConstraintLayout layoutLoiMoiKetBan, layoutDanhBaMay, layoutLichSinhNhat;

    public BanBeFragment() {}

    public static BanBeFragment newInstance(String param1, String param2) {
        BanBeFragment fragment = new BanBeFragment();
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

    private void showToast(String mess){
        Toast.makeText(getContext(), mess, Toast.LENGTH_SHORT).show();
    }

    private void AnhXa() {
        layoutLoiMoiKetBan = mView.findViewById(R.id.layoutLoiMoiKetBan);
        layoutDanhBaMay = mView.findViewById(R.id.layoutDanhBa);
        layoutLichSinhNhat = mView.findViewById(R.id.layoutlichSinhNhat);
    }

    private void LoiMoiKetBan() {
        layoutLoiMoiKetBan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), LoiMoiKetBan.class));
            }
        });
    }

    private void DanhBaMay(){
        layoutDanhBaMay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Click Danh ba May");
            }
        });
    }

    private void LichSinhNhat(){
        layoutLichSinhNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Click Lich Sinh Nhat");
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_ban_be, container, false);
        AnhXa();
        LoiMoiKetBan();
        DanhBaMay();
        LichSinhNhat();

        mViewpager = mView.findViewById(R.id.idview_pager);
        mBottomNavigationView = mView.findViewById(R.id.idBottomNavigation);

        ViewPagerTrongDanhBaAdapter viewPagerTrongDanhBaAdapter = new ViewPagerTrongDanhBaAdapter(getChildFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewpager.setAdapter(viewPagerTrongDanhBaAdapter);
        mViewpager.setOffscreenPageLimit(1);
        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        if (!mBottomNavigationView.getMenu().findItem(R.id.idTatCa).isChecked())
                            mBottomNavigationView.getMenu().findItem(R.id.idTatCa).setChecked(true);
                        break;
                    case 1:
                        if (!mBottomNavigationView.getMenu().findItem(R.id.idMoiTruyCap).isChecked())
                            mBottomNavigationView.getMenu().findItem(R.id.idMoiTruyCap).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });

        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.idTatCa) {
                    if (mViewpager.getCurrentItem() != 0)
                        mViewpager.setCurrentItem(0);
                } else if (itemId == R.id.idMoiTruyCap) {
                    if (mViewpager.getCurrentItem() != 1)
                        mViewpager.setCurrentItem(1);
                }
                return true;
            }
        });
        return mView;
    }
}
