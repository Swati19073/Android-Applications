package com.example.mt19073_mt19112_deadline2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SalesPerson extends AppCompatActivity {

    EditText e1,e2,e3;
    Button b1;
    String s1;
    float s2;
    TextView t1,t2,t3,t4;
    DatabaseReference ref1,ref2;
    Orders orders;
    String omail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_person);
        e1=(EditText)findViewById(R.id.et1);
        e2=(EditText)findViewById(R.id.et2);

        b1=(Button)findViewById(R.id.total);
        Intent intent=getIntent();
        omail=intent.getStringExtra("email");

        ref1= FirebaseDatabase.getInstance().getReference().child("Items");
        ref2=FirebaseDatabase.getInstance().getReference().child("Orders");


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Float itemcost=Float.parseFloat((e2.getText().toString().trim()));
                final String itemName=e1.getText().toString().trim();

                final Float itemquant=Float.parseFloat((e3.getText().toString().trim()));

                try
                {
                    Query q=ref1.orderByKey();

                    q.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot sn : dataSnapshot.getChildren())
                            {
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

                                String date = simpleDateFormat.format(new Date());


                                Items items=sn.getValue(Items.class);

                                s1=items.getItemName();
                                s2=items.getQuantity();

                                Log.d("itemname",s1);
                                Log.d("itemname",itemName);
                                if(s1.equals(itemName)&&(s2!=0)&&(s2>=itemquant))
                                {
                                    items.setQuantity(s2-itemquant);
                                    orders=new Orders();
                                    orders.setOitemName(itemName);
                                    orders.setOcost(itemcost);
                                    orders.setOquant(itemquant);
                                    orders.setOemail(omail);
                                    orders.setTime(date);
                                    ref2.push().setValue(orders);
//                                    Toast.makeText(getApplicationContext(),"Item successfully added",Toast.LENGTH_SHORT).show();
                                }

                                else
                                {
                                    Toast.makeText(getApplicationContext(),"Item does not exists",Toast.LENGTH_SHORT).show();
                                }

                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });


    }
}
