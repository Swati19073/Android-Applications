package com.example.paathshaala;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Home extends AppCompatActivity {
    private ViewPager viewPager;
    private DemoAdapterhome demoAdapter;
    boolean doubleBackToExitPressedOnce = false;
    String currentDate;
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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
            //  Toast.makeText(this,"Welcome",Toast.LENGTH_LONG).show();

        }
        //============================
        viewPager=findViewById(R.id.pager);
        demoAdapter=new DemoAdapterhome(getSupportFragmentManager());
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


        SharedPreferences sp=getSharedPreferences("sp1",MODE_PRIVATE);
        final String s1 =sp.getString("email","");



        String semail ="";
        for(int i=0;i<s1.length();i++)
        {
            if(s1.charAt(i)!='@')
            {
                semail=semail+s1.charAt(i);
            }
            else
            {
                break;
            }
        }


final String temp=semail;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref1 = database.getReference().child("Studentinfo");

        try {
            Query q = ref1.orderByKey();
            // For counting Users
            q.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    int flag=0;

                    for (DataSnapshot sn : dataSnapshot.getChildren()) {


                        if(sn.getKey().toString().equals(temp))
                        {
                            flag=1;

                        }


                    }
                onforwardtouinfo(flag);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }


                public void onCancelled(FirebaseError firebaseError) {

                }
            });


        }catch(Exception ex){}










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
    public void onforwardtouinfo(int flag){

        if(flag==0)
        {
            SharedPreferences sp=getSharedPreferences("sp1",MODE_PRIVATE);
            final String s1 =sp.getString("email","");
            Intent it=new Intent(this,Signup_Userinfo.class);
            it.putExtra("email",s1);
            startActivity(it);

        }

    }


    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            //super.onBackPressed();
            //return;
            finishAffinity();
            finish();
        }

        this.doubleBackToExitPressedOnce = true;
        if(count==0) {
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
            count=1;
        }
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
                count=0;
            }
        }, 2000);
    }
    public void onglobalchat(View view){
        FirebaseDatabase database = FirebaseDatabase.getInstance();



        SharedPreferences sp=getSharedPreferences("sp1",MODE_PRIVATE);
        final String s1 =sp.getString("email","");



        String semail ="";
        for(int i=0;i<s1.length();i++)
        {
            if(s1.charAt(i)!='@')
            {
                semail=semail+s1.charAt(i);
            }
            else
            {
                break;
            }
        }
      String  teachermail=semail;

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy HH:mm");
        Date date = new Date();
        currentDate=dateFormat.format(date);





        DatabaseReference ref1 = database.getReference().child("Global").child("Gchat");
       // ref1.setValue("`"+teachermail+"||"+"Welcome"+"||"+currentDate+"`");
        Intent it =new Intent(this,Global.class);
        startActivity(it);


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
