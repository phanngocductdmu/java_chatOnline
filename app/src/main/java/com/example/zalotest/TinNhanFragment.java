package com.example.zalotest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import com.example.zalotest.MenuTrangChinh.*;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TinNhanFragment extends ListFragment {

    private ImageButton imgSearch, imgQr, imgChucNang, imgPhanLoai;
    private TextView txtSearch;
    private ArrayList<DanhSachBanBe> arrayList;
    private DanhSachBBadapter adapter;
    private FirebaseDatabase mdata = FirebaseDatabase.getInstance();
    private DatabaseReference Chats = mdata.getReference("Chats");
    private DatabaseReference Friends = mdata.getReference("Friends");
    private DatabaseReference Account = mdata.getReference("Account");
    private DatabaseReference Messenger = mdata.getReference("Messenger");
    private DatabaseReference inChats = mdata.getReference("inChats");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tin_nhan, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        arrayList = new ArrayList<>();
        adapter = new DanhSachBBadapter(TinNhanFragment.this, R.layout.danh_sach_ban_be, arrayList);
        setListAdapter(adapter);
        SharedPreferences s = requireContext().getSharedPreferences("IdUser", Context.MODE_PRIVATE);
        String IdUser = s.getString("IdUser", "");
        TrainChat(IdUser);
        Collections.sort(arrayList, new Comparator<DanhSachBanBe>() {
            @Override
            public int compare(DanhSachBanBe o1, DanhSachBanBe o2) {
                return Integer.compare(o2.getSTT(), o1.getSTT());
            }
        });
    }
    private void TrainChat(String idUser) {
        Friends.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                for(DataSnapshot da : snapshot.getChildren()){
                    String IDPhong = da.getKey();
                    String IDUser = da.child("IDUser").getValue(String.class);
                    String IDFriend = da.child("IDFriend").getValue(String.class);
                    int STT = da.child("STT").getValue(Integer.class);
                    if (idUser.equals(IDUser) || idUser.equals(IDFriend)){
                        Account.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for(DataSnapshot data : snapshot.getChildren()){
                                    String IDUsername = data.getKey();
                                    String Ten = data.child("TenNguoiDung").getValue(String.class);
                                    if(idUser.equals(IDFriend)){
                                        if(IDUser.equals(IDUsername)){
                                            DanhSachBanBe bb = new DanhSachBanBe(IDUser, IDPhong, Ten, STT);
                                            arrayList.add(bb);
                                        }
                                    }else {
                                        if(IDFriend.equals(IDUsername)){
                                            DanhSachBanBe bb = new DanhSachBanBe(IDFriend, IDPhong, Ten, STT);
                                            arrayList.add(bb);
                                        }
                                    }
                                }
                                Collections.sort(arrayList, new Comparator<DanhSachBanBe>() {
                                    @Override
                                    public int compare(DanhSachBanBe o1, DanhSachBanBe o2) {
                                        return Integer.compare(o2.getSTT(), o1.getSTT());
                                    }
                                });
                                adapter.notifyDataSetChanged();

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

    public void inChat(String idphong){
        SharedPreferences s = requireContext().getSharedPreferences("IdUser", Context.MODE_PRIVATE);
        String IdUser = s.getString("IdUser", "");

        DatabaseReference newInChats = inChats.push();
        Map<String, Object> m = new HashMap<>();
        m.put("IDPhongChat",idphong);
        m.put("IDUser", IdUser);
        newInChats.setValue(m).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

            }
        });
    }

    public void DoiTrangThai(String idPhong) {
        SharedPreferences s = requireContext().getSharedPreferences("IdUser", Context.MODE_PRIVATE);
        String IdUser = s.getString("IdUser", "");
        Messenger.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Map<String, Object> updates = new HashMap<>();
                for (DataSnapshot da : snapshot.getChildren()) {
                    String key = da.getKey();
                    String IDPhong = da.child("IDPhongChats").getValue(String.class);
                    String IDUser = da.child("IDUser").getValue(String.class);
                    if (idPhong.equals(IDPhong) && !IdUser.equals(IDUser)) {
                        if (key != null) {
                            updates.put(key + "/TrangThai", "Đã xem");
                        }
                    }
                }

                if (!updates.isEmpty()) {
                    Messenger.updateChildren(updates);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }


    public void Menu(View view) {
        PopupMenu popupMenu = new PopupMenu(requireContext(), view);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.menu_item_ThemBan) {
                    // Xử lý khi người dùng nhấn vào menu "Thêm tài khoản"
                    startActivity(new Intent(requireContext(), ThemBan.class));
                    return true;
                } else if (id == R.id.menu_item_TaoNhom) {
                    startActivity(new Intent(requireContext(), TaoNhom.class));
                    return true;
                } else if (id == R.id.menu_item_CloudCuaToi) {
                    startActivity(new Intent(requireContext(), CloudCuaToi.class));
                    return true;
                } else if (id == R.id.menu_item_LichZalotest) {
                    startActivity(new Intent(requireContext(), ListFragment.class));
                    return true;
                } else if (id == R.id.menu_item_TaoCuocGoiNhom) {
                    startActivity(new Intent(requireContext(), TaoCuocGoiNhom.class));
                    return true;
                } else if (id == R.id.menu_item_ThietBiDangNhap) {
                    startActivity(new Intent(requireContext(), ThietBiDangNhap.class));
                    return true;
                }
                return false;
            }
        });
        popupMenu.inflate(R.menu.menu_tinnhan_trangchinh);
        popupMenu.show();
    }

    public void PhanLoai(View view){
        PopupMenu pop = new PopupMenu(requireContext(), view);
        pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if(id == R.id.idChuaDoc){
                    showToast("click Chua Doc");
                    return true;
                }else if(id == R.id.idDaNhacDenToi){
                    showToast("click da nhac den toi");
                    return true;
                } else if (id == R.id.idThePhanLoai) {
                    showToast("click the phan loai");
                    return true;
                }
                return false;
            }
        });
        pop.inflate(R.menu.menu_phan_loai);
        pop.show();
    }

    private void initView(View view) {
        imgSearch = view.findViewById(R.id.imgBack);
        imgQr = view.findViewById(R.id.imgMaQR);
        imgChucNang = view.findViewById(R.id.imgPlus);
        imgPhanLoai = view.findViewById(R.id.imgPhanLoai);
        txtSearch = view.findViewById(R.id.txtSearch);

        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireContext(), TimKiemBanBe.class));
            }
        });
        txtSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireContext(), TimKiemBanBe.class));
            }
        });

        imgChucNang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Menu(v);
            }
        });

        imgPhanLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhanLoai(v);
            }
        });

        imgQr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireContext(), QuetQR.class));
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }
}
