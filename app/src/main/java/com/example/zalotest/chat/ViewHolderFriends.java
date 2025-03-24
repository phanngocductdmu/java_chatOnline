package com.example.zalotest.chat;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zalotest.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewHolderFriends extends RecyclerView.ViewHolder {
    CircleImageView imgAVT;
    TextView txtChatFriends;
    ImageView imgChatFriends;
    public ViewHolderFriends(@NonNull View itemView) {
        super(itemView);
        imgAVT = itemView.findViewById(R.id.imgAVT);
        txtChatFriends = itemView.findViewById(R.id.txtChatFriends);
        imgChatFriends = itemView.findViewById(R.id.imgChatFriends);
    }
}
