package com.example.zalotest.CaNhan.Setting.TaiKhoanVaBaoMat.pKiemTraBaoMat;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.zalotest.CaNhanFragment;
import com.example.zalotest.DanhBaFragment;
import com.example.zalotest.KhamPhaFragment;
import com.example.zalotest.NhatKyFragment;
import com.example.zalotest.TinNhanFragment;

public class KiemTraBaoMatViewPagerAdapter extends FragmentStatePagerAdapter {
    public KiemTraBaoMatViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new TrinhTrangFragment();
            case 1:
                return new LichSuFragment();
            default:
                return new TrinhTrangFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
