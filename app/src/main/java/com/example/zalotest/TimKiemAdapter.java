package com.example.zalotest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.List;

public class TimKiemAdapter extends BaseAdapter {
    private TimKiemBanBe context;
    private int layout;
    private List<DanhSachBanBe> TimKiemList;
    private static final int REQUEST_CODE_NOTIFICATION_PERMISSION = 1;

    public TimKiemAdapter(TimKiemBanBe context, int layout, List<DanhSachBanBe> timKiemList) {
        this.context = context;
        this.layout = layout;
        TimKiemList = timKiemList;
    }

    @Override
    public int getCount() {
        return TimKiemList.size();
    }

    @Override
    public Object getItem(int position) {
        return TimKiemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {
        TextView txtTenKetBan;
        Button btnKetBan;

        ViewHolder(View itemView) {
            txtTenKetBan = itemView.findViewById(R.id.txtTenKetBan);
            btnKetBan = itemView.findViewById(R.id.btnKetBan);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(layout, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        DanhSachBanBe danhSachBanBe = TimKiemList.get(position);


        holder.txtTenKetBan.setText(danhSachBanBe.getTenNguoiDung());

        SharedPreferences s = context.getSharedPreferences("IdUser", Context.MODE_PRIVATE);
        String IdUser = s.getString("IdUser", "");
        String IDBanBe = danhSachBanBe.getIdTaiKhoanChinh();
        holder.btnKetBan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.btnKetBan.getText().equals("Kết bạn")){
                    context.InsertKetBan(IdUser, IDBanBe);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        // Kiểm tra quyền thông báo
                        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                            // Yêu cầu quyền thông báo
                            ActivityCompat.requestPermissions(context, new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, REQUEST_CODE_NOTIFICATION_PERMISSION);
                        } else {
                            // Quyền đã được cấp, bạn có thể gửi thông báo
                            //context.sendNotification();
                        }
                    } else {
                        // Đối với các phiên bản Android cũ hơn, quyền thông báo không cần yêu cầu
                        //context.sendNotification();
                    }
                    holder.btnKetBan.setText("Hủy");
                }else if(holder.btnKetBan.getText().equals("Hủy")){
                    LMDaGuiFragment dg = new LMDaGuiFragment();
                    dg.HuyLoiMoi(IDBanBe, IdUser);
                    holder.btnKetBan.setText("Kết bạn");
                }
            }
        });
        return convertView;
    }
}
