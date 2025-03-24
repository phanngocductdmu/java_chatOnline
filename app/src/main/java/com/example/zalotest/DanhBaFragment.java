package com.example.zalotest;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DanhBaFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private ViewPager mViewpager;
    private BottomNavigationView mBottomNavigationView;
    private View mView;

    public DanhBaFragment() {

    }

    public static DanhBaFragment newInstance(String param1, String param2) {
        DanhBaFragment fragment = new DanhBaFragment();
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
        mView = inflater.inflate(R.layout.fragment_danh_ba, container, false);
        mViewpager = mView.findViewById(R.id.idview_pager);
        mBottomNavigationView = mView.findViewById(R.id.idBottomNavigation);

        ViewPagerDanhBaAdapter viewPagerDanhBaAdapter = new ViewPagerDanhBaAdapter(getChildFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewpager.setAdapter(viewPagerDanhBaAdapter);
        mViewpager.setOffscreenPageLimit(3);
        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        mBottomNavigationView.getMenu().findItem(R.id.idBanBe).setChecked(true);
                        break;
                    case 1:
                        mBottomNavigationView.getMenu().findItem(R.id.idNhom).setChecked(true);
                        break;
                    case 2:
                        mBottomNavigationView.getMenu().findItem(R.id.idOA).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.idBanBe) {
                    mViewpager.setCurrentItem(0);
                    BanBeFragment banBeFragment = (BanBeFragment) mViewpager.getAdapter().instantiateItem(mViewpager, 0);
                } else if (itemId == R.id.idNhom) {
                    mViewpager.setCurrentItem(1);
                } else if (itemId == R.id.idOA) {
                    mViewpager.setCurrentItem(2);
                }
                return true;
            }
        });
        return mView;
    }
}