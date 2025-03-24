package com.example.zalotest.CaNhan.pTrangCaNhan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.zalotest.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class AnhAVTBottomDiaLog extends BottomSheetDialogFragment {
    private ConstraintLayout ctTaoKhoanhKhac, ctXemAnhDaiDien, ctChupAnhMoi, ctChonAnhTrenMay, ctChonAnhDaiDienCu;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.chose_anh_avt, container, false);
        AnhXa(v);
        return v;
    }

    private void AnhXa(View v) {
        ctTaoKhoanhKhac = v.findViewById(R.id.ctTaoKhoanhKhac);
        ctXemAnhDaiDien = v.findViewById(R.id.ctXemAnhDaiDien);
        ctChupAnhMoi = v.findViewById(R.id.ctChupAnhMoi);
        ctChonAnhTrenMay = v.findViewById(R.id.ctChonAnhTrenMay);
        ctChonAnhDaiDienCu = v.findViewById(R.id.ctChonAnhDaiDienCu);

        ctTaoKhoanhKhac.setOnClickListener(v1 -> {

        });
        ctXemAnhDaiDien.setOnClickListener(v1 -> {

        });
        ctChupAnhMoi.setOnClickListener(v1 -> {

        });
        ctChonAnhTrenMay.setOnClickListener(v1 -> {

        });
        ctChonAnhDaiDienCu.setOnClickListener(v1 -> {

        });
    }

    @Override
    public void onStart() {
        super.onStart();
        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) getDialog();
        if(bottomSheetDialog != null){
            bottomSheetDialog.getBehavior().setState(BottomSheetBehavior.STATE_EXPANDED);
            bottomSheetDialog.getBehavior().addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                @Override
                public void onStateChanged(@NonNull View view, int i) {
                    if(i == BottomSheetBehavior.STATE_HIDDEN){
                        dismiss();
                    }
                }

                @Override
                public void onSlide(@NonNull View view, float v) {

                }
            });
        }
    }
}
