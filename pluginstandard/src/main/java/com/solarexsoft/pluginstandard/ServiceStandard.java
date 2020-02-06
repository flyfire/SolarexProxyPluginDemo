package com.solarexsoft.pluginstandard;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.IBinder;

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 16:23/2020-02-06
 *    Desc:
 * </pre>
 */

public interface ServiceStandard {
    public void attach(Service proxyService);
    public void onCreate();
    public void onStart(Intent intent, int startId);
    public int onStartCommand(Intent intent, int flags, int startId);
    public void onDestroy();
    public void onConfigurationChanged(Configuration configuration);
    public void onLowMemory();
    public void onTrimMemory(int level);
    public IBinder onBind(Intent intent);
    public boolean onUnbind(Intent intent);
    public void onRebind(Intent intent);
    public void onTaskRemoved(Intent intent);
    String KEY_SERVICE_CLASS_NAME = "key_service_class_name";
}
