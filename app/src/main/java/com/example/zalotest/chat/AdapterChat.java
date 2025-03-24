package com.example.zalotest.chat;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.zalotest.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AdapterChat extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int VIEW_TYPE_ME = 1;
    private static final int VIEW_TYPE_FRIENDS = 2;
    private TextView currentStatusView = null;
    private boolean isStatusViewVisible = false;
    private Context mContext;
    private List<TinNhanBanBe> mDataList;
    private MediaPlayer mediaPlayer;
    private ValueAnimator seekBarAnimator;
    private boolean isPlaying = false;
    private boolean isPaused = false;
    public AdapterChat(Context context, List<TinNhanBanBe> dataList) {
        this.mContext = context;
        this.mDataList = dataList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view;
        switch (viewType) {
            case VIEW_TYPE_ME:
                view = inflater.inflate(R.layout.chat_me, parent, false);
                return new ViewHodelMe(view);
            case VIEW_TYPE_FRIENDS:
                view = inflater.inflate(R.layout.chat_friends, parent, false);
                return new ViewHodelFrends(view);
            default:
                throw new IllegalArgumentException("Unsupported view type");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TinNhanBanBe tinNhan = mDataList.get(position);
        String chat = tinNhan.getChat();
        String TypeChats = tinNhan.getTypeChats();
        long thoiGian = Long.parseLong(tinNhan.getRealTime());
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String formattedTime = dateFormat.format(new Date(thoiGian));

        switch (holder.getItemViewType()) {
            case VIEW_TYPE_ME:
                ViewHodelMe holderMe = (ViewHodelMe) holder;
                handleStickerAndMessage(holderMe, tinNhan, chat, formattedTime, TypeChats);
                break;

            case VIEW_TYPE_FRIENDS:
                ViewHodelFrends holderFriends = (ViewHodelFrends) holder;
                handleStickerAndMessage(holderFriends, tinNhan, chat, formattedTime, TypeChats);
                break;
        }
    }
    private void handleStickerAndMessage(ViewHodelMe holder, TinNhanBanBe tinNhan, String chat, String formattedTime, String TypeChats) {
        holder.txtChatMe.setVisibility(View.INVISIBLE);
        holder.imgStick.setVisibility(View.INVISIBLE);
        holder.imgChatMe.setVisibility(View.INVISIBLE);
        holder.txtThoiGianMe.setVisibility(View.INVISIBLE);
        holder.ctTinNhan.setVisibility(View.INVISIBLE);

        if (chat != null) {
            if(TypeChats.equals("tinNhan")){
                holder.ctTinNhan.setVisibility(View.VISIBLE);
                holder.txtChatMe.setVisibility(View.VISIBLE);
                holder.imgChatMe.setVisibility(View.INVISIBLE);
                holder.txtChatMe.setText(chat);
                holder.txtTrangThai.setText(tinNhan.getTrangThai());
                holder.ctTinNhan.setOnClickListener(v -> {
                    if (holder.txtTrangThai == currentStatusView && isStatusViewVisible) {
                        holder.txtTrangThai.setVisibility(View.INVISIBLE);
                        isStatusViewVisible = false;
                    } else {
                        updateStatus(holder.txtTrangThai);
                        isStatusViewVisible = true;
                    }
                    currentStatusView = holder.txtTrangThai;
                });
                holder.txtThoiGianMe.setVisibility(View.VISIBLE);
                holder.txtThoiGianMe.setText(formattedTime);
                holder.ctTinNhan.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        showToast(tinNhan.getChat());
                        return true;
                    }
                });
            } else if(TypeChats.equals("Sticker")){
                int drawableId = mContext.getResources().getIdentifier(chat, "drawable", mContext.getPackageName());
                if (drawableId != 0) {
                    holder.imgStick.setVisibility(View.VISIBLE);
                    holder.imgStick.setImageResource(drawableId);
                }
            }else if(TypeChats.equals("ghiAm")){
                holder.ctTinNhan.setVisibility(View.INVISIBLE);
                holder.ctGhiAmMe.setVisibility(View.VISIBLE);
                holder.txtThoiGianGhiAmMe.setVisibility(View.VISIBLE);
                holder.txtThoiGianGhiAmMe.setText(formattedTime);
                holder.imgTiepTucMe.setOnClickListener(v -> {
                    togglePlayPauseMe(chat, holder);
                });
            }else if(TypeChats.equals("hinhAnh")){
                    Glide.with(mContext).load(chat).into(holder.imgChatMe);
                    holder.txtChatMe.setVisibility(View.GONE);
                    holder.txtThoiGianMe.setText(formattedTime);
                    holder.txtThoiGianMe.setVisibility(View.GONE);
                    holder.imgChatMe.setVisibility(View.VISIBLE);
            }
        }
    }
    private void togglePlayPauseMe(String url, ViewHodelMe holder) {
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
            try {
                mediaPlayer.setDataSource(url);
                mediaPlayer.prepare();
                mediaPlayer.start();
                isPlaying = true;
                isPaused = false;
                holder.seekBarMe.setMax(mediaPlayer.getDuration());
                holder.seekBarMe.setProgress(0);
                startSeekBarAnimatorMe(holder);
                holder.imgTiepTucMe.setImageResource(R.drawable.tamdung);
                mediaPlayer.setOnCompletionListener(mp -> {
                    resetMediaPlayerMe(holder);
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (isPlaying) {
            mediaPlayer.pause();
            isPlaying = false;
            isPaused = true;

            if (seekBarAnimator != null) {
                seekBarAnimator.cancel();
            }

            holder.imgTiepTucMe.setImageResource(R.drawable.tieptuc);
        } else if (isPaused) {
            mediaPlayer.start();
            isPlaying = true;
            isPaused = false;

            startSeekBarAnimatorMe(holder);
            holder.imgTiepTucMe.setImageResource(R.drawable.tamdung);
        }
    }

    private void startSeekBarAnimatorMe(ViewHodelMe holder) {
        if (seekBarAnimator != null) {
            seekBarAnimator.cancel();
        }

        seekBarAnimator = ValueAnimator.ofInt(holder.seekBarMe.getProgress(), mediaPlayer.getDuration());
        seekBarAnimator.setDuration(mediaPlayer.getDuration() - mediaPlayer.getCurrentPosition());
        seekBarAnimator.setInterpolator(new LinearInterpolator());
        seekBarAnimator.addUpdateListener(animation -> {
            int progress = (int) animation.getAnimatedValue();
            holder.seekBarMe.setProgress(progress);
        });
        seekBarAnimator.start();
    }

    private void resetMediaPlayerMe(ViewHodelMe holder) {
        if (seekBarAnimator != null) {
            seekBarAnimator.cancel();
        }

        holder.seekBarMe.setProgress(0);
        mediaPlayer.release();
        mediaPlayer = null;

        isPlaying = false;
        isPaused = false;

        holder.imgTiepTucMe.setImageResource(R.drawable.tieptuc);
    }


    private void handleStickerAndMessage(ViewHodelFrends holder, TinNhanBanBe tinNhan, String chat, String formattedTime, String TypeChats) {
        holder.imgStickFriend.setVisibility(View.INVISIBLE);
        holder.txtChatFriends.setVisibility(View.INVISIBLE);
        holder.imgStickFriend.setVisibility(View.INVISIBLE);
        holder.txtThoiGianFriend.setVisibility(View.INVISIBLE);
        holder.ctChatFriend.setVisibility(View.INVISIBLE);
        if (chat != null) {
            if(TypeChats.equals("tinNhan")){
                holder.ctChatFriend.setVisibility(View.VISIBLE);
                holder.txtChatFriends.setVisibility(View.VISIBLE);
                holder.txtChatFriends.setText(chat);
                holder.txtThoiGianFriend.setText(formattedTime);
                holder.txtThoiGianFriend.setVisibility(View.VISIBLE);
                holder.imgChatFriends.setVisibility(View.INVISIBLE);
                holder.imgStickFriend.setVisibility(View.INVISIBLE);
            } else if(TypeChats.equals("Sticker")){
                int drawableId = mContext.getResources().getIdentifier(chat, "drawable", mContext.getPackageName());
                if (drawableId != 0) {
                    holder.ctChatFriend.setVisibility(View.INVISIBLE);
                    holder.imgStickFriend.setVisibility(View.VISIBLE);
                    holder.imgStickFriend.setImageResource(drawableId);
                    holder.ctChatFriend.setVisibility(View.INVISIBLE);
                }
            }else if(TypeChats.equals("ghiAm")){
                holder.ctChatFriend.setVisibility(View.INVISIBLE);
                holder.ctGhiAmFriend.setVisibility(View.VISIBLE);
                holder.txtThoiGianGhiAmFriend.setVisibility(View.VISIBLE);
                holder.txtThoiGianGhiAmFriend.setText(formattedTime);
                holder.imgTiepTucFriend.setOnClickListener(v -> {
                    togglePlayPauseFriend(chat, holder);
                });
            }else if(TypeChats.equals("hinhAnh")){
                Glide.with(mContext).load(chat).into(holder.imgChatFriends);
                holder.txtChatFriends.setVisibility(View.GONE);
                holder.txtThoiGianFriend.setText(formattedTime);
                holder.txtThoiGianFriend.setVisibility(View.GONE);
                holder.imgChatFriends.setVisibility(View.VISIBLE);
            }
        }
    }

    private void togglePlayPauseFriend(String url, ViewHodelFrends holder) {
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
            try {
                mediaPlayer.setDataSource(url);
                mediaPlayer.prepare();
                mediaPlayer.start();
                isPlaying = true;
                isPaused = false;
                holder.seekBarFriend.setMax(mediaPlayer.getDuration());
                holder.seekBarFriend.setProgress(0);
                startSeekBarAnimatorFriend(holder);
                holder.imgTiepTucFriend.setImageResource(R.drawable.tamdung);
                mediaPlayer.setOnCompletionListener(mp -> {
                    resetMediaPlayerFriend(holder);
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (isPlaying) {
            mediaPlayer.pause();
            isPlaying = false;
            isPaused = true;

            if (seekBarAnimator != null) {
                seekBarAnimator.cancel();
            }

            holder.imgTiepTucFriend.setImageResource(R.drawable.tieptuc);
        } else if (isPaused) {
            mediaPlayer.start();
            isPlaying = true;
            isPaused = false;

            startSeekBarAnimatorFriend(holder);
            holder.imgTiepTucFriend.setImageResource(R.drawable.tamdung);
        }
    }

    private void startSeekBarAnimatorFriend(ViewHodelFrends holder) {
        if (seekBarAnimator != null) {
            seekBarAnimator.cancel();
        }

        seekBarAnimator = ValueAnimator.ofInt(holder.seekBarFriend.getProgress(), mediaPlayer.getDuration());
        seekBarAnimator.setDuration(mediaPlayer.getDuration() - mediaPlayer.getCurrentPosition());
        seekBarAnimator.setInterpolator(new LinearInterpolator());
        seekBarAnimator.addUpdateListener(animation -> {
            int progress = (int) animation.getAnimatedValue();
            holder.seekBarFriend.setProgress(progress);
        });
        seekBarAnimator.start();
    }

    private void resetMediaPlayerFriend(ViewHodelFrends holder) {
        if (seekBarAnimator != null) {
            seekBarAnimator.cancel();
        }

        holder.seekBarFriend.setProgress(0);
        mediaPlayer.release();
        mediaPlayer = null;

        isPlaying = false;
        isPaused = false;

        holder.imgTiepTucFriend.setImageResource(R.drawable.tieptuc);
    }

    private void updateStatus(TextView newStatusView) {
        if (currentStatusView != null && currentStatusView != newStatusView) {
            currentStatusView.setVisibility(View.GONE);
        }
        newStatusView.setVisibility(View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        TinNhanBanBe item = mDataList.get(position);
        SharedPreferences s = mContext.getSharedPreferences("IdUser", Context.MODE_PRIVATE);
        String IdUser = s.getString("IdUser", "");
        if (item.getIDFriends().equals(IdUser)){
            return VIEW_TYPE_ME;
        } else {
            return VIEW_TYPE_FRIENDS;
        }
    }

    public static class ViewHodelMe extends RecyclerView.ViewHolder {
        TextView txtChatMe, txtThoiGianMe, txtTrangThai, txtThoiGianGhiAmMe;
        ImageView imgChatMe, imgStick, imgTiepTucMe;
        ConstraintLayout ctTinNhan, ctGhiAmMe;
        SeekBar seekBarMe;

        public ViewHodelMe(@NonNull View v) {
            super(v);
            txtChatMe = v.findViewById(R.id.txtChatMe);
            imgChatMe = v.findViewById(R.id.imgChatMe);
            txtThoiGianMe = v.findViewById(R.id.txtThoiGianMe);
            txtTrangThai = v.findViewById(R.id.txtTrangThai);
            ctTinNhan = v.findViewById(R.id.ctTinNhan);
            imgStick = v.findViewById(R.id.imgStick);
            txtThoiGianGhiAmMe = v.findViewById(R.id.txtThoiGianGhiAmMe);
            imgTiepTucMe = v.findViewById(R.id.imgTiepTucMe);
            ctGhiAmMe = v.findViewById(R.id.ctGhiAmMe);
            seekBarMe = v.findViewById(R.id.seekBarMe);
        }
    }

    public static class ViewHodelFrends extends RecyclerView.ViewHolder {
        TextView txtChatFriends, txtThoiGianFriend, txtThoiGianGhiAmFriend;
        ImageView imgChatFriends, imgStickFriend, imgTiepTucFriend;
        ConstraintLayout ctChatFriend, ctGhiAmFriend;
        SeekBar seekBarFriend;

        public ViewHodelFrends(@NonNull View v) {
            super(v);
            txtChatFriends = v.findViewById(R.id.txtChatFriends);
            imgChatFriends = v.findViewById(R.id.imgChatFriends);
            txtThoiGianFriend = v.findViewById(R.id.txtThoiGianFriend);
            imgStickFriend = v.findViewById(R.id.imgStickFriend);
            ctChatFriend = v.findViewById(R.id.ctChatFriend);
            txtThoiGianGhiAmFriend = v.findViewById(R.id.txtThoiGianGhiAmFriend);
            imgTiepTucFriend = v.findViewById(R.id.imgTiepTucFriend);
            ctGhiAmFriend = v.findViewById(R.id.ctGhiAmFriend);
            seekBarFriend = v.findViewById(R.id.seekBarFriend);
        }
    }
    public void showToast(String a){
        Toast.makeText(mContext, a, Toast.LENGTH_SHORT).show();
    }
}
