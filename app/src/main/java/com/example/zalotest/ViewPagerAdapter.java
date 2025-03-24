package com.example.zalotest;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new TinNhanFragment();
            case 1:
                return new DanhBaFragment();
            case 2:
                return new KhamPhaFragment();
            case 3:
                return new NhatKyFragment();
            case 4:
                return new CaNhanFragment();
            default:
                return new TinNhanFragment();
        }
    }

    @Override
    public int getCount() {
        return 5;
    }
}
