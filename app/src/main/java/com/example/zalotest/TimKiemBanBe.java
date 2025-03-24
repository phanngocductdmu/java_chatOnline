package com.example.zalotest;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TimKiemBanBe extends AppCompatActivity {
    private ImageButton imgQuayLai, imgQR;
    private EditText edtTiemKiemBanBe;
    private ListView lstTimKiem;
    private ArrayList<DanhSachBanBe> TimKiemBanBeArrayList;
    private TimKiemAdapter adapter;
    private FirebaseDatabase mdata = FirebaseDatabase.getInstance();
    private DatabaseReference accountRef = mdata.getReference("Account");
    private DatabaseReference FriendRequest = mdata.getReference("FriendRequest");
    private static final int REQUEST_CODE_NOTIFICATION_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tim_kiem_ban_be);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        AnhXa();
        Quaylai();
        MaQR();

        TimKiemBanBeArrayList = new ArrayList<>();
        adapter = new TimKiemAdapter(this, R.layout.ket_ban, TimKiemBanBeArrayList);
        String tim = edtTiemKiemBanBe.getText().toString().trim().toLowerCase();
        if(!tim.isEmpty()){
            lstTimKiem.setAdapter(adapter);
        }

        SharedPreferences s = getSharedPreferences("IdUser", Context.MODE_PRIVATE);
        String IdUser = s.getString("IdUser", "");
        TrainData(IdUser);

        edtTiemKiemBanBe.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                TimKiem();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void TimKiem() {
        String tim = edtTiemKiemBanBe.getText().toString().trim().toLowerCase();
        ArrayList<DanhSachBanBe> searchResults = new ArrayList<>();
        if (!tim.isEmpty()) {
            for (DanhSachBanBe danhSachBanBe : TimKiemBanBeArrayList) {
                if (danhSachBanBe.getTenNguoiDung().toLowerCase().contains(tim) || danhSachBanBe.getSoDienThoai().toLowerCase().contains(tim)) {
                    searchResults.add(danhSachBanBe);
                }
            }
        }
        adapter = new TimKiemAdapter(this, R.layout.ket_ban, searchResults);
        lstTimKiem.setAdapter(adapter);
    }
    private void TrainData(String IdUser) {
        accountRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                TimKiemBanBeArrayList.clear();
                for (DataSnapshot data : snapshot.getChildren()){
                    String ID = data.getKey();
                    String SDT = data.child("SoDienThoai").getValue(String.class);
                    String Ten = data.child("TenNguoiDung").getValue(String.class);
                    if(!IdUser.equals(ID)){
                        DanhSachBanBe ds = new DanhSachBanBe(ID, SDT, Ten);
                        TimKiemBanBeArrayList.add(ds);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    public void InsertKetBan(String IDUser, String IDBan){
        DatabaseReference newFriends = FriendRequest.push();
        Map<String, Object> fq = new HashMap<>();
        fq.put("IDUser",IDUser);
        fq.put("IDFriend", IDBan);
        newFriends.setValue(fq).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

            }
        });
    }

    private void MaQR() {
        imgQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Click QR");
            }
        });
    }

    private void Quaylai() {
        imgQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void AnhXa() {
        imgQuayLai = findViewById(R.id.imgQUAYLAI);
        imgQR = findViewById(R.id.imgMAQR);
        edtTiemKiemBanBe = findViewById(R.id.edtTIMKIEMBANBE);
        lstTimKiem = findViewById(R.id.lstTIMKIEMBANBE);
    }
    public void showToast(String mess){
        Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
    }
}