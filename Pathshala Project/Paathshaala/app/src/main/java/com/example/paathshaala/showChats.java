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

public class showChats extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_chats);

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
        final List<String> valuelist = new ArrayList<>();

        SharedPreferences sp=getSharedPreferences("sp1",MODE_PRIVATE);
        String s1=sp.getString("email","");
        String s2="";
        for(int i=0;i<s1.length();i++) {
            if (s1.charAt(i) != '@') {
                s2 = s2 + s1.charAt(i);
            } else {
                break;
            }

        }

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref1 = database.getReference().child("Private").child(s2).child("Message");

        try {
            Query q = ref1.orderByKey();
            // For counting Users
            q.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for (DataSnapshot sn : dataSnapshot.getChildren()) {

                        valuelist.add(sn.getKey().toString());
                        //Log.d("chat","In Data"+sn.getKey());


                    }
                    // e1.setText(keylist.get(1));
                    dothis(valuelist);






                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }


                public void onCancelled(FirebaseError firebaseError) {

                }
            });


        } catch (Exception ex) {
        }

    }

    public void dothis(final List<String> valuelist){

        // ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, valuelist);
        List<String> inamelist = new ArrayList<>();
        List<String> cnamelist = new ArrayList<>();
        List<String> datelist = new ArrayList<>();
        inamelist=valuelist;
        datelist=valuelist;
        cnamelist=valuelist;


        //Log.d("chat","In Do"+valuelist.get(0));
       // Log.d("chat","In Do"+valuelist.get(1));

        datelist=valuelist;




        MyAdapterShowChat adapter=new MyAdapterShowChat(this,cnamelist , inamelist);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);







        //MyAdapterShowChat adapter=new MyAdapterShowChat(this,valuelist,datelist);
        //ListView listView = (ListView) findViewById(R.id.listView);

//        listView.setAdapter(adapter);







//        MyAdapterShowChat adapter=new MyAdapterShowChat(this,valuelist,inamelist);
//
//
//
//        ListView listView = (ListView) findViewById(R.id.listView);
//       listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                final Intent it=new Intent(getApplicationContext(),PrivateChat.class);

                it.putExtra("temail",valuelist.get(position));
                startActivity(it);
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

