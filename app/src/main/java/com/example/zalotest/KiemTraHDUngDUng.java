package com.example.zalotest;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class KiemTraHDUngDUng implements LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onEnterForeground() {
        // Ứng dụng vào foreground
        Log.d("AppLifecycleObserver", "App vào foreground");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onEnterBackground() {
        // Ứng dụng vào background hoặc bị tắt
        Log.d("AppLifecycleObserver", "App ra background");
    }
}
