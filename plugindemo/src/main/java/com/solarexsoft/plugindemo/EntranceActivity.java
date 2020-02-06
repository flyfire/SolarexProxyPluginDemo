package com.solarexsoft.plugindemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.solarexsoft.pluginstandard.BasePluginActivity;

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 17:04/2020-02-06
 *    Desc:
 * </pre>
 */

public class EntranceActivity extends BasePluginActivity implements View.OnClickListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrance);
        findViewById(R.id.startActivity).setOnClickListener(this);
        findViewById(R.id.startService).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.startActivity) {
            Intent intent = new Intent(that, JumpActivity.class);
            startActivity(intent);
        } else if (id == R.id.startService) {
            Intent intent = new Intent(that, JumpService.class);
            startService(intent);
        }
    }
}
