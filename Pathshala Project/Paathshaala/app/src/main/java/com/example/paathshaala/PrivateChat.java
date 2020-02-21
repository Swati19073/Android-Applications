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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

public class PrivateChat extends AppCompatActivity {

    EditText msgEt;
    //    TextView msgTv;
    ImageView sendBtn;
    // String coursename;
    String username1;
    String currentDate;
    TextView replyTv;
    Button cutBtn;
    LinearLayout l;
    int flag=0;
    String temail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_chat);
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
    Intent it=getIntent();
     temail=it.getStringExtra("temail");


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
        username1=semail;
        Toast.makeText(PrivateChat.this,"Hii " + username1, Toast.LENGTH_SHORT).show();
        //coursename =i.getStringExtra("coursename");
        /*DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        currentDate=dateFormat.format(date);
        //title.setText(coursename);*/

        cutBtn=(Button)findViewById(R.id.btn_cut);
        replyTv=(TextView)findViewById(R.id.reply_tv);
        msgEt=(EditText)findViewById(R.id.messageArea);
//        msgTv=(TextView)findViewById(R.id.private_tv);
        sendBtn= (ImageView) findViewById(R.id.send_msg);
        l=(LinearLayout)findViewById(R.id.linear);
        fetch();
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                send(view);
            }
        });

        if (savedInstanceState != null) {

            String Z1 = savedInstanceState.getString("S");
            //  Toast.makeText(getApplicationContext(),"in not null "+Z1,Toast.LENGTH_LONG).show();
            replyTv.setText(Z1);
            l.setVisibility(View.VISIBLE);
            cutBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    l.setVisibility(View.GONE);
                }
            });


        }


    }



    public void fetch()
    {
        //-------------------Fetching Data-----------
        final List<String> temp2=new ArrayList<>();
//        final ArrayList<String> valuelist=new ArrayList<>();
//        final ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, valuelist);
        //final List<String> keylist=new ArrayList<>();
        flag=0;
        final String msg=msgEt.getText().toString();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref1 = database.getReference().child("Private").child(temail).child("Message").child(username1);
        DatabaseReference ref2 = database.getReference().child("Private").child(username1).child("Message").child(temail);

        try {
            Query q=ref1.orderByKey();


            q.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    //chat.setText(dataSnapshot.getValue().toString());
                    //valuelist.add(dataSnapshot.getValue().toString() + username);
                    String s2=dataSnapshot.getValue().toString();
                    temp2.add(s2);

                    viewList(temp2);
                    //msgEt.setText("");
                    // adapter.notifyDataSetChanged();

                }



                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }



                public void onCancelled(FirebaseError firebaseError) {

                }
            });

            //ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, valuelist);

//            ListView listView = (ListView) findViewById(R.id.listView);
//            listView.setAdapter(adapter);
//
//
//            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//
//
//
//
//                    //  Toast.makeText(getApplicationContext(),String.valueOf(keylist.get(position)),Toast.LENGTH_LONG).show();
//
//                }
//            });

            Query q1=ref2.orderByKey();


            q1.addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    temp2.clear();
                    String s3=dataSnapshot.getValue().toString();
                    temp2.add(s3);
                    viewList(temp2);
                    //chat.setText(dataSnapshot.getValue().toString());
                    //valuelist.add(dataSnapshot.getValue().toString() + username);

                    msgEt.setText("");
                    //adapter.notifyDataSetChanged();

                }



                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }



                public void onCancelled(FirebaseError firebaseError) {

                }
            });



        }catch(Exception ex){}


        //-------------------------

    }


    public void viewList(List<String> temp2) {
        List<String> list1 = new ArrayList<>();
        final List<String> name= new ArrayList<>();
        final List<String> time= new ArrayList<>();
        final List<String> msg= new ArrayList<>();
        List<String> temp3= new ArrayList<>();
        list1 = temp2;
        String finalList= list1.get(0);
        StringTokenizer st= new StringTokenizer(finalList,"`");
        while(st.hasMoreTokens())
        {
            //temp2.add(st.nextToken());
            String temp=st.nextToken();
            temp2.add(temp);
            StringTokenizer st1= new StringTokenizer(temp,"||");
            while(st1.hasMoreTokens()){

                temp3.add(st1.nextToken());


            }
            msg.add(temp3.get(0));
            time.add(temp3.get(1));
            name.add(temp3.get(2));

            temp3.clear();
        }
        //temp2.set(0,"");
        // temp2.set(temp2.size()-1,"");
        //temp2.set(temp2.size()-1,"");

        MyArrayAdapter adapter=new MyArrayAdapter(this, name, msg,time);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setSelection(temp2.size()-1);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                l.setVisibility(View.VISIBLE);
                replyTv.setText(time.get(position)+"-->"+"\n");
                cutBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        replyTv.setText("");
                        l.setVisibility(View.GONE);
                    }
                });

                // edChat.setText("Replied to "+time.get(position)+"==>");

                //  Toast.makeText(getApplicationContext(),String.valueOf(keylist.get(position)),Toast.LENGTH_LONG).show();

            }
        });


