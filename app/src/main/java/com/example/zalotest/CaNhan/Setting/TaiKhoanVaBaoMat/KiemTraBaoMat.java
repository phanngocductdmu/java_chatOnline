package com.example.zalotest.CaNhan.Setting.TaiKhoanVaBaoMat;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.zalotest.CaNhan.Setting.TaiKhoanVaBaoMat.pKiemTraBaoMat.KiemTraBaoMatViewPagerAdapter;
import com.example.zalotest.R;
import com.example.zalotest.TinNhanFragment;
import com.example.zalotest.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class KiemTraBaoMat extends AppCompatActivity {
    private ImageButton imgBack;
    private ViewPager mViewpager;
    private BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_kiem_tra_bao_mat);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        AnhXa();
        viewpager();
        quayLai();
    }

    private void quayLai() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void viewpager() {
        KiemTraBaoMatViewPagerAdapter adapter = new KiemTraBaoMatViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewpager.setAdapter(adapter);
        mViewpager.setOffscreenPageLimit(2);
        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        mBottomNavigationView.getMenu().findItem(R.id.idTinhTrang).setChecked(true);
                        break;
                    case 1:
                        mBottomNavigationView.getMenu().findItem(R.id.idLichSu).setChecked(true);
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
                if (itemId == R.id.idTinhTrang) {
                    mViewpager.setCurrentItem(0);
                } else if (itemId == R.id.idLichSu) {
                    mViewpager.setCurrentItem(1);
                }
                return true;
            }
        });
    }
    private void AnhXa() {
        mViewpager = findViewById(R.id.idview_pager);
        mBottomNavigationView = findViewById(R.id.idBottomNavigation);
        imgBack = findViewById(R.id.imgBack);
    }
}