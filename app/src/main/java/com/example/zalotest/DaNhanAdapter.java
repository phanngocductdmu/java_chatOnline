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

import java.util.List;

public class DaNhanAdapter extends BaseAdapter {
    private LMDaNhanFragment context;
    private int layout;
    private List<DanhSachBanBe> LoiMoiDaNhan;

    public DaNhanAdapter(LMDaNhanFragment context, int layout, List<DanhSachBanBe> loiMoiDaNhan) {
        this.context = context;
        this.layout = layout;
        this.LoiMoiDaNhan = loiMoiDaNhan;
    }

    private class ViewHolder {
        TextView txtNguoiDung;
        Button btnXacNhan;
        ImageView imgAVTKetBan;
        ViewHolder(View item) {
            imgAVTKetBan = item.findViewById(R.id.imgAVTKetBan);
            txtNguoiDung = item.findViewById(R.id.txtNguoiDung);
            btnXacNhan = item.findViewById(R.id.btnXacNhan);
        }
    }

    @Override
    public int getCount() {
        return LoiMoiDaNhan.size();
    }

    @Override
    public Object getItem(int position) {
        return LoiMoiDaNhan.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context.getContext());
            convertView = layoutInflater.inflate(layout, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        DanhSachBanBe bb = LoiMoiDaNhan.get(position);
        holder.txtNguoiDung.setText(bb.getTenNguoiDung());
        final View finalConvertView = convertView;
        holder.btnXacNhan.setOnClickListener(new View.OnClickListener() {
            SharedPreferences sharedPreferences = finalConvertView.getContext().getSharedPreferences("IdUser", Context.MODE_PRIVATE);
            String IdUser = sharedPreferences.getString("IdUser", "");
            @Override
            public void onClick(View v) {
                String IDFriend = String.valueOf(bb.getIdTaiKhoanChinh());
                context.getMaxSTT(IdUser, IDFriend);
            }
        });
        return convertView;
    }
}
