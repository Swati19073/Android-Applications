package com.example.paathshaala;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class StudentHome extends AppCompatActivity {
    AutoCompleteTextView e1;
    String[] Skills;
    String ans="";
    int flag=0;
    private ViewPager viewPager;
    private DemoAdapter demoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);
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
        e1=(AutoCompleteTextView) findViewById(R.id.editText3);
        Skills=getResources().getStringArray(R.array.skills);
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Skills);
        e1.setAdapter(arrayAdapter);
        //e1.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        viewPager=findViewById(R.id.pager);
        demoAdapter=new DemoAdapter(getSupportFragmentManager());
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



    public void onfind (View view){
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
        flag=0;
        final String s=e1.getText().toString();


        //--------------------------search Course  in firebase ---------------------

        final List<String> valuelist=new ArrayList<>();
        final List<String> keylist=new ArrayList<>();
        final List<String> cnamelist=new ArrayList<>();
        final List<String> inamelist=new ArrayList<>();
        final List<String> datelist=new ArrayList<>();
        final List<String> feelist=new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref1 = database.getReference().child("NewsFeed");

        try {
            Query q=ref1.orderByKey();
            // For counting Users
            q.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for (DataSnapshot sn : dataSnapshot.getChildren()) {
                        PostCourseinfo p1=sn.getValue(PostCourseinfo.class);
                        if (p1.getCourse1().equals(s)) {
                            String c1 = p1.getCourse1();
                            String n1=p1.getName1();
                            String e1=p1.getEmail();
                            String f1=p1.getFees1();
                            String d1=p1.getDate1();
                            String k1=sn.getKey();
                            valuelist.add("Key:"+k1+"\n"+"Course Name:"+c1+"    "+"Instructor:"+n1+"\n"+"Fees:"+f1+"    "+"Date:"+d1);

                            cnamelist.add(c1);
                            inamelist.add(n1);
                            feelist.add(f1);
                            datelist.add(d1);
                            keylist.add(k1);


                        }




                    }
                    // e1.setText(keylist.get(1));
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }


                public void onCancelled(FirebaseError firebaseError) {

                }
            });

           // ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, valuelist);
            MyListAdapter adapter=new MyListAdapter(this,cnamelist , inamelist,datelist,feelist);
            ListView listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(adapter);


            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                    Intent i=new Intent(getApplicationContext(),DetailActivity.class);
                    i.putExtra("key",keylist.get(position));
                    startActivity(i);


                    //  Toast.makeText(getApplicationContext(),String.valueOf(keylist.get(position)),Toast.LENGTH_LONG).show();

                }
            });












        }catch(Exception ex){}


        //---------------------------------------------------------------------------

    }
    public void onFindInstructor(View view)
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
        final String s=e1.getText().toString();
        flag=1;

        //--------------------------search Course  in firebase ---------------------

        final List<String> valuelist=new ArrayList<>();
        final List<String> keylist=new ArrayList<>();
        final List<String> cnamelist=new ArrayList<>();
        final List<String> inamelist=new ArrayList<>();
        final List<String> datelist=new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref1 = database.getReference().child("Studentinfo");

        try {
            Query q=ref1.orderByKey();
            // For counting Users
            q.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for (DataSnapshot sn : dataSnapshot.getChildren()) {
                        Studentinfo p1=sn.getValue(Studentinfo.class);
                        if (p1.getSkills().toLowerCase().contains(s.toLowerCase())) {
                            String c1=s;
                            String n1=p1.getName();
                            String e1=sn.getKey()+"@iiitd.ac.in";
                            String k1=sn.getKey();

                            valuelist.add("Instructor:"+n1+"\n"+"Email:"+e1+"    "+"Course:"+c1);
                            cnamelist.add(c1);
                            inamelist.add(n1);

                            datelist.add(e1);
                            keylist.add(k1);




                        }

                    }
                    // e1.setText(keylist.get(1));
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }


                public void onCancelled(FirebaseError firebaseError) {

                }
            });

           // ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, valuelist);
            MyListAdapter1 adapter=new MyListAdapter1(this,cnamelist , inamelist,datelist);
            ListView listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(adapter);


            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                    //Toast.makeText(getApplicationContext(),String.valueOf(keylist.get(position)),Toast.LENGTH_LONG).show();
                    Intent i=new Intent(getApplicationContext(),showStudentDetail.class);
                    i.putExtra("key",keylist.get(position));
                    startActivity(i);
                }
            });












        }catch(Exception ex){}



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
