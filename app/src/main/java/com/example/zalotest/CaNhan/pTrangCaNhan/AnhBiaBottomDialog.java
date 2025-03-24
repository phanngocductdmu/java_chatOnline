package com.example.zalotest.CaNhan.pTrangCaNhan;

import android.content.Intent;
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


public class AnhBiaBottomDialog extends BottomSheetDialogFragment {
    private static final int REQUEST_CODE_GALLERY = 1;
    private ConstraintLayout ctXemAnhBia;
    private ConstraintLayout ctChupAnhMoi;
    private ConstraintLayout ctChonAnhTrenMay;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.chose_anh_bia, container, false);
        AnhXa(v);
        return v;
    }

    public void AnhXa(View v){
        ctXemAnhBia = v.findViewById(R.id.ctXemAnhBia);
        ctChupAnhMoi = v.findViewById(R.id.ctChupAnhMoi);
        ctChonAnhTrenMay = v.findViewById(R.id.ctChonAnhTrenMay);

        ctXemAnhBia.setOnClickListener(v1 -> {

        });

        ctChupAnhMoi.setOnClickListener(v1 -> {

        });

        ctChonAnhTrenMay.setOnClickListener(v1 -> {
            openGallery();
        });
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CODE_GALLERY);
    }

    @Override
    public void onStart() {
        super.onStart();

        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) getDialog();
        if (bottomSheetDialog != null) {
            bottomSheetDialog.getBehavior().setState(BottomSheetBehavior.STATE_EXPANDED);
            bottomSheetDialog.getBehavior().addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {
                    if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                        dismiss();
                    }
                }

                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                    // Không cần xử lý sự kiện này
                }
            });
        }
    }
}
