package com.example.mt19073_mt19112_deadline2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MyRole extends AppCompatActivity {

    Button badmin,bsp,bsm,bim,logout;
    String email;
    DatabaseReference ref1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_role);
        badmin=(Button)findViewById(R.id.admin_btn);
        bsp=(Button)findViewById(R.id.sp_btn);
        bsm=(Button)findViewById(R.id.sm_btn);
        bim=(Button)findViewById(R.id.im_btn);
        logout=(Button)findViewById(R.id.logout_btn);
        Intent i = getIntent();
        email=i.getStringExtra("mail");
        ref1= FirebaseDatabase.getInstance().getReference().child("EmpRole");
        bsp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try
                {
                    Query q=ref1.orderByKey();

                    q.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot sn : dataSnapshot.getChildren())
                            {

                                EmpRole empRole=sn.getValue(EmpRole.class);
                                String s1=empRole.getEmpemail();
                                if(s1.equals(email))
                                {
                                    String s2= empRole.getEmprole();
                                    if(s2.equals("sp"))
                                    {
                                        Intent i=new Intent(MyRole.this,SalesPerson.class);
                                        i.putExtra("email",email);
                                        startActivity(i);
                                    }
                                    else
                                    {
                                        Toast.makeText(getApplicationContext(),"not in",Toast.LENGTH_SHORT).show();
                                    }
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




        bsm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try
                {
                    Query q=ref1.orderByKey();

                    q.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot sn : dataSnapshot.getChildren())
                            {

                                EmpRole empRole=sn.getValue(EmpRole.class);
                                String s3=empRole.getEmpemail();
                                if(s3.equals(email))
                                {
                                    String s4= empRole.getEmprole();
                                    if(s4.equals("sm"))
                                    {

                                        Intent i=new Intent(MyRole.this,SalesManager.class);
                                        i.putExtra("email",email);
                                        startActivity(i);
                                    }
                                    else
                                    {
                                        Toast.makeText(getApplicationContext(),"not in",Toast.LENGTH_SHORT).show();
                                    }
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



        bim.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try
                {
                    Query q=ref1.orderByKey();

                    q.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot sn : dataSnapshot.getChildren())
                            {

                                EmpRole empRole=sn.getValue(EmpRole.class);
                                String s5=empRole.getEmpemail();
                                if(s5.equals(email))
                                {
                                    String s6= empRole.getEmprole();
                                    if(s6.equals("im"))
                                    {
                                        Intent i=new Intent(MyRole.this,InventoryManager.class);
                                        i.putExtra("email",email);
                                        startActivity(i);
                                    }
                                    else
                                    {
                                        Toast.makeText(getApplicationContext(),"not in",Toast.LENGTH_SHORT).show();
                                    }
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

        badmin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try
                {
                    Query q=ref1.orderByKey();

                    q.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot sn : dataSnapshot.getChildren())
                            {

                                EmpRole empRole=sn.getValue(EmpRole.class);
                                String s7=empRole.getEmpemail();
                                if(s7.equals(email))
                                {
                                    String s8= empRole.getEmprole();
                                    if(s8.equals("admin"))
                                    {
                                        Intent i=new Intent(MyRole.this,Admin.class);
                                        i.putExtra("email",email);
                                        startActivity(i);
                                    }
                                    else
                                    {
                                        Toast.makeText(getApplicationContext(),"not in",Toast.LENGTH_SHORT).show();
                                    }
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

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii= new Intent(MyRole.this,MainActivity.class);
                startActivity(ii);
            }
        });

    }
}
