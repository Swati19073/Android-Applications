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
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Dashboard extends AppCompatActivity {
    TextView numusers;
    RatingBar rating2;
    RatingBar rate;
    Button rateButton;
    AlertDialog.Builder builder;
    ArrayAdapter adapter;
    ListView listView;
    private String m_Text="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
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
        numusers=(TextView)findViewById(R.id.tvusercount);
        rating2=(RatingBar) findViewById(R.id.edrating2);
        rate=(RatingBar) findViewById(R.id.edrating);
        rateButton=(Button)findViewById(R.id.brate);
        builder = new AlertDialog.Builder(this);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref1 = database.getReference().child("Dashboard").child("users");
        DatabaseReference ref2 = database.getReference().child("Dashboard").child("Rating");
        try {
            Query q=ref1.orderByKey();
            Query q1=ref2.orderByKey();
            // For counting Users
            q.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for (DataSnapshot sn : dataSnapshot.getChildren()) {
                        String count=String.valueOf(sn.getValue());
                        numusers.setText(count);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }


                public void onCancelled(FirebaseError firebaseError) {

                }
            });
            //For Overall Rating


            q1.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for (DataSnapshot sn : dataSnapshot.getChildren()) {
                        String count=String.valueOf(sn.getValue());
                        String temp="";
                        for(int i=0;i<count.length();i++)
                        {
                            if(String.valueOf(count.charAt(i)).equals("."))
                            {
                                temp=temp+String.valueOf(count.charAt(i))+String.valueOf(count.charAt(i+1));
                                break;
                            }
                            else
                            {
                                temp=temp+String.valueOf(count.charAt(i));
                            }
                        }

                        rating2.setRating(Float.parseFloat(temp));
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }


                public void onCancelled(FirebaseError firebaseError) {

                }
            });
        }catch(Exception ex){}
         listView = (ListView) findViewById(R.id.listView);
        feedback();
    }

    public void feedback()
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
        //Toast.makeText(getApplicationContext(),"in Feedback",Toast.LENGTH_LONG).show();

        //final String[] feedbacks = new String[Integer.parseInt(numusers.getText().toString())];
        final List<String> feedbacks=new ArrayList<>();
        final List<String> name1=new ArrayList<>();
        final List<String> msg1=new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        final DatabaseReference ref2 = database.getReference().child("Dashboard").child("feedback");
        Query q1=ref2.orderByKey();
        q1.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i=0;
                for (DataSnapshot sn : dataSnapshot.getChildren()) {
                    String feed =String.valueOf(sn.getValue());
                    String name=String.valueOf(sn.getKey());
                    feedbacks.add(name+":\n"+feed);
                    name1.add(name+":");
                    msg1.add(feed+".");
                    i=i++;
                }

                Log.d("msg1","msg:"+msg1.get(1));
                MyfeedbacklistAdapter adapter=new MyfeedbacklistAdapter(Dashboard.this,name1,msg1);
                //  ListView listView = (ListView) findViewById(R.id.listView);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


            public void onCancelled(FirebaseError firebaseError) {

            }
        });

         //adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, feedbacks);
//        Log.d("msg1","name:"+name1.get(1));

    }

    public void rate(View view)
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
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        final DatabaseReference ref2 = database.getReference().child("Dashboard").child("Rating");
        Query q1=ref2.orderByKey();
        q1.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot sn : dataSnapshot.getChildren()) {
                    String count =String.valueOf(sn.getValue());
                    if(rate.getRating()!=0.0) {
                        float count1 = (rate.getRating() + Float.parseFloat(count)) / 2;

                        String count2=String.valueOf(count1);
                        String temp="";
                        for(int i=0;i<count2.length();i++)
                        {
                            if(String.valueOf(count2.charAt(i)).equals("."))
                            {
                                temp=temp+String.valueOf(count2.charAt(i))+String.valueOf(count2.charAt(i+1));
                                break;
                            }
                            else
                            {
                                temp=temp+String.valueOf(count2.charAt(i));
                            }
                        }

                        rating2.setRating(Float.parseFloat(temp));
                       // rating2.setText(String.valueOf(count1));
                        ref2.child("OverallRating").setValue(String.valueOf(count1));
                    }
                    else {
                        Toast.makeText(Dashboard.this,"Please Give Rating!",Toast.LENGTH_LONG).show();

                    }
                }

                rate.setEnabled(false);
                rateButton.setEnabled(false);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void submitFeedback(View view)
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
        //Uncomment the below code to Set the message and title from the strings.xml file
        builder.setTitle("Submit Feedback");


        final EditText input = new EditText(this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_LONG_MESSAGE);
        builder.setView(input);

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
// Set up the buttons
        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String feedback1 = input.getText().toString();
                int flag=0;
if(!feedback1.equals("")) {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref1 = database.getReference().child("Dashboard").child("feedback").child(temp);
    ref1.setValue(feedback1);
    flag=1;
    //======================


    //=======================




    //========================

}
else {
    Toast.makeText(getApplicationContext(),"Write Feedback",Toast.LENGTH_LONG).show();
}

if(flag==1)
{

Intent it=new Intent(Dashboard.this,Dashboard.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
startActivity(it);
//Toast.makeText(getApplicationContext(),"in feedback",Toast.LENGTH_LONG).show();

}


            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
        if(!input.getText().toString().equals("")) {
            feedback();
        }
    }


    public void onBackPressed()
    {
        Intent startMain = new Intent(this,Home.class);
        //startMain.addCategory(Intent.CATEGORY_HOME);
        //startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }
    public boolean isConnected()
    {
        ConnectivityManager connectivityManager= (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo= connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}