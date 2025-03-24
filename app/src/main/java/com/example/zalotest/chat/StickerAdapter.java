package com.example.zalotest.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zalotest.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class StickerAdapter extends RecyclerView.Adapter<StickerAdapter.StickerViewHolder> {
    private Context context;
    private List<Integer> stickerList;
    private OnStickerClickListener onStickerClickListener;
    public interface OnStickerClickListener {
        void onStickerClick(int stickerResourceId);
    }

    public StickerAdapter(Context context, List<Integer> stickerList, OnStickerClickListener onStickerClickListener) {
        this.context = context;
        this.stickerList = stickerList;
        this.onStickerClickListener = onStickerClickListener;
    }

    @NonNull
    @Override
    public StickerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_sticker, parent, false);
        return new StickerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StickerViewHolder holder, int position) {
        int stickerResId = stickerList.get(position);
        holder.stickerImage.setImageResource(stickerResId);
        holder.itemView.setOnClickListener(v -> onStickerClickListener.onStickerClick(stickerResId));
    }

    @Override
    public int getItemCount() {
        return stickerList.size();
    }

    public static class StickerViewHolder extends RecyclerView.ViewHolder {
        ImageView stickerImage;

        public StickerViewHolder(@NonNull View itemView) {
            super(itemView);
            stickerImage = itemView.findViewById(R.id.sticker_image);
        }
    }
}
