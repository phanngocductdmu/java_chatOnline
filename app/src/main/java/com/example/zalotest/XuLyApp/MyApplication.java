package com.example.zalotest.XuLyApp;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyApplication extends Application {
    private FirebaseDatabase mdata = FirebaseDatabase.getInstance();
    private DatabaseReference inChats = mdata.getReference("inChats");
    private ValueEventListener inChatsListener;

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {

            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
                // Gọi khi Activity được tạo ra
            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {
                // Gọi khi Activity bắt đầu được hiển thị ra màn hình
            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {
                // Gọi khi Activity trở lại trạng thái hoạt động (foreground)

                SharedPreferences s = getSharedPreferences("IdUser", Context.MODE_PRIVATE);
                String IdUser = s.getString("IdUser", "");

                inChatsListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot da : snapshot.getChildren()) {
                            String iduser = da.child("IDUser").getValue(String.class);
                            if (IdUser.equals(iduser)) {
                                da.getRef().removeValue();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Handle errors if needed
                    }
                };

                inChats.addValueEventListener(inChatsListener);
            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {
                // Gọi khi Activity chuyển sang trạng thái không hoạt động (background)
                if (inChatsListener != null) {
                    inChats.removeEventListener(inChatsListener);
                    inChatsListener = null;
                }
            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {
                // Gọi khi Activity không còn hiển thị ra màn hình nữa
            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {
                // Gọi khi trạng thái của Activity được lưu lại
            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {
                // Gọi khi Activity bị hủy
            }
        });
    }
}
