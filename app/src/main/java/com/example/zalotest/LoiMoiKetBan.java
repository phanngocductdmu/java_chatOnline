package com.example.zalotest;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LoiMoiKetBan extends AppCompatActivity {
    private ViewPager mViewpagerr;
    private BottomNavigationView mBottomNavigationVieww;
    private ImageView imgQuayLai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_loi_moi_ket_ban);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        AnhXa();
        viewpager();
        QuayLai();
    }
    private void viewpager() {
        ViewPagerLoiMoiKetBanAdapter adapter = new ViewPagerLoiMoiKetBanAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewpagerr.setAdapter(adapter);
        mViewpagerr.setOffscreenPageLimit(1);
        mViewpagerr.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        mBottomNavigationVieww.getMenu().findItem(R.id.idLoiMoiDaNhan).setChecked(true);
                        break;
                    case 1:
                        mBottomNavigationVieww.getMenu().findItem(R.id.idLoiMoiDaGui).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mBottomNavigationVieww.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.idLoiMoiDaNhan) {
                    mViewpagerr.setCurrentItem(0);
                } else if (itemId == R.id.idLoiMoiDaGui) {
                    mViewpagerr.setCurrentItem(1);
                }
                return true;
            }
        });
    }
    private void QuayLai(){
        imgQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void AnhXa() {
        mViewpagerr = findViewById(R.id.viewPager);
        mBottomNavigationVieww = findViewById(R.id.bottom_navigation);
        imgQuayLai = findViewById(R.id.imgQuayLai);
    }
}