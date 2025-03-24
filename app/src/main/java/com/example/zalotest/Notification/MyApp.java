package com.example.zalotest.Notification;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

public class MyApp extends Application {

    private static final String TAG = "MyApp";

    @Override
    public void onCreate() {
        super.onCreate();

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            private int numStarted = 0;

            @Override
            public void onActivityStarted(Activity activity) {
                if (numStarted == 0) {
                    // App entered foreground
                    Log.d(TAG, "App in foreground" + numStarted);
                }
                numStarted++;
            }

            @Override
            public void onActivityStopped(Activity activity) {
                numStarted--;
                if (numStarted == 0) {
                    // App entered background
                    Log.d(TAG, "App in background" + numStarted);
                }
            }

            // Other lifecycle methods can be overridden as needed
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {}
            @Override
            public void onActivityResumed(Activity activity) {}
            @Override
            public void onActivityPaused(Activity activity) {}
            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {}
            @Override
            public void onActivityDestroyed(Activity activity) {}
        });
    }
}
