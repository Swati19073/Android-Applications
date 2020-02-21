package com.example.mt19073_mt19112_deadline2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Admin extends AppCompatActivity {
    Button aditem;
    Button tosp;
    Button ademp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        aditem=(Button)findViewById(R.id.btn_add_itema);
        tosp=(Button)findViewById(R.id.btn_access_emp);
        ademp=(Button)findViewById(R.id.btn_access_emp);
        aditem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Admin.this,AddItem.class);
                startActivity(i);
            }
        });

        tosp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(Admin.this,SalesPerson.class);
                startActivity(i1);
            }
        });


        ademp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(Admin.this,AddEmployee.class);
                startActivity(i2);
            }
        });
    }
}
