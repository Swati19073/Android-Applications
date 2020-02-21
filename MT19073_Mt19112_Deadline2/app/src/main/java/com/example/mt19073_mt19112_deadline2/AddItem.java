package com.example.mt19073_mt19112_deadline2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddItem extends AppCompatActivity {


    EditText etname, etcost,etquant;
    Button btnadditem;
    Items item;
    String name;
    Float quant,cost1;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        etname=(EditText)findViewById(R.id.et_item_name);
        etcost=(EditText)findViewById(R.id.et_item_cost);
        etquant=(EditText)findViewById(R.id.et_item_quant);
        btnadditem=(Button)findViewById(R.id.btn_add_item);
        item=new Items();
        ref= FirebaseDatabase.getInstance().getReference().child("Items");
        btnadditem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=etname.getText().toString().trim();
                quant=Float.parseFloat((etquant.getText().toString().trim()));
                cost1=Float.parseFloat((etcost.getText().toString().trim()));


                item.setItemName(name);
                item.setCost(cost1);
                item.setQuantity(quant);
                ref.child(name).setValue(item);
                Toast.makeText(getApplicationContext(),"Item added", Toast.LENGTH_SHORT).show();
                etname.setText("");
                etcost.setText("");
                etname.setText("");
                etquant.setText("");


            }
        });

    }
}
