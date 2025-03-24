package com.example.zalotest;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LMDaGuiFragment extends ListFragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private ArrayList<DanhSachBanBe> ListLoiMoiDaGui;
    private DaGuiAdapter adapter;
    private View mView;
    private FirebaseDatabase mdata = FirebaseDatabase.getInstance();
    private DatabaseReference Account = mdata.getReference("Account");
    private DatabaseReference FriendRequest = mdata.getReference("FriendRequest");

    public LMDaGuiFragment() {
    }
    public static LMDaGuiFragment newInstance(String param1, String param2) {
        LMDaGuiFragment fragment = new LMDaGuiFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_l_m_da_gui, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListLoiMoiDaGui = new ArrayList<>();
        adapter = new DaGuiAdapter(this, R.layout.huy_loi_moi, ListLoiMoiDaGui);
        setListAdapter(adapter);
        SharedPreferences s = getContext().getSharedPreferences("IdUser", Context.MODE_PRIVATE);
        String IdUser = s.getString("IdUser", "");
        loiMoiDaGui(IdUser);
    }

    private void loiMoiDaGui(String id) {
        Account.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {
                    String ID = data.getKey();
                    if (id.equals(ID)) {
                        danhSachDaGui(ID);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("Firebase", "Database error: ", error.toException());
            }
        });
    }

    private void danhSachDaGui(String IDuser){
        FriendRequest.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ListLoiMoiDaGui.clear();
                for (DataSnapshot data : snapshot.getChildren()){
                    String IdUser = data.child("IDUser").getValue(String.class);
                    String IdFriend = data.child("IDFriend").getValue(String.class);
                    if (IDuser.equals(IdUser)){
                        Account.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for(DataSnapshot sn : snapshot.getChildren()){
                                    String Id = sn.getKey();
                                    String SDT = sn.child("SoDienThoai").getValue(String.class);
                                    String Ten = sn.child("TenNguoiDung").getValue(String.class);
                                    if(IdFriend.equals(Id)){
                                        DanhSachBanBe ds = new DanhSachBanBe(Id, SDT, Ten);
                                        ListLoiMoiDaGui.add(ds);
                                        adapter.notifyDataSetChanged();
                                    }
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void HuyLoiMoi(String IDFriend, String IdUser){
        Account.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {
                    String ID = data.getKey();
                    if (IdUser.equals(ID)) {
                        FriendRequest.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot sn : snapshot.getChildren()){
                                    String IDFriendRequest = sn.getKey();
                                    String IDUser = sn.child("IDUser").getValue(String.class);
                                    String IdFriend = sn.child("IDFriend").getValue(String.class);
                                    if (ID.equals(IDUser) && IDFriend.equals(IdFriend)){
                                        FriendRequest.child(IDFriendRequest).removeValue();
                                    }
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("Firebase", "Database error: ", error.toException());
            }
        });
    }


    private void showToast(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }
}