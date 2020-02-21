package com.example.mt19073_mt19112_deadline2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SelectRole extends AppCompatActivity {
    EditText etr;
    Button btnr;
    DatabaseReference ref1;
    EmpRole empRole;
    String er;
    String enam,ephn;
    String temail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_role);
        etr=(EditText)findViewById(R.id.et_role);
        btnr=(Button)findViewById(R.id.btn_role);
        Intent it=getIntent();
        temail=it.getStringExtra("email");
        enam=it.getStringExtra("name");
        ephn=it.getStringExtra("phone");
        Toast.makeText(getApplicationContext(),temail, Toast.LENGTH_SHORT).show();
        empRole= new EmpRole();
        ref1= FirebaseDatabase.getInstance().getReference().child("EmpRole");

        Log.d("inside button","ajaaa");
        btnr.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                er = etr.getText().toString().trim();
                int index = temail.indexOf('@');
                String pemail = temail.substring(0, index);
                empRole.setEmpemail(temail);
                empRole.setEmprole(er);
                empRole.setEmpname(enam);
                empRole.setPhoneNo(ephn);
                ref1.child(pemail).setValue(empRole);
                Toast.makeText(getApplicationContext(),"Employee added sucessfully!!", Toast.LENGTH_SHORT).show();
                etr.setText("");

                Intent i= new Intent(SelectRole.this,Admin.class);
                startActivity(i);
            }
        });



    }
}
