package com.example.zalotest.chat;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zalotest.R;
import com.example.zalotest.TinNhan;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StickerPickerBottomSheet extends BottomSheetDialogFragment implements StickerAdapter.OnStickerClickListener {
    private RecyclerView recyclerView;
    private StickerAdapter adapter;
    private List<Integer> stickerList;
    private OnStickerSelectedListener onStickerSelectedListener;
    private FirebaseDatabase mdata = FirebaseDatabase.getInstance();
    private DatabaseReference Messenger = mdata.getReference("Messenger");
    public Map<Integer, String> stickerNameMap;

    public interface OnStickerSelectedListener {
        void onStickerSelected(int stickerResourceId);
    }

    public void setOnStickerSelectedListener(OnStickerSelectedListener listener) {
        this.onStickerSelectedListener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sticker_picker, container, false);

        recyclerView = view.findViewById(R.id.sticker_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));

        stickerNameMap = new HashMap<>();
        stickerList = new ArrayList<>();

        stickerList.add(R.drawable.angel);
        stickerNameMap.put(R.drawable.angel, "angel");

        stickerList.add(R.drawable.angry);
        stickerNameMap.put(R.drawable.angry, "angry");

        stickerList.add(R.drawable.confused);
        stickerNameMap.put(R.drawable.confused, "confused");

        stickerList.add(R.drawable.crying);
        stickerNameMap.put(R.drawable.crying, "crying");

        stickerList.add(R.drawable.drool);
        stickerNameMap.put(R.drawable.drool, "drool");

        stickerList.add(R.drawable.excited);
        stickerNameMap.put(R.drawable.excited, "excited");

        stickerList.add(R.drawable.freeze);
        stickerNameMap.put(R.drawable.freeze, "freeze");

        stickerList.add(R.drawable.hungry);
        stickerNameMap.put(R.drawable.hungry, "hungry");

        stickerList.add(R.drawable.in_love);
        stickerNameMap.put(R.drawable.in_love, "in_love");

        stickerList.add(R.drawable.laughing);
        stickerNameMap.put(R.drawable.laughing, "laughing");

        stickerList.add(R.drawable.ninja);
        stickerNameMap.put(R.drawable.ninja, "ninja");

        stickerList.add(R.drawable.rich);
        stickerNameMap.put(R.drawable.rich, "rich");

        stickerList.add(R.drawable.sick);
        stickerNameMap.put(R.drawable.sick, "sick");

        stickerList.add(R.drawable.sleepy);
        stickerNameMap.put(R.drawable.sleepy, "sleepy");

        stickerList.add(R.drawable.smart);
        stickerNameMap.put(R.drawable.smart, "smart");

        stickerList.add(R.drawable.strong);
        stickerNameMap.put(R.drawable.strong, "strong");

        stickerList.add(R.drawable.superhero);
        stickerNameMap.put(R.drawable.superhero, "superhero");

        stickerList.add(R.drawable.surprised);
        stickerNameMap.put(R.drawable.surprised, "surprised");

        stickerList.add(R.drawable.vampire);
        stickerNameMap.put(R.drawable.vampire, "vampire");

        stickerList.add(R.drawable.wink);
        stickerNameMap.put(R.drawable.wink, "wink");

        adapter = new StickerAdapter(getContext(), stickerList, this);
        recyclerView.setAdapter(adapter);


        return view;
    }

    @Override
    public void onStickerClick(int stickerResourceId) {

        String stickerName = stickerNameMap.get(stickerResourceId);
        if (stickerName != null) {
            //Toast.makeText(getContext(), stickerName, Toast.LENGTH_SHORT).show();
        }

        if (onStickerSelectedListener != null) {
            onStickerSelectedListener.onStickerSelected(stickerResourceId);
        }
        dismiss();
    }
}
