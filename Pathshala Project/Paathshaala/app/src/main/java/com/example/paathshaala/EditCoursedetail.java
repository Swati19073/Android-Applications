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
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class EditCoursedetail extends AppCompatActivity {
    EditText name,course,venue,timings,duration,fees,date;
    String n;
    String key1;
    String ts,ns;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_coursedetail);
        //name=(EditText)findViewById(R.id.name);
        course=(EditText)findViewById(R.id.course);
        venue=(EditText)findViewById(R.id.venue);
        timings=(EditText)findViewById(R.id.timings);
        duration=(EditText)findViewById(R.id.duration);
        fees=(EditText)findViewById(R.id.fees);
        date=(EditText)findViewById(R.id.date);


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

        //=========================================
        Intent it=getIntent();
        key1=it.getStringExtra("key");
        //Toast.makeText(getApplicationContext(),""+key1,Toast.LENGTH_LONG).show();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref1 = database.getReference().child("NewsFeed").child(key1);

        try {
            Query q=ref1.orderByKey();
            // For counting Users
            q.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    PostCourseinfo p1 = dataSnapshot.getValue(PostCourseinfo.class);
    ts=p1.getTotalstudents();
    ns=p1.getStudentname();
                    course.setText(p1.getCourse1().toString());

                    venue.setText(p1.getVenue1());

                    timings.setText(p1.getTimings1());

                    duration.setText(p1.getDuration1());
                    fees.setText(p1.getFees1());
                    date.setText(p1.getDate1());
                    // e1.setText(keylist.get(1));
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }


                public void onCancelled(FirebaseError firebaseError) {

                }
            });












        }catch(Exception ex){}






        //==========================================


        SharedPreferences sp=getSharedPreferences("sp1",MODE_PRIVATE);
        String s1=sp.getString("email","");
        String s2="";
        for(int i=0;i<s1.length();i++)
        {
            if(s1.charAt(i)!='@')
            {
                s2=s2+s1.charAt(i);
            }
            else
            {
                break;
            }
        }


        final DatabaseReference ref2 = database.getReference().child("Studentinfo").child(s2);
        try {
            Query q=ref2.orderByKey();
            // For counting Users
            q.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    Studentinfo p1=dataSnapshot.getValue(Studentinfo.class);

                    putname(p1.getName());

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }


                public void onCancelled(FirebaseError firebaseError) {

                }
            });

        }catch(Exception ex){}




    }

    public void post(View view) {
        // Toast.makeText(getApplicationContext(),"//"+course.getText().toString()+"//"+venue.getText().toString()+"//",Toast.LENGTH_LONG).show();
        if (!(course.getText().toString().equals("") || venue.getText().toString() .equals("") || timings.getText().toString().equals("") || duration.getText().toString().equals("") || fees.getText().toString().equals("") || date.getText().toString().equals(""))) {
            SharedPreferences sp = getSharedPreferences("sp1", MODE_PRIVATE);
            String s1 = sp.getString("email", "");
            String s2 = "";
            for (int i = 0; i < s1.length(); i++) {
                if (s1.charAt(i) != '@') {
                    s2 = s2 + s1.charAt(i);
                } else {
                    break;
                }
            }
            final String s3 = s2;

            // String name1,course1,venue1,timings1,duration1,fees1;
            PostCourseinfo n1 = new PostCourseinfo();

            n1.setEmail(s1);
            n1.setCourse1(course.getText().toString());
            n1.setTotalstudents(ts);
            n1.setStudentname(ns);
            n1.setVenue1(venue.getText().toString());
            n1.setTimings1(timings.getText().toString());
            n1.setDuration1(duration.getText().toString());
            n1.setFees1(fees.getText().toString());
            n1.setDate1(date.getText().toString());
            n1.setName1(n);

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference ref1 = database.getReference().child("NewsFeed").child(key1);
            ref1.setValue(n1);
            String tempo = ref1.getKey();
          //  updateInfokey(tempo, s3);


        }

        else
        {
            Toast.makeText(getApplicationContext(),"Fill All details",Toast.LENGTH_LONG).show();
        }

        Intent it=new Intent(this,TeachaerHome.class);
        startActivity(it);
    }
    public void updateInfokey(final String lastkey, String s2)
    {
        //-----------update in student info for coursepublised --------------------
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref2 = database.getReference().child("Studentinfo").child(s2);
        try {
            Query q = ref2.orderByKey();
            // For counting Users
            q.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    Studentinfo p1 = dataSnapshot.getValue(Studentinfo.class);

                    p1.setCoursepublished(p1.getCoursepublished() + "||" + lastkey);


                    ref2.setValue(p1);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }


                public void onCancelled(FirebaseError firebaseError) {

                }
            });

        } catch (Exception ex) {
        }


//--------------------------------------------------------

        Intent it = new Intent(this, TeachaerHome.class);
        startActivity(it);

    }

    public void putname(String n1)
    {
        n=n1;

    }
    public boolean isConnected()
    {
        ConnectivityManager connectivityManager= (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo= connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}

