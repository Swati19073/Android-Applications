package com.example.mt19073_contactlist;

//https://www.youtube.com/watch?v=oBhgyiBVd3k
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabL;
    private ViewPager vPager;
    private ViewPagerAdapter vpAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabL=(TabLayout)findViewById(R.id.tab);
        vPager=(ViewPager)findViewById(R.id.view_pid);
        vpAdapter=new ViewPagerAdapter(getSupportFragmentManager());


        //Adding fragment here

        vpAdapter.InsertFrag(new CallFrag(),"");
        vpAdapter.InsertFrag(new ContactFrag(),"");
        vpAdapter.InsertFrag(new FavFrag(),"");

        vPager.setAdapter(vpAdapter);
        tabL.setupWithViewPager(vPager);
//        for (int i=0;i<tabL.getTabCount();i++)
//        {
//            TabLayout.Tab tab=tabL.getTabAt(i);
//
//            tab.setIcon(ICONS[i]);
//        }

        tabL.getTabAt(0).setIcon(R.drawable.call);
        tabL.getTabAt(1).setIcon(R.drawable.group);
        tabL.getTabAt(2).setIcon(R.drawable.fav);
        Log.d("msg","main activity");

    }


    private void askPermissions()
    {

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS},1);
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CALL_LOG},1);
        }
    }
}
