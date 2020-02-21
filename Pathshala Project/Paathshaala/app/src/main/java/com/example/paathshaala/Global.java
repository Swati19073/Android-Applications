package com.example.paathshaala;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
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
import java.util.TimerTask;

public class Global extends AppCompatActivity {

    EditText edChat;
    TextView title,chat;
    String coursename;
    String username;
    String currentDate;
    ImageView send;
    String Z;
    TextView replyTv;
    Button cutBtn;
    TextView msgTv;
    LinearLayout l;
    int flag=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global);
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
        edChat=(EditText)findViewById(R.id.messageArea);
        //title =(TextView)findViewById(R.id.title);
        // chat=(TextView)findViewById(R.id.chat);

        cutBtn=(Button)findViewById(R.id.btn_cut);
        replyTv=(TextView)findViewById(R.id.reply_tv);
        send=(ImageView)findViewById(R.id.send_msg);
        l=(LinearLayout)findViewById(R.id.linear) ;
        //msgTv=(TextView)findViewById(R.id.tv1);

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
        username=semail;
        Toast.makeText(Global.this,"Hii " + username, Toast.LENGTH_SHORT).show();





        fetch();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref1 = database.getReference().child("Global").child("Gchat");




        send.setOnClickListener(new View.OnClickListener() {
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
        //-------------------Fetching Data-----------
        final List<String> temp1=new ArrayList<>();

        //final List<String> keylist=new ArrayList<>();
        flag=0;
        //final String msg=edChat.getText().toString();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref1 = database.getReference().child("Global").child("Gchat");

        try {
            Query q=ref1.orderByKey();


            // q.addListenerForSingleValueEvent(new ValueEventListener() {
            q.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    String s1=dataSnapshot.getValue().toString();

                    //chat.setText(dataSnapshot.getValue().toString());

                    temp1.add(s1);

                    //edChat.setText("");
                    //adapter.notifyDataSetChanged();
                    listView1(temp1);



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

    public void listView1(List<String> temp1)
    {
        List<String> list1= new ArrayList<>();
        final List<String> name= new ArrayList<>();
        final List<String> time= new ArrayList<>();
        final List<String> msg= new ArrayList<>();
        List<String> temp3= new ArrayList<>();

        list1=temp1;
//        for(int i=0;i<temp1.size();i++)
//        {
//            temp1.set(i,"");
//        }
        int index1=0,index2=0;
        String finalList= list1.get(0);
        // int length=finalList.length();

        StringTokenizer st= new StringTokenizer(finalList,"`");
        while(st.hasMoreTokens())
        {
            //msgTv.setText(st.nextToken());
            String temp=st.nextToken();
            temp1.add(temp);
            StringTokenizer st1= new StringTokenizer(temp,"||");
            while(st1.hasMoreTokens()){

                temp3.add(st1.nextToken());


            }
            msg.add(temp3.get(0));
            time.add(temp3.get(1));
            name.add(temp3.get(2));

            temp3.clear();




        }
        temp1.set(0,"");
//        for(int i=0;i<length;i++)
//        {
//
//            if((finalList.charAt(i)=='\\') && (finalList.charAt(i+1)=='n'))

//
//        }
//


//........................................Second alternative..................//
//        int length=finalList.length();
//        String items[]=finalList.split("\\n");
//        for(int i=0;i<items.length;i++)
//        {
//            temp1.add(items[i]);
//        }
//        //........................................Second alternative................../


        //Toast.makeText(getApplicationContext(),toadd,Toast.LENGTH_LONG).show();
//        Toast.makeText(getApplicationContext(),"hii:"+temp1,Toast.LENGTH_LONG).show();

        //ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,temp1);
        MyArrayAdapter adapter=new MyArrayAdapter(this, name, msg,time);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setSelection(temp1.size()-1);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                l.setVisibility(View.VISIBLE);

                replyTv.setTextColor(Color.parseColor("#b03060"));
                replyTv.setText(time.get(position)+"-->" +"\n");

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
    }
    public void send(View view)
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
        l.setVisibility(View.GONE);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref1 = database.getReference().child("Global").child("Gchat");

        //title.setText(coursename);
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy HH:mm ");
        Date date = new Date();
        currentDate=dateFormat.format(date);
        try {
            Query q=ref1.orderByKey();


            q.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String newChat=edChat.getText().toString();
                    if(newChat.equals("")) {
                        Toast.makeText(getApplicationContext(),"Type something",Toast.LENGTH_LONG).show();
                    }
                    else{
                        //Toast.makeText(Global.this,dataSnapshot.getValue().toString(),Toast.LENGTH_LONG).show();
                        ref1.setValue(dataSnapshot.getValue().toString() + "`" + username + "||" +replyTv.getText().toString() +""+newChat + "||" + currentDate + "`");
                        edChat.setText("");
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
/*public void onRestoreInstanceState(Bundle savedInstancestate)
{
    super.onRestoreInstanceState(savedInstancestate);

    replyTv.setText(savedInstancestate.getString("S"));

}*/
public boolean isConnected()
{
    ConnectivityManager connectivityManager= (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo networkInfo= connectivityManager.getActiveNetworkInfo();
    return networkInfo != null && networkInfo.isConnected();
}
}





