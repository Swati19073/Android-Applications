package com.example.paathshaala;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class DetailActivity extends AppCompatActivity
{

    TextView coursename;
    TextView course_fee;
    TextView duration;
    TextView email;
    TextView count;
    TextView Iname;
    TextView date;
    TextView timing;
    TextView venue;
    TextView studname;

    //Instructor elements
    TextView Insname;
    TextView coursepub;
    TextView dob;
    TextView gender;
    TextView phone;
    TextView experiance;
    TextView qualification;
    TextView skills;
    String s1="";
    String key;
    Button bt;


    //======
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;

    StorageReference ref;
    //=====
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
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
        coursename=(TextView)findViewById(R.id.course_name_value);
        course_fee=(TextView)findViewById(R.id.course_fees_value);
        duration=(TextView)findViewById(R.id.duration_value);
        email=(TextView)findViewById(R.id.email_value);
        count=(TextView)findViewById(R.id.no_students_value);
        Iname=(TextView)findViewById(R.id.ins_name_value);
        date=(TextView)findViewById(R.id.start_date_value);
        timing=(TextView)findViewById(R.id.timing_value);
        venue=(TextView)findViewById(R.id.venue_value);
        Insname=(TextView)findViewById(R.id.stud_name_value);
        //coursepub=(TextView)findViewById(R.id.course_published_value);
        dob=(TextView)findViewById(R.id.dob_value);
        gender=(TextView)findViewById(R.id.gender_value);
        phone=(TextView)findViewById(R.id.phone_value);
        studname=(TextView) findViewById(R.id.name_value);
        experiance=(TextView)findViewById(R.id.experiance_value);
        qualification=(TextView)findViewById(R.id.qualification_value);
        skills=(TextView)findViewById(R.id.skills_value);
        bt=(Button)findViewById(R.id.enroll) ;
        Intent rcv = getIntent();

       key = rcv.getStringExtra("key");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref1 = database.getReference().child("NewsFeed").child(key);


        try {
            Query q = ref1.orderByKey();
            // For counting Users
            q.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    PostCourseinfo p1 = dataSnapshot.getValue(PostCourseinfo.class);
                    coursename.setText(p1.getCourse1());
                    course_fee.setText(p1.getFees1());
                    duration.setText(p1.getDuration1());
                    email.setText(p1.getEmail());
                    count.setText(p1.getTotalstudents());
                    Iname.setText(p1.getName1());
                    date.setText(p1.getDate1());
                    timing.setText(p1.getTimings1());
                    venue.setText(p1.getVenue1());
                    studname.setText(p1.getStudentname());




                 s1=p1.getEmail();

                    studin(s1);
                    offbutton();


                    SharedPreferences sp=getSharedPreferences("sp1",MODE_PRIVATE);
                    final String s33 =sp.getString("email","");

                    if(s33.equals(s1))
                    {
                        bt.setEnabled(false);
                        bt.setVisibility(View.GONE);

                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }


                public void onCancelled(FirebaseError firebaseError) {

                }
            });


        }catch(Exception ex){}

        //----------------------------------------





    }


    public void studin(String s1){

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
       // Toast.makeText(getApplicationContext(),"abe:"+s2,Toast.LENGTH_LONG).show();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref2 = database.getReference().child("Studentinfo").child(s2);

        try {
            Query q = ref2.orderByKey();
            // For counting Users
            q.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    Studentinfo p1 = dataSnapshot.getValue(Studentinfo.class);
                    //coursepub.setText(""+p1.getCoursepublished());
                    dob.setText(p1.getDOB());
                    experiance.setText(p1.getExperience());
                    gender.setText(p1.getGender());
                    Insname.setText(p1.getName());
                    phone.setText(p1.getPhoneNo());
                    qualification.setText(p1.getQualification());
                    skills.setText(p1.getSkills());

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }


                public void onCancelled(FirebaseError firebaseError) {

                }
            });


        }catch(Exception ex){}   //----------------------------------------









    }

    public void onEnroll(View view){


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
        final   DatabaseReference ref1 = database.getReference().child("NewsFeed").child(key);



        try {
            Query q=ref1.orderByKey();
            // For counting Users
            q.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    PostCourseinfo p1=dataSnapshot.getValue(PostCourseinfo.class);

                    String a= String.valueOf(Integer.parseInt(p1.getTotalstudents())+1);

                    // e1.setText(ans);
                    p1.setTotalstudents(a);
                    p1.setStudentname(p1.getStudentname()+"||"+temp);
                    ref1.setValue(p1);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }


                public void onCancelled(FirebaseError firebaseError) {

                }
            });

        }catch(Exception ex){}

//----------------------------------update student info-------------------

        final   DatabaseReference ref2 = database.getReference().child("Studentinfo").child(temp);
        try {
            Query q=ref2.orderByKey();
            // For counting Users
            q.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    Studentinfo p1=dataSnapshot.getValue(Studentinfo.class);

                    p1.setCoursetaken(p1.getCoursetaken()+"||"+key);



                    ref2.setValue(p1);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }


                public void onCancelled(FirebaseError firebaseError) {

                }
            });

        }catch(Exception ex){}





Intent it =new Intent(this,StudentHome.class);
        startActivity(it);

    }

    public void offbutton()
    {
        SharedPreferences sp=getSharedPreferences("sp1",MODE_PRIVATE);
        final String s11 =sp.getString("email","");
        String semail ="";
        for(int i=0;i<s11.length();i++)
        {
            if(s11.charAt(i)!='@')
            {
                semail=semail+s11.charAt(i);
            }
            else
            {
                break;
            }
        }


        final String temp=semail;

        if(studname.getText().toString().contains(temp))
        {
            bt.setEnabled(false);
            bt.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(),"You are already enrolled in this Course",Toast.LENGTH_LONG).show();
        }
        else {
            //Toast.makeText(getApplicationContext(),"outside",Toast.LENGTH_LONG).show();
        }

    }

    public void oncertificate(View view)
    {
        SharedPreferences sp=getSharedPreferences("sp1",MODE_PRIVATE);
        String s1=email.getText().toString();
        String s2="";
        for(int i=0;i<s1.length();i++) {
            if (s1.charAt(i) != '@') {
                s2 = s2 + s1.charAt(i);
            } else {
                break;
            }

        }

        final String s3=s2;
        storageReference = firebaseStorage.getInstance().getReference();
        ref = storageReference.child("Uploads/").child(s2+"Certi");
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String ur = uri.toString();
                downloadfiles(DetailActivity.this,s3+" Certificates" ,".pdf", Environment.DIRECTORY_DOWNLOADS, ur );
                Toast.makeText(getApplicationContext(),"Certificate Successfully Downloaded",Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"No Certificates Uploaded",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void downloadfiles(Context context,String fileName, String fileExtension, String destinationDirectory, String url){

        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);

        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context, destinationDirectory, fileName+fileExtension);

        downloadManager.enqueue(request);

    }
    public boolean isConnected()
    {
        ConnectivityManager connectivityManager= (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo= connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

}
