package com.example.paathshaala;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;


public class PostCourse extends AppCompatActivity {

    DatePickerDialog picker;

    EditText name,venue,timing_from, timing_to,duration,fees,date1, timings;
    MultiAutoCompleteTextView course;
    private Spinner month,week,days;
    String n;
    String[] Skills;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_course);
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
        //name=(EditText)findViewById(R.id.name);
        course=(MultiAutoCompleteTextView) findViewById(R.id.course);
        Skills=getResources().getStringArray(R.array.skills);
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Skills);
        course.setAdapter(arrayAdapter);
        course.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        venue=(EditText)findViewById(R.id.venue);
        timing_from=(EditText)findViewById(R.id.from);
        timing_to = (EditText)findViewById(R.id.to);
        //duration=(EditText)findViewById(R.id.duration);
        fees=(EditText)findViewById(R.id.fees);
        date1=(EditText)findViewById(R.id.date);

        month=(Spinner)findViewById(R.id.month);
        week=(Spinner)findViewById(R.id.week);
        days=(Spinner)findViewById(R.id.day);
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

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final   DatabaseReference ref2 = database.getReference().child("Studentinfo").child(s2);
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



        date1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                picker = new DatePickerDialog(PostCourse.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                date1.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });


        timing_from.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(PostCourse.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        timing_from.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        timing_to.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(PostCourse.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        timing_to.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });




    }

    public void post(View view) {
        // Toast.makeText(getApplicationContext(),"//"+course.getText().toString()+"//"+venue.getText().toString()+"//",Toast.LENGTH_LONG).show();
        if (!(course.getText().toString().equals("") || venue.getText().toString() .equals("") || timing_from.getText().toString().equals("") || timing_to.getText().toString().equals("") || month.getSelectedItem().toString().equals("M") || week.getSelectedItem().toString().equals("W") || days.getSelectedItem().toString().equals("D") || fees.getText().toString().equals("") || date1.getText().toString().equals(""))) {
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

            String duration1="";
            String timing="";
            String temp2hour="";
            String temp2min="";

            String temp3hour="";
            String temp3min="";

            String temp2=timing_from.getText().toString();
            String temp3=timing_to.getText().toString();

            int flag=0;
            for(int i=0;i<temp2.length();i++)
            {
                if(flag==0)
                {
                    if(String.valueOf(temp2.charAt(i)).equals(":"))
                    {
                        flag=1;
                    }
                    else
                    {
                        temp2hour=temp2hour+temp2.charAt(i);
                    }
                }
                else
                {
                    temp2min=temp2min+temp2.charAt(i);
                }
            }

            flag=0;
            for(int i=0;i<temp3.length();i++)
            {
                if(flag==0)
                {
                    if(String.valueOf(temp3.charAt(i)).equals(":"))
                    {
                        flag=1;
                    }
                    else
                    {
                        temp3hour=temp3hour+temp3.charAt(i);
                    }
                }
                else
                {
                    temp3min=temp3min+temp3.charAt(i);
                }
            }

            int from=Integer.parseInt(temp2hour)*60+Integer.parseInt(temp2min);
            int to=Integer.parseInt(temp3hour)*60+Integer.parseInt(temp3min);
            if(from>=to)
            {
                Toast.makeText(this,"Choose valid time",Toast.LENGTH_LONG).show();
            }
            else {
                duration1 = month.getSelectedItem().toString() + " months " + week.getSelectedItem().toString() + " weeks " + days.getSelectedItem().toString() + " days";
                timing = "From " + timing_from.getText().toString() + " to " + timing_to.getText().toString();
                // String name1,course1,venue1,timings1,duration1,fees1;
                PostCourseinfo n1 = new PostCourseinfo();

                n1.setEmail(s1);
                n1.setCourse1(course.getText().toString());
                n1.setTotalstudents("0");
                n1.setStudentname("");
                n1.setVenue1(venue.getText().toString());
                n1.setTimings1(timing);
                n1.setDuration1(duration1);
                n1.setFees1(fees.getText().toString());
                n1.setDate1(date1.getText().toString());
                n1.setName1(n);

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference ref1 = database.getReference().child("NewsFeed").push();
                ref1.setValue(n1);
                String tempo = ref1.getKey();
                updateInfokey(tempo, s3);
            }

        }

        else
        {
            Toast.makeText(getApplicationContext(),"Fill All details",Toast.LENGTH_LONG).show();
        }
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

