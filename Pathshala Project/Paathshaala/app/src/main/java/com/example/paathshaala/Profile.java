package com.example.paathshaala;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {
TextView name,mDOB,gender,mobileno,col,skill,exp,cp,ct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
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
        SharedPreferences sp=getSharedPreferences("sp1",MODE_PRIVATE);
        String s1=sp.getString("email","");
        String s2="";
        for(int i=0;i<s1.length();i++) {
            if (s1.charAt(i) != '@') {
                s2 = s2 + s1.charAt(i);
            } else {
                break;
            }

        }


        name=(TextView)findViewById(R.id.name);
        mDOB=(TextView)findViewById(R.id.dob);
        gender=(TextView)findViewById(R.id.gender);
        mobileno=(TextView)findViewById(R.id.phone);
        col=(TextView)findViewById(R.id.qualification);
        skill=(TextView)findViewById(R.id.skills);
        exp=(TextView)findViewById(R.id.experience);
        //cp=(TextView)findViewById(R.id.courses_pub);
        //ct=(TextView)findViewById(R.id.courses_taken);








        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref1 = database.getReference().child("Studentinfo").child(s2);

        try {
            Query q = ref1.orderByKey();
            // For counting Users
            q.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    Studentinfo p1 = dataSnapshot.getValue(Studentinfo.class);
                    name.setText(p1.getName());
                    mDOB.setText(p1.getDOB());
                    gender.setText(p1.getGender());
                    mobileno.setText(p1.getPhoneNo());
                    col.setText(p1.getQualification());
                    skill.setText(p1.getSkills());
                   // Toast.makeText(getApplicationContext(),""+p1.getExperience(),Toast.LENGTH_LONG).show();
                    exp.setText(p1.getExperience());
                    //cp.setText(p1.getCoursepublished());
                    //ct.setText(p1.getCoursetaken());


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }


                public void onCancelled(FirebaseError firebaseError) {

                }
            });


        }catch(Exception ex){}







    }

    public void onCoursePublished(View v){
        Intent i=new Intent(this,showCPProfile.class);
        startActivity(i);
    }

    public void onBackPressed() {
        Intent startMain = new Intent(this,Home.class);
        //startMain.addCategory(Intent.CATEGORY_HOME);
        //startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }
    public void onCourseTaken(View v)
    {
        Intent i=new Intent(this,showCourseTaken.class);
        startActivity(i);
    }
    public void onLogout(View view)
    {

        SharedPreferences sp=getSharedPreferences("sp1",MODE_PRIVATE);
    SharedPreferences.Editor ed=sp.edit();
    ed.putString("email","");
    ed.commit();

        Intent intentTomain=new Intent(this,MainActivity.class);
        startActivity(intentTomain);

    }
    public void oneditprofile(View view)
    {
        Intent intentTomain=new Intent(this,EditProfile.class);
        startActivity(intentTomain);


    }

    public void onMyChats(View view)
    {
        Intent it=new Intent(getApplicationContext(),showChats.class);
        startActivity(it);
    }

    public void onCourseCompleted(View view)
    {
        Intent it=new Intent(this,CompletedCourses.class);
        startActivity(it);
    }

    public boolean isConnected()
    {
        ConnectivityManager connectivityManager= (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo= connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

}
