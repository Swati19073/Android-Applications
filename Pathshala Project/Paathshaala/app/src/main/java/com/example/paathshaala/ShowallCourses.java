package com.example.paathshaala;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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

public class ShowallCourses extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showall_courses);


        final List<String> valuelist=new ArrayList<>();
        final List<String> keylist=new ArrayList<>();
        final List<String> cnamelist=new ArrayList<>();
        final List<String> inamelist=new ArrayList<>();
        final List<String> datelist=new ArrayList<>();
        final List<String> feelist=new ArrayList<>();


        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref1 = database.getReference().child("NewsFeed");

        try {
            Query q=ref1.orderByKey();
            // For counting Users
            q.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for (DataSnapshot sn : dataSnapshot.getChildren()) {
                        PostCourseinfo p1=sn.getValue(PostCourseinfo.class);

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

        final List<String> keylist1=keylist;

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
               // Toast.makeText(getApplicationContext(),String.valueOf(keylist1.get(position)),Toast.LENGTH_LONG).show();
                Intent it=new Intent(getApplicationContext(),DetailActivity.class);
                it.putExtra("key",keylist1.get(position));
                startActivity(it);

                    }





        });


    }
}
