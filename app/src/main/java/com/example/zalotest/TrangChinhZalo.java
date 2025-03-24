package com.example.zalotest;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TrangChinhZalo extends AppCompatActivity {
    private ViewPager mViewpager;
    private BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_trang_chinh_zalo);
        AnhXa();
        viewpager();
        // Lắng nghe sự thay đổi của WindowInsets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            // Lấy kích thước của các thanh hệ thống như thanh trạng thái và thanh điều hướng
            Insets systemBarsInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            // Đặt padding cho view chính để tránh các vùng hệ thống
            v.setPadding(systemBarsInsets.left, systemBarsInsets.top, systemBarsInsets.right, systemBarsInsets.bottom);
            return WindowInsetsCompat.CONSUMED;
        });
    }


    private void viewpager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewpager.setAdapter(adapter);
        mViewpager.setOffscreenPageLimit(4);
        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        mBottomNavigationView.getMenu().findItem(R.id.idTinNhan).setChecked(true);
                        break;
                    case 1:
                        mBottomNavigationView.getMenu().findItem(R.id.idDanhBa).setChecked(true);
                        break;
                    case 2:
                        mBottomNavigationView.getMenu().findItem(R.id.idKhamPha).setChecked(true);
                        break;
                    case 3:
                        mBottomNavigationView.getMenu().findItem(R.id.idNhatKy).setChecked(true);
                        break;
                    case 4:
                        mBottomNavigationView.getMenu().findItem(R.id.idCaNhan).setChecked(true);
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
                if (itemId == R.id.idTinNhan) {
                    mViewpager.setCurrentItem(0);
                    TinNhanFragment tinNhanFragment = (TinNhanFragment) mViewpager.getAdapter().instantiateItem(mViewpager, 0);
                } else if (itemId == R.id.idDanhBa) {
                    mViewpager.setCurrentItem(1);
                } else if (itemId == R.id.idKhamPha) {
                    mViewpager.setCurrentItem(2);
                } else if (itemId == R.id.idNhatKy) {
                    mViewpager.setCurrentItem(3);
                } else if (itemId == R.id.idCaNhan) {
                    mViewpager.setCurrentItem(4);
                }
                return true;
            }
        });
    }

    private void AnhXa() {
        mViewpager = findViewById(R.id.idview_pager);
        mBottomNavigationView = findViewById(R.id.idBottomNavigation);
    }
}