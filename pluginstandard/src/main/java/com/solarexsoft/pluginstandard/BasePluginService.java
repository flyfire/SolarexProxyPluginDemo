package com.solarexsoft.pluginstandard;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 16:55/2020-02-06
 *    Desc:
 * </pre>
 */

public class BasePluginService extends Service implements ServiceStandard {

    protected Service that;
    @Override
    public void attach(Service proxyService) {
        that = proxyService;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
