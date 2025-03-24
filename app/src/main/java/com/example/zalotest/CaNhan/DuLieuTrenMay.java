package com.example.zalotest.CaNhan;

import android.content.Intent;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.zalotest.CaNhan.Setting.DuLieuTrenMay.XemVaDonDep;
import com.example.zalotest.R;

public class DuLieuTrenMay extends AppCompatActivity {
    SeekBar seekBar;
    ImageView imgBack;
    Button btnXemVaDonDepXam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_du_lieu_tren_may);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        AnhXa();
        duLieuTrenMay();
        quayLai();
        duLieuTroChuyen();
    }

    private void duLieuTroChuyen() {
        btnXemVaDonDepXam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DuLieuTrenMay.this, XemVaDonDep.class));
            }
        });
    }

    private void quayLai() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void AnhXa() {
        btnXemVaDonDepXam = findViewById(R.id.btnXemVaDonDepXam);
        seekBar = findViewById(R.id.seekBar);
        imgBack = findViewById(R.id.imgBack);
    }

    private void duLieuTrenMay() {
        seekBar.setMax(100);

        Drawable progressDrawable = seekBar.getProgressDrawable();
        LayerDrawable layerDrawable = (LayerDrawable) progressDrawable;

        int a = 1000;

        ClipDrawable greenClipDrawable = (ClipDrawable) layerDrawable.findDrawableByLayerId(R.id.progress_XanhNhat);
        greenClipDrawable.setLevel(a);

        int b = a + 5000;

        ClipDrawable yellowClipDrawable = (ClipDrawable) layerDrawable.findDrawableByLayerId(R.id.progress_XanhDam);
        yellowClipDrawable.setLevel(b);

        seekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }

}