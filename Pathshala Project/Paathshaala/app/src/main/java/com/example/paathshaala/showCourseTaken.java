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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class showCourseTaken extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_course_taken);
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
                    String cp=p1.getCoursetaken();

                    passcp(cp) ;

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }


                public void onCancelled(FirebaseError firebaseError) {

                }
            });


        }catch(Exception ex){}   //----------------------------------------


    }

    public void passcp(String cp)
    {
//        String temp;
//        for(int i=0;i<cp.length();i++)
//        {
//            if(cp.charAt(i)=='|'){
//                i++;
//
//            }
//            else
//            {
//
//
//            }
//
//
//        }
        final String cp1 = cp;
        List<String> key1list=new ArrayList<>();
        final List<String> cnamelist=new ArrayList<>();
        final List<String> inamelist=new ArrayList<>();
        final List<String> datelist=new ArrayList<>();
        final List<String> feelist=new ArrayList<>();
        StringTokenizer st=new StringTokenizer(cp,"||");

        while(st.hasMoreTokens())
        {
            key1list.add(st.nextToken());
        }

        final List<String> valuelist=new ArrayList<>();
        final List<String> keylist=new ArrayList<>();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref1 = database.getReference().child("NewsFeed");

        try {
            Query q=ref1.orderByKey();
            // For counting Users
            q.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for (DataSnapshot sn : dataSnapshot.getChildren()) {
                        if (cp1.contains(sn.getKey().toString()))
                        {
                            PostCourseinfo p1 = sn.getValue(PostCourseinfo.class);

                            String c1 = p1.getCourse1();
                            String n1 = p1.getName1();
                            String e1 = p1.getEmail();
                            String f1 = p1.getFees1();
                            String d1 = p1.getDate1();
                            String k1 = sn.getKey();
                            valuelist.add("Course Name:" + c1 + "    " + "Instructor:" + n1 + "\n" + "Fees:" + f1 + "    " + "Date:" + d1);

                            cnamelist.add(c1);
                            inamelist.add(n1);
                            feelist.add(f1);
                            datelist.add(d1);

                            keylist.add(k1);


                        }



                        /*
                        if (p1.getCourse1().equals(s)){
                         ans+=sn.getKey();
                           ans+="||";
                        }

                        e1.setText(ans);

                        */
                    }
                    // e1.setText(keylist.get(1));
                    dothis(valuelist,keylist,cnamelist,inamelist,feelist,datelist);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }


                public void onCancelled(FirebaseError firebaseError) {

                }
            });












        }catch(Exception ex){}


    }

    public void dothis(List<String> valuelist,List<String> keylist,List<String> cnamelist,List<String> inamelist,List<String> feelist,List<String> datelist){

        //ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, valuelist);
        MyListAdapter adapter=new MyListAdapter(this,cnamelist , inamelist,datelist,feelist);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        final List<String> k1=keylist;

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {


                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("key", k1.get(position));
                startActivity(intent);

                //  Toast.makeText(getApplicationContext(),String.valueOf(keylist.get(position)),Toast.LENGTH_LONG).show();

            }
        });


    }
    public boolean isConnected()
    {
        ConnectivityManager connectivityManager= (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo= connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
