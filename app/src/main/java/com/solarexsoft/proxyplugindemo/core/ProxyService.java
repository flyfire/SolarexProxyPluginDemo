package com.solarexsoft.proxyplugindemo.core;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.solarexsoft.pluginstandard.ServiceStandard;

import static com.solarexsoft.pluginstandard.ServiceStandard.KEY_SERVICE_CLASS_NAME;

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 17:57/2020-02-06
 *    Desc:
 * </pre>
 */

public class ProxyService extends Service {
    ServiceStandard proxyee;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        if (proxyee == null) {
            init(intent);
        }
        return proxyee.onBind(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (proxyee == null) {
            init(intent);
        }
        int ret = proxyee.onStartCommand(intent, flags, startId);
        return ret;
    }

    private void init(Intent intent) {
        String actualServiceName = intent.getStringExtra(KEY_SERVICE_CLASS_NAME);
        try {
            ClassLoader classLoader = PluginManager.getInstance().getPluginClassLoader();
            if (classLoader == null) {
                PluginManager.getInstance().attachBaseContext(this.getApplicationContext());
                PluginManager.getInstance().loadPath();
            }
            Class<?> clz = classLoader.loadClass(actualServiceName);
            Object instance = clz.newInstance();
            proxyee = (ServiceStandard) instance;
            proxyee.attach(this);
            proxyee.onCreate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        proxyee.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        proxyee.onLowMemory();
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        proxyee.onTrimMemory(level);
        super.onTrimMemory(level);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return proxyee.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        proxyee.onRebind(intent);
    }
}
