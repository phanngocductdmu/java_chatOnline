package com.example.zalotest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class DanhSachBBadapter extends BaseAdapter {
    private TinNhanFragment context;
    private int layout;
    private List<DanhSachBanBe> danhSachBanBeList;

    private FirebaseDatabase mdata = FirebaseDatabase.getInstance();
    private DatabaseReference Messenger = mdata.getReference("Messenger");
    private DatabaseReference Friends = mdata.getReference("Friends");

    public DanhSachBBadapter(TinNhanFragment context, int layout, List<DanhSachBanBe> danhSachBanBeList) {
        this.context = context;
        this.layout = layout;
        this.danhSachBanBeList = danhSachBanBeList;
    }

    @Override
    public int getCount() {
        return danhSachBanBeList.size();
    }

    @Override
    public Object getItem(int position) {
        return danhSachBanBeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {
        TextView txtHoTen, txtTinNhan;
        ImageView imgTNMoi;
        ConstraintLayout lyoTinNhan;
        ViewHolder(View itemView) {
            txtHoTen = itemView.findViewById(R.id.txtTenBanBe);
            lyoTinNhan = itemView.findViewById(R.id.lyoTinNhan);
            txtTinNhan = itemView.findViewById(R.id.txtTinNhan);
            imgTNMoi = itemView.findViewById(R.id.imgTNMoi);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context.requireContext());
            convertView = inflater.inflate(layout, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        DanhSachBanBe bb = danhSachBanBeList.get(position);

        holder.txtHoTen.setText(bb.getTenNguoiDung());

        SharedPreferences s = context.requireContext().getSharedPreferences("IdUser", Context.MODE_PRIVATE);
        String IdUser = s.getString("IdUser", "");
        String dannhan = "Đã nhận";
        String daxem = "Đã xem";
        Messenger.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot da : snapshot.getChildren()){
                    String IDPhong = da.child("IDPhongChats").getValue(String.class);
                    String IDUser = da.child("IDUser").getValue(String.class);
                    String TrangThai = da.child("TrangThai").getValue(String.class);
                    if(bb.getIDPhongChat().equals(IDPhong) && !IdUser.equals(IDUser) && dannhan.equals(TrangThai)){
                        holder.imgTNMoi.setVisibility(View.VISIBLE);
                    } else if (bb.getIDPhongChat().equals(IDPhong) && !IdUser.equals(IDUser) && daxem.equals(TrangThai)) {
                        holder.imgTNMoi.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        holder.lyoTinNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.requireContext(), TinNhan.class);
                intent.putExtra("dataTinNhan", bb);
                context.inChat(bb.getIDPhongChat());
                context.DoiTrangThai(bb.getIDPhongChat());
                context.startActivity(intent);
            }
        });
        return convertView;
    }
    private void showToast(String a){
        Toast.makeText(context.requireContext(), a, Toast.LENGTH_SHORT).show();
    }
}
