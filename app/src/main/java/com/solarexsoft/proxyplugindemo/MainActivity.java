package com.solarexsoft.proxyplugindemo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.solarexsoft.proxyplugindemo.core.PluginManager;
import com.solarexsoft.proxyplugindemo.core.ProxyActivity;

import static com.solarexsoft.pluginstandard.ActivityStandard.KEY_ACTIVITY_CLASSNAME;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.load).setOnClickListener(this);
        findViewById(R.id.jump).setOnClickListener(this);
        PluginManager.getInstance().attachBaseContext(this.getApplicationContext());
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.load) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                justDoIt();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
            }
        } else if (id == R.id.jump){
            Intent intent = new Intent(this, ProxyActivity.class);
            intent.putExtra(KEY_ACTIVITY_CLASSNAME, "com.solarexsoft.plugindemo.EntranceActivity");
            startActivity(intent);
        }
    }

    private void copySdcardApkFile() {
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 100 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            justDoIt();
        }
    }

    private void justDoIt() {
        copySdcardApkFile();
        PluginManager.getInstance().loadPath();
    }
}
