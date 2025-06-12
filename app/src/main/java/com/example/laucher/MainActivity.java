package com.example.laucher;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.media.tv.interactive.AppLinkInfo;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    PackageManager pm;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pm=getPackageManager();
        List<ApplicationInfo> applicationInfos= new ArrayList<>();

        //outraabordagem
        Intent i = new Intent( Intent.ACTION_MAIN);
        i.addCategory(Intent.CATEGORY_LAUNCHER);

        List< ResolveInfo> resolveInfosList = pm.queryIntentActivities(i,0);
        applicationInfos.clear();
        resolveInfosList.forEach(resolveInfo -> {
            applicationInfos.add(resolveInfo.activityInfo.applicationInfo);
        });
        AppAdapter adapter = new AppAdapter( this, R.layout.item_app,applicationInfos);
        listView.setAdapter(adapter);

        listView.setOnClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                ApplicationInfo app = (ApplicationInfo) adapterView.getItemAtPosition(i);
                Intent laucherIntent = pm.getLaunchIntentForPackage(app.packageName);
                startActivity(laucherIntent);
            }
        });
    }
}