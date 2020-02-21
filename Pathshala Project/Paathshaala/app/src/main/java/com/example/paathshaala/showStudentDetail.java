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
import android.widget.TextView;
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

public class showStudentDetail extends AppCompatActivity {
    TextView stud;
    String currentDate;
    TextView db;
    TextView gender;
    TextView contact;
    TextView exp;
    TextView qua;
    TextView skill;
    TextView courpub;
    String teachermail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_student_detail);
        stud=(TextView)findViewById(R.id.stud_name_value_2);
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
         courpub=(TextView) findViewById(R.id.course_published_value_2);
        db=(TextView)findViewById(R.id.dob_value_2);
        gender=(TextView)findViewById(R.id.gender_value_2);
        contact=(TextView)findViewById(R.id.phone_value_2);
        exp=(TextView) findViewById(R.id.experiance_value_2);
        qua=(TextView) findViewById(R.id.qualification_value_2);
        skill=(TextView) findViewById(R.id.skills_value_2);

        //------------Fetch info From Db--------
        Intent i=getIntent();
        String key=i.getStringExtra("key");
        teachermail=key;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref1 = database.getReference().child("Studentinfo").child(key);

        try {
            Query q = ref1.orderByKey();
            // For counting Users
            q.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    Studentinfo p1 = dataSnapshot.getValue(Studentinfo.class);
                    courpub.setText(p1.getCoursepublished());
                    db.setText(p1.getDOB());
                    exp.setText(p1.getExperience());
                    gender.setText(p1.getGender());
                    stud.setText(p1.getName());
                    contact.setText(p1.getPhoneNo());
                    qua.setText(p1.getQualification());
                    skill.setText(p1.getSkills());

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }


                public void onCancelled(FirebaseError firebaseError) {

                }
            });


        }catch(Exception ex){}   //----------------------------------------
    }

    public void onrequest(View view)
    {
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
final String g1=semail;
        Toast.makeText(showStudentDetail.this,"outside",Toast.LENGTH_LONG).show();

        final Intent it=new Intent(getApplicationContext(),PrivateChat.class);

        it.putExtra("temail",teachermail);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref1 = database.getReference().child("Private").child(teachermail).child("Message");
        final DatabaseReference ref2 = database.getReference().child("Private").child(semail).child("Message");
        final DatabaseReference ref3 = database.getReference().child("Private");

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy HH:mm");
        Date date = new Date();
        currentDate=dateFormat.format(date);
        ref3.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot data: dataSnapshot.getChildren()){
                    if (data.getKey().toString().equals(g1)) {
                        //do ur stuff
                       // Toast.makeText(showStudentDetail.this,"in exists",Toast.LENGTH_LONG).show();
                    } else {

                        //do something if not exists
                        ref1.child(g1).setValue("`"+teachermail+"||"+"Welcome"+"||"+currentDate+"`");
                       // Toast.makeText(showStudentDetail.this,"in not exists",Toast.LENGTH_LONG).show();
                        ref2.child(teachermail).setValue("`"+teachermail+"||"+"Welcome"+"||"+currentDate+"`");
                        startActivity(it);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });














        ref1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot data: dataSnapshot.getChildren()){
                    if (data.getKey().toString().equals(g1)) {
                        //do ur stuff
                        Toast.makeText(showStudentDetail.this,"in exists",Toast.LENGTH_LONG).show();
                        startActivity(it);
                    } else {

                        //do something if not exists
                        ref1.child(g1).setValue("`"+teachermail+"||"+"Welcome"+"||"+currentDate+"`");
                       // Toast.makeText(showStudentDetail.this,"in not exists",Toast.LENGTH_LONG).show();
                        ref2.child(teachermail).setValue("`"+teachermail+"||"+"Welcome"+"||"+currentDate+"`");
                        startActivity(it);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });

        //FirebaseUtil.getResponsesRef().child(postKey).addValueEventListener(responseListener);


    }
    public boolean isConnected()
    {
        ConnectivityManager connectivityManager= (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo= connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

}