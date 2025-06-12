package com.example.laucher;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.media.tv.interactive.AppLinkInfo;
import android.os.Bundle;
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
        EdgeToEdge.enable(this);
        listView = findViewById(R.id.listView);

        pm=getPackageManager();
        List<ApplicationInfo> applicationInfos= pm.getInstalledApplications(PackageManager.MATCH_ALL);


        AppAdapter adapter = new AppAdapter(this, R.layout.item_app, applicationInfos);
        List<ApplicationInfo> appsFiltrados=new ArrayList<>();
        for (ApplicationInfo app: applicationInfos){
            if((app.flags & ApplicationInfo.FLAG_SYSTEM)==0){
                appsFiltrados.add(app);
            }
        }

        listView.setAdapter(adapter);

    }
}