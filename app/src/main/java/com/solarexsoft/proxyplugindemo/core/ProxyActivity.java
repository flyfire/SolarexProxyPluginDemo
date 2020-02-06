package com.solarexsoft.proxyplugindemo.core;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.MotionEvent;

import androidx.annotation.Nullable;

import com.solarexsoft.pluginstandard.ActivityStandard;

import static com.solarexsoft.pluginstandard.ActivityStandard.KEY_ACTIVITY_CLASSNAME;
import static com.solarexsoft.pluginstandard.ServiceStandard.KEY_SERVICE_CLASS_NAME;

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 17:45/2020-02-06
 *    Desc:
 * </pre>
 */

public class ProxyActivity extends Activity {
    ActivityStandard proxyee;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String actualActivityClassName = getIntent().getStringExtra(KEY_ACTIVITY_CLASSNAME);
        try {
            Class<?> clz = PluginManager.getInstance().getPluginClassLoader().loadClass(actualActivityClassName);
            Object instance = clz.newInstance();
            proxyee = (ActivityStandard) instance;
            proxyee.attach(this);
            proxyee.onCreate(savedInstanceState);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startActivity(Intent intent) {
        Intent newIntent = new Intent(this, ProxyActivity.class);
        newIntent.putExtra(KEY_ACTIVITY_CLASSNAME, intent.getComponent().getClassName());
        super.startActivity(newIntent);
    }

    @Override
    public ComponentName startService(Intent service) {
        Intent newIntent = new Intent(this, ProxyService.class);
        newIntent.putExtra(KEY_SERVICE_CLASS_NAME, service.getComponent().getClass());
        return super.startService(newIntent);
    }

    @Override
    protected void onStart() {
        proxyee.onStart();
        super.onStart();
    }

    @Override
    protected void onResume() {
        proxyee.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        proxyee.onPause();
        super.onPause();
    }

    @Override
    protected void onStop() {
        proxyee.onStop();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        proxyee.onDestroy();
        super.onDestroy();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return proxyee.onTouchEvent(event);
    }

    @Override
    public ClassLoader getClassLoader() {
        return PluginManager.getInstance().getPluginClassLoader();
    }

    @Override
    public Resources getResources() {
        return PluginManager.getInstance().getPluginResources();
    }
}
