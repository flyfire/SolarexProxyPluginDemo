package com.solarexsoft.pluginstandard;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 16:19/2020-02-06
 *    Desc:
 * </pre>
 */

public interface ActivityStandard {
    public void attach(Activity proxyActivity);
    public void onCreate(Bundle savedInstanceState);
    public void onStart();
    public void onResume();
    public void onPause();
    public void onStop();
    public void onDestroy();
    public void onSaveInstanceState(Bundle outState);
    public boolean onTouchEvent(MotionEvent event);
    public void onBackPressed();
    String KEY_ACTIVITY_CLASSNAME = "key_activity_class_name";
}
