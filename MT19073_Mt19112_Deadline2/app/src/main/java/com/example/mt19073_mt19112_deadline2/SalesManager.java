package com.example.mt19073_mt19112_deadline2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class SalesManager extends AppCompatActivity {

    DatabaseReference ref1;
    Button b;
    Context c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_manager);
//        b = (Button) findViewById(R.id.btn_genRep);
//        ref1 = FirebaseDatabase.getInstance().getReference().child("Orders");
//
//        final List<String> iname = new ArrayList<>();
//        final List<String> spmail = new ArrayList<>();
//        final List<Float> iquant = new ArrayList<>();
//        final List<Float> icost = new ArrayList<>();
//        final List<String> itime = new ArrayList<>();
//
//        b.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                try {
//                    Query q = ref1.orderByKey();
//                    // For counting Users
//                    q.addListenerForSingleValueEvent(new ValueEventListener() {
//
//                        @Override
//                        public void onDataChange(DataSnapshot dataSnapshot) {
//
//                            for (DataSnapshot sn : dataSnapshot.getChildren()) {
//                                Orders p1 = sn.getValue(Orders.class);
//
//                                String n1 = p1.getOitemName();
//                                String m1 = p1.getOemail();
//                                String t1 = p1.getTime();
//                                Float c1 = p1.getOtotalCost();
//                                Float q1 = p1.getOquant();
//                                String k1 = sn.getKey();
////                               valuelist.add("Key:"+k1+"\n"+"Course Name:"+c1+"    "+"Instructor:"+n1+"\n"+"Fees:"+f1+"    "+"Date:"+d1);
//
//                                iname.add(n1);
//                                spmail.add(m1);
//                                icost.add(c1);
//                                iquant.add(q1);
//                                itime.add(t1);
//
//
//                            }
//                            MyArrayAdapter adapter = new MyArrayAdapter(SalesManager.this, iname, spmail, itime,icost, iquant);
//
////                final List<String> keylist1=keylist;
//
//                            ListView listView = (ListView) findViewById(R.id.listView);
//                            listView.setAdapter(adapter);
//
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                        }
//
//
//                        public void onCancelled(FirebaseError firebaseError) {
//
//                        }
//                    });
//
//
//                } catch (Exception ex) {
//                }
//
//            }
//
//
//        });
//    }

}}