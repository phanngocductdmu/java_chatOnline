package com.example.zalotest.chat;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zalotest.R;

public class ViewHodelMe extends RecyclerView.ViewHolder {
    TextView txtChatMe, txtTrangThai;
    ImageView imgChatMe;
    public ViewHodelMe(@NonNull View itemView) {
        super(itemView);
        txtChatMe = itemView.findViewById(R.id.txtChatMe);
        txtTrangThai = itemView.findViewById(R.id.txtTrangThai);
        imgChatMe = itemView.findViewById(R.id.imgChatMe);
    }
}
