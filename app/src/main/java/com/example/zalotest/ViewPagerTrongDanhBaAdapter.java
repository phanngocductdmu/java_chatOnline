package com.example.zalotest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPagerTrongDanhBaAdapter extends FragmentStatePagerAdapter {
    public ViewPagerTrongDanhBaAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new TatCaFragment();
            case 1:
                return new MoiTruyCapFragment();
            default:
                return new TatCaFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "1";
            case 1:
                return "2";
            default:
                return "1";
        }
    }
}
