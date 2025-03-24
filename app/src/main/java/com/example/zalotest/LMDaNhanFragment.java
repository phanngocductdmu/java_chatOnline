package com.example.zalotest;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LMDaNhanFragment extends ListFragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private ArrayList<DanhSachBanBe> ListLoiMoiDaNhan;
    private DaNhanAdapter adapter;
    private View mview;
    private FirebaseDatabase mdata = FirebaseDatabase.getInstance();
    private DatabaseReference FriendRequest = mdata.getReference("FriendRequest");
    private DatabaseReference Account = mdata.getReference("Account");
    private DatabaseReference Friends = mdata.getReference("Friends");
    private DatabaseReference Chats = mdata.getReference("Chats");
    private int SLN;

    public LMDaNhanFragment() {}

    public static LMDaNhanFragment newInstance(String param1, String param2) {
        LMDaNhanFragment fragment = new LMDaNhanFragment();
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
        mview = inflater.inflate(R.layout.fragment_l_m_da_nhan, container, false);
        return mview;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListLoiMoiDaNhan = new ArrayList<>();
        adapter = new DaNhanAdapter(this, R.layout.xac_nhan_loi_moi, ListLoiMoiDaNhan);
        setListAdapter(adapter);
        SharedPreferences s = getContext().getSharedPreferences("IdUser", Context.MODE_PRIVATE);
        String IdUser = s.getString("IdUser", "");
        DanhSachDaNhan(IdUser);
    }
    public void getMaxSTT(String IdUser, String IDFriend) {
        Friends.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int maxSTT = Integer.MIN_VALUE;
                for (DataSnapshot da : snapshot.getChildren()) {
                    Integer stt = da.child("STT").getValue(Integer.class);
                    if (stt != null && stt > maxSTT) {
                        maxSTT = stt;
                    }
                }
                SLN = maxSTT+1;
                xacNhanLoiMoi(IdUser, IDFriend, SLN);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    private void xacNhanLoiMoi(String IdUser, String IDFriend, int STT){
        DatabaseReference newFriends = Friends.push();
        Map<String, Object> m = new HashMap<>();
        m.put("IDUser", IdUser);
        m.put("IDFriend", IDFriend);
        m.put("STT", STT);
        newFriends.setValue(m).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
            }
        });

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ListLoiMoiDaNhan.clear();
                for(DataSnapshot sn : snapshot.getChildren()){
                    String id = sn.getKey();
                    String user = sn.child("IDUser").getValue(String.class);
                    String friend = sn.child("IDFriend").getValue(String.class);
                    if (user.equals(IDFriend) && friend.equals(IdUser)){
                        FriendRequest.child(id).removeValue();
                    }
                }
                adapter.notifyDataSetChanged();
                FriendRequest.removeEventListener(this);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle error
            }
        };
        FriendRequest.addValueEventListener(valueEventListener);
    }
    private void DanhSachDaNhan(String ID){
        FriendRequest.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ListLoiMoiDaNhan.clear();
                for (DataSnapshot sn : snapshot.getChildren()){
                    String IDUser = sn.child("IDUser").getValue(String.class);
                    String IDFriends = sn.child("IDFriend").getValue(String.class);
                    if(ID.equals(IDFriends)){
                        Account.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot data : snapshot.getChildren()){
                                    String id = data.getKey();
                                    String Sdt = data.child("SoDienThoai").getValue(String.class);
                                    String ten = data.child("TenNguoiDung").getValue(String.class);

                                    if (IDUser.equals(id)){
                                        DanhSachBanBe ds = new DanhSachBanBe(id,Sdt,ten);
                                        ListLoiMoiDaNhan.add(ds);
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

    private void showToast(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }
}
