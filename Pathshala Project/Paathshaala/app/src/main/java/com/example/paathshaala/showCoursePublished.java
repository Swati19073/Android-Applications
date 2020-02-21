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
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
import java.util.StringTokenizer;

public class showCoursePublished extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_course_published);
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
                    String cp=p1.getCoursepublished();

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
        StringTokenizer st=new StringTokenizer(cp,"||");

        while(st.hasMoreTokens())
        {
            key1list.add(st.nextToken());
        }

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
                    dothis(valuelist,keylist,cnamelist,inamelist,datelist,feelist);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }


                public void onCancelled(FirebaseError firebaseError) {

                }
            });












        }catch(Exception ex){}


    }

    public void dothis(List<String> valuelist,List<String> keylist,List<String> cnamelist,List<String> inamelist,List<String> datelist,List<String> feelist){

       // ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, valuelist);
        MyListAdapter adapter=new MyListAdapter(this,cnamelist , inamelist,datelist,feelist);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        final List<String>keyTemplist=keylist;

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                final int pos=position;
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(showCoursePublished.this);

                alertDialog.setMessage("which Action you want to take?");
                alertDialog.setPositiveButton("Delete Course", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {
                        //===============
                       FirebaseDatabase database = FirebaseDatabase.getInstance();
                       final String key=String.valueOf(keyTemplist.get(pos));
                        DatabaseReference ref1 = database.getReference().child("NewsFeed").child(key);





                        try {
                            Query q=ref1.orderByKey();
                            // For counting Users
                            q.addListenerForSingleValueEvent(new ValueEventListener() {

                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {


                                        PostCourseinfo p1=dataSnapshot.getValue(PostCourseinfo.class);

                             //   String s=p1.getStudentname();
                              //  Toast.makeText(getApplicationContext(),""+s,Toast.LENGTH_LONG).show();




                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }


                                public void onCancelled(FirebaseError firebaseError) {

                                }
                            });












                        }catch(Exception ex){}



                       //===============================================

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

                                    String cp=p1.getCoursepublished();

                                    String ncp=cp.replace("||"+key,"");

                                    p1.setCoursepublished(ncp);


                                    ref2.setValue(p1);



                                  //  Toast.makeText(getApplicationContext(),""+ncp,Toast.LENGTH_LONG).show();




                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }


                                public void onCancelled(FirebaseError firebaseError) {

                                }
                            });







                        }catch(Exception ex){}

                        //===================================================



                        final DatabaseReference ref3 = database.getReference().child("Studentinfo");



                        try {
                            Query q=ref3.orderByKey();
                            // For counting Users
                            q.addListenerForSingleValueEvent(new ValueEventListener() {

                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {

                                    for (DataSnapshot sn : dataSnapshot.getChildren()) {
                                        Studentinfo p1 = sn.getValue(Studentinfo.class);

                                      String ct=  p1.getCoursetaken();
                                      if (ct.contains(key)){

                                          String nct=ct.replace("||"+key,"");
                                          p1.setCoursetaken(nct);

                                          ref3.child(sn.getKey()).setValue(p1);
                                          //Toast.makeText(getApplicationContext(),""+sn.getKey(),Toast.LENGTH_LONG).show();
                                        Log.d("check",sn.getKey()+"=="+nct);

                                      }





                                    }


                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }


                                public void onCancelled(FirebaseError firebaseError) {

                                }
                            });







                        }catch(Exception ex){}



                       //=================================================





                        ref1.removeValue();

                        //===============
                        Toast.makeText(getApplicationContext(), "Course Deleted", Toast.LENGTH_SHORT).show();
                    }
                });

                alertDialog.setNegativeButton("Course Completed", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,	int which) {



                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        final String key=String.valueOf(keyTemplist.get(pos));
                      final   DatabaseReference ref1 = database.getReference().child("NewsFeed").child(key);
                        final DatabaseReference ref2 = database.getReference().child("CoursesCompleted").child(key);



                        try {
                            Query q=ref1.orderByKey();
                            // For counting Users
                            q.addListenerForSingleValueEvent(new ValueEventListener() {

                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {

                                    PostCourseinfo p2=dataSnapshot.getValue(PostCourseinfo.class);
                                    Log.d("inshow",p2.getCourse1());
                                    ref2.setValue(p2);
                                    ref1.removeValue();



                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }


                                public void onCancelled(FirebaseError firebaseError) {

                                }
                            });




                        }catch(Exception ex){}




                    //==============================================
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


                     final   DatabaseReference ref3 = database.getReference().child("Studentinfo").child(s2);
                       final DatabaseReference ref4 = database.getReference().child("StudentCourseCompleted").child(s2);

                        try {
                            Query q=ref3.orderByKey();
                            // For counting Users
                            q.addListenerForSingleValueEvent(new ValueEventListener() {

                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {


                                    Studentinfo p1=dataSnapshot.getValue(Studentinfo.class);

                                    String cp=p1.getCoursepublished();

                                    String ncp=cp.replace("||"+key,"");

                                    p1.setCoursepublished(ncp);


                                    ref3.setValue(p1);



                                    //  Toast.makeText(getApplicationContext(),""+ncp,Toast.LENGTH_LONG).show();




                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }


                                public void onCancelled(FirebaseError firebaseError) {

                                }
                            });







                        }catch(Exception ex){}



                        try {
                            Query q=ref4.orderByKey();
                            // For counting Users
                            q.addListenerForSingleValueEvent(new ValueEventListener() {

                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {


                                    completedcourseinfo p1=dataSnapshot.getValue(completedcourseinfo.class);

                                     String temp= p1.getCoursetaught();
                                     String temp1=temp+"||"+key;

                                     p1.setCoursetaught(temp1);


                                     ref4.setValue(p1);


                                    //  Toast.makeText(getApplicationContext(),""+ncp,Toast.LENGTH_LONG).show();




                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }


                                public void onCancelled(FirebaseError firebaseError) {

                                }
                            });







                        }catch(Exception ex){}




                    //==========================================


                        final DatabaseReference ref5 = database.getReference().child("Studentinfo");



                        try {
                            Query q=ref5.orderByKey();
                            // For counting Users
                            q.addListenerForSingleValueEvent(new ValueEventListener() {

                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {

                                    for (DataSnapshot sn : dataSnapshot.getChildren()) {
                                        Studentinfo p1 = sn.getValue(Studentinfo.class);

                                        String ct=  p1.getCoursetaken();
                                        if (ct.contains(key)){

                                            String nct=ct.replace("||"+key,"");
                                            p1.setCoursetaken(nct);

                                            ref5.child(sn.getKey()).setValue(p1);
                                            //Toast.makeText(getApplicationContext(),""+sn.getKey(),Toast.LENGTH_LONG).show();
                                            Log.d("check",sn.getKey()+"=="+nct);



                                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                                            final DatabaseReference ref6 = database.getReference().child("StudentCourseCompleted").child(sn.getKey());


                                           //=============================

                                            try {
                                                Query q=ref6.orderByKey();
                                                // For counting Users
                                                q.addListenerForSingleValueEvent(new ValueEventListener() {

                                                    @Override
                                                    public void onDataChange(DataSnapshot dataSnapshot) {


                                                        completedcourseinfo p1=dataSnapshot.getValue(completedcourseinfo.class);

                                                        String temp= p1.getCoursestudied();
                                                        String temp1=temp+"||"+key;

                                                        p1.setCoursestudied(temp1);


                                                        ref6.setValue(p1);


                                                        //  Toast.makeText(getApplicationContext(),""+ncp,Toast.LENGTH_LONG).show();




                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                                    }


                                                    public void onCancelled(FirebaseError firebaseError) {

                                                    }
                                                });







                                            }catch(Exception ex){}
                                            //=====================









                                        }





                                    }


                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }


                                public void onCancelled(FirebaseError firebaseError) {

                                }
                            });







                        }catch(Exception ex){}


                    //===================================================================


                        Toast.makeText(getApplicationContext(), "Successfully Completed", Toast.LENGTH_SHORT).show();
                        Intent it =new Intent(showCoursePublished.this,TeachaerHome.class);
                        startActivity(it);

                        dialog.cancel();
                    }
                });


                alertDialog.show();
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
