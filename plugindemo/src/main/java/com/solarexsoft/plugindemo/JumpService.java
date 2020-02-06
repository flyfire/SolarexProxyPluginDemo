package com.solarexsoft.plugindemo;

import android.content.Intent;
import android.util.Log;

import com.solarexsoft.pluginstandard.BasePluginService;

import java.util.concurrent.Executors;

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 17:17/2020-02-06
 *    Desc:
 * </pre>
 */

public class JumpService extends BasePluginService {
    private static final String TAG = "JumpService";
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (i < 1000000) {
                    if (i % 1000 == 0) {
                        Log.d(TAG, String.valueOf(i));
                    }
                    i++;
                }
            }
        });
        return super.onStartCommand(intent, flags, startId);
    }
}
