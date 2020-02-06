package com.solarexsoft.proxyplugindemo.core;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 17:23/2020-02-06
 *    Desc:
 * </pre>
 */

public class PluginManager {
    private static final PluginManager ourInstance = new PluginManager();

    public static PluginManager getInstance() {
        return ourInstance;
    }

    private PluginManager() {
    }

    private PackageInfo pluginPackageInfo;
    private Resources pluginResources;
    private Context hostContext;
    private DexClassLoader pluginClassLoader;

    public void attachBaseContext(Context context) {
        hostContext = context;
    }

    public void loadPath() {
        File pluginDir = hostContext.getDir("plugin", Context.MODE_PRIVATE);
        String pluginName = "plugin.apk";
        String pluginPath = new File(pluginDir, pluginName).getAbsolutePath();

        PackageManager packageManager = hostContext.getPackageManager();
        pluginPackageInfo = packageManager.getPackageArchiveInfo(pluginPath, PackageManager.GET_ACTIVITIES);

        File dexOutDir = hostContext.getDir("dexout", Context.MODE_PRIVATE);
        pluginClassLoader = new DexClassLoader(pluginPath, dexOutDir.getAbsolutePath(), null, hostContext.getClassLoader());

        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPath = AssetManager.class.getMethod("addAssetPath", String.class);
            addAssetPath.invoke(assetManager, pluginPath);
            pluginResources = new Resources(assetManager, hostContext.getResources().getDisplayMetrics(), hostContext.getResources().getConfiguration());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        // try to fix, not work, :)
        /*
        Resources resources = hostContext.getResources();
        try {
            Field assetsField = Resources.class.getDeclaredField("mAssets");
            assetsField.setAccessible(true);
            AssetManager assetManager = (AssetManager) assetsField.get(resources);
            Method addAssetPath = AssetManager.class.getMethod("addAssetPath", String.class);
            addAssetPath.invoke(assetManager, pluginPath);
            pluginResources = resources;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
         */
    }

    public PackageInfo getPluginPackageInfo() {
        return pluginPackageInfo;
    }

    public Resources getPluginResources() {
        return pluginResources;
    }

    public DexClassLoader getPluginClassLoader() {
        return pluginClassLoader;
    }
}
