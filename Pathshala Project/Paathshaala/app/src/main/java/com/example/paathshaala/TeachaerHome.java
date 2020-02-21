package com.example.paathshaala;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class TeachaerHome extends AppCompatActivity {
    private ViewPager viewPager;
    private DemoAdapterTeacher demoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachaer_home);
        //===================================
        if(!isConnected()) {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Internet Connectivity")
                    .setMessage("Please Check your Internet Conncetion")
                    .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    })

                    .show();
        }
        else
        {
            //  Toast.makeText(MainActivity.this,"Welcome",Toast.LENGTH_LONG).show();

        }
        //============================
        viewPager = findViewById(R.id.pager);
        demoAdapter = new DemoAdapterTeacher(getSupportFragmentManager());
        viewPager.setAdapter(demoAdapter);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                viewPager.post(new Runnable() {

                    @Override
                    public void run() {
                        viewPager.setCurrentItem((viewPager.getCurrentItem() + 1) % 4);
                    }
                });
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 3000, 3000);

    }
    public void onStudent(View view)
    {
        Intent das=new Intent(this,StudentHome.class);
        startActivity(das);
    }
    public void onTeacher(View view)
    {
        Intent das=new Intent(this,TeachaerHome.class);
        startActivity(das);
    }


    public void onProfile(View view)
    {
        Intent das=new Intent(this,Profile.class);
        startActivity(das);
    }
    public void onDashboard(View view)
    {
        Intent das=new Intent(this,Dashboard.class);
        startActivity(das);
    }


   public void onPostCourse(View view){
       //===================================
       if(!isConnected()) {
           new AlertDialog.Builder(this)
                   .setIcon(android.R.drawable.ic_dialog_alert)
                   .setTitle("Internet Connectivity")
                   .setMessage("Please Check your Internet Conncetion")
                   .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialogInterface, int i) {
                           finish();
                       }
                   })

                   .show();
       }
       else
       {
           //  Toast.makeText(MainActivity.this,"Welcome",Toast.LENGTH_LONG).show();

       }
       //============================

       Intent das=new Intent(this,PostCourse.class);
       startActivity(das);

   }

   public void onmycourses(View view){
       //===================================
       if(!isConnected()) {
           new AlertDialog.Builder(this)
                   .setIcon(android.R.drawable.ic_dialog_alert)
                   .setTitle("Internet Connectivity")
                   .setMessage("Please Check your Internet Conncetion")
                   .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialogInterface, int i) {
                           finish();
                       }
                   })

                   .show();
       }
       else
       {
           //  Toast.makeText(MainActivity.this,"Welcome",Toast.LENGTH_LONG).show();

       }
       //============================

       Intent das=new Intent(this,MyCourses.class);
       das.putExtra("fromteacherhome","0");
       startActivity(das);



   }
public void ondeletencomplete(View view)
{
    //===================================
    if(!isConnected()) {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Internet Connectivity")
                .setMessage("Please Check your Internet Conncetion")
                .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })

                .show();
    }
    else
    {
        //  Toast.makeText(MainActivity.this,"Welcome",Toast.LENGTH_LONG).show();

    }
    //============================
    Intent das=new Intent(this,showCoursePublished.class);
    startActivity(das);

}
public void oneditcourse(View view)
{
    //===================================
    if(!isConnected()) {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Internet Connectivity")
                .setMessage("Please Check your Internet Conncetion")
                .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })

                .show();
    }
    else
    {
        //  Toast.makeText(MainActivity.this,"Welcome",Toast.LENGTH_LONG).show();

    }
    //============================
    Intent das=new Intent(this,MyCourses.class);
    das.putExtra("fromteacherhome","1");
    startActivity(das);


}

    public void onBackPressed()
    {
        Intent startMain = new Intent(this,Home.class);
        //startMain.addCategory(Intent.CATEGORY_HOME);
        //startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }
    public void onTeam(View view)
    {
        Intent it=new Intent(this,BestTeam.class);
        startActivity(it);
    }
    public boolean isConnected()
    {
        ConnectivityManager connectivityManager= (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo= connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
