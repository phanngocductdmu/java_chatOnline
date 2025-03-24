package com.example.zalotest.Notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.example.zalotest.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;


public class LayToken extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseService";
    private FirebaseDatabase mdata = FirebaseDatabase.getInstance();
    private DatabaseReference Account = mdata.getReference("Account");

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        Log.d(TAG, "New token: " + token);
        saveTokenToDatabase(token);
        FirebaseMessaging.getInstance().setAutoInitEnabled(true);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);
        String title = message.getNotification().getTitle();
        String content = message.getNotification().getBody();
        String data = new Gson().toJson(message.getData());
        ShowNotification(this, title, content);
        Log.d(TAG, data);
    }

    private void ShowNotification(Context context, String title, String body){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "edtm.dev.channel");
        builder.setSmallIcon(R.drawable.avtzalo);
        builder.setContentTitle(title);
        builder.setContentText(body);
        builder.setPriority(NotificationCompat.PRIORITY_MAX);

        //style
        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        bigTextStyle.bigText(title);
        bigTextStyle.setBigContentTitle(title);
        bigTextStyle.setSummaryText(title);

        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            String chenalId = "edmt.dev.channel.id";
            NotificationChannel channel = new NotificationChannel(chenalId, "EDMTDEV Chennal", NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(channel);
            builder.setChannelId(chenalId);
        }
        manager.notify(1, builder.build());
    }

    private void saveTokenToDatabase(String token) {
        SharedPreferences sharedPreferences = getSharedPreferences("IdUser", Context.MODE_PRIVATE);
        String IdUser = sharedPreferences.getString("IdUser", "");
        if (token != null) {
            if (!IdUser.isEmpty()) {
                Account.child(IdUser).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            // Create a map to hold the new token
                            Map<String, Object> updates = new HashMap<>();
                            updates.put("Token", token);

                            // Update the user's token
                            Account.child(IdUser).updateChildren(updates)
                                    .addOnSuccessListener(aVoid -> Log.d(TAG, "Token updated successfully."))
                                    .addOnFailureListener(e -> Log.e(TAG, "Failed to update token: ", e));
                        } else {
                            Log.e(TAG, "User ID not found in database.");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e(TAG, "Database error: ", error.toException());
                    }
                });
            } else {
                Log.e(TAG, "User ID is empty.");
            }
        }
    }
}
