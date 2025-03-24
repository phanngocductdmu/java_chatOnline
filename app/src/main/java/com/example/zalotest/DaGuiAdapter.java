package com.example.zalotest;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

public class DaGuiAdapter extends BaseAdapter {
    private LMDaGuiFragment context;
    private int layout;
    private List<DanhSachBanBe> LoiMoiDaGui;


    public DaGuiAdapter(LMDaGuiFragment context, int layout, List<DanhSachBanBe> loiMoiDaGui) {
        this.context = context;
        this.layout = layout;
        LoiMoiDaGui = loiMoiDaGui;
    }

    private class ViewHolder{
        TextView txtHuyNguoiDung;
        Button btnHuy;
        ImageView imgAVTKetBan;
        ViewHolder(View v){
            imgAVTKetBan = v.findViewById(R.id.imgAVTKetBan);
            txtHuyNguoiDung = v.findViewById(R.id.txtHuyNguoiDung);
            btnHuy = v.findViewById(R.id.btnHuy);
        }
    }
    @Override
    public int getCount() {
        return LoiMoiDaGui.size();
    }

    @Override
    public Object getItem(int position) {
        return LoiMoiDaGui.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            LayoutInflater layoutInflater = LayoutInflater.from(context.getContext());
            convertView = layoutInflater.inflate(layout, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        DanhSachBanBe bb = LoiMoiDaGui.get(position);
        holder.txtHuyNguoiDung.setText(bb.getTenNguoiDung());
        final View finalConvertView = convertView;

        holder.btnHuy.setOnClickListener(new View.OnClickListener() {
            SharedPreferences sharedPreferences = finalConvertView.getContext().getSharedPreferences("IdUser", Context.MODE_PRIVATE);
            String IdUser = sharedPreferences.getString("IdUser", "");
            @Override
            public void onClick(View v) {
                String IDFriend = String.valueOf(bb.getIdTaiKhoanChinh());
                context.HuyLoiMoi(IDFriend, IdUser);
                Toast.makeText(context.getContext(), "Hủy "+bb.getTenNguoiDung()+" Thành công", Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }
}