//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//
////msg:uname      time: msg    username:time
//                replyTv.setText(time.get(position)+"==>" + "\n");
//                cutBtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        replyTv.setText("");
//                    }
//                });
//
//                //msgEt.setText(replyTv.getText().toString());
//
//                //  Toast.makeText(getApplicationContext(),String.valueOf(keylist.get(position)),Toast.LENGTH_LONG).show();
//
//            }
//        });
    }

    public void send(View view)
    {
        l.setVisibility(View.GONE);
        final String chat= msgEt.getText().toString();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref1 = database.getReference().child("Private").child(temail).child("Message").child(username1);
        final DatabaseReference ref2 = database.getReference().child("Private").child(username1).child("Message").child(temail);
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy HH:mm");
        Date date = new Date();
        currentDate=dateFormat.format(date);
        try {
            Query q=ref1.orderByKey();



            /*q.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String newChat=msgEt.getText().toString();
                    if(newChat.equals(""))
                    {
                        Toast.makeText(getApplicationContext(),"Type Something",Toast.LENGTH_LONG).show();
                    }
                    else {
                        //ref1.setValue(dataSnapshot.getValue().toString() + "`" + username1.toString() + "||" + newChat + "||" + currentDate + "`");
                        ref1.setValue(dataSnapshot.getValue().toString() + "`" + username1.toString() + "||" + newChat + "||" + currentDate + "`");
                        ref2.setValue(dataSnapshot.getValue().toString() + "`" + username1.toString() + "||" + newChat + "||" + currentDate + "`");

                        //ref2.setValue(dataSnapshot.getValue().toString()+"`"+username1+"\n"+newChat + "\n" +currentDate+"`");
                        //ref1.setValue(username+"`"+newChat + "`" +currentDate);
                        // msgEt.setText("");
                       // fetch();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }



                public void onCancelled(FirebaseError firebaseError) {

                }
            });
*/
            Query q1=ref2.orderByKey();


            q1.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String newChat=msgEt.getText().toString();
                    if(newChat.equals(""))
                    {
                        Toast.makeText(getApplicationContext(),"Type Something",Toast.LENGTH_LONG).show();
                    }
                    else {
                        // ref2.setValue(dataSnapshot.getValue().toString() + "`" + username1.toString() + "||" + newChat + "||" + currentDate + "`");
                        // ref1.setValue(dataSnapshot.getValue().toString()+"`"+username1+"\n"+newChat + "\n" +currentDate+"`");
                        ref1.setValue(dataSnapshot.getValue().toString() + "`" + username1.toString() + "||" + replyTv.getText().toString()+newChat + "||" + currentDate + "`");
                        ref2.setValue(dataSnapshot.getValue().toString() + "`" + username1.toString() + "||" + replyTv.getText().toString()+newChat + "||" + currentDate + "`");

                        msgEt.setText("");
                        fetch();

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }



                public void onCancelled(FirebaseError firebaseError) {

                }
            });

        }catch(Exception ex){}
    }
    public void onSaveInstanceState(Bundle  outstate) {
        super.onSaveInstanceState(outstate);
        outstate.putString("S",replyTv.getText().toString() );



    }

    public void onBackPressed()
    {
        Intent startMain = new Intent(this,StudentHome.class);
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


