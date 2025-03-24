package com.example.zalotest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ViewPagerDanhBaAdapter extends FragmentStatePagerAdapter {
    private ViewPager mViewpager;
    private BottomNavigationView mBottomNavigationView;
    public ViewPagerDanhBaAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new BanBeFragment();
            case 1:
                return new NhomFragment();
            case 2:
                return new OAFragment();
            default:
                return new BanBeFragment();
        }
    }
    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "1";
            case 1:
                return "1";
            case 2:
                return "1";
            default:
                return "1";
        }
    }
}
