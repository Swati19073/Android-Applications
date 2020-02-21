package com.example.mt19073_mt19112_deadline2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class AddEmployee extends AppCompatActivity {


    EditText email, password,phn,name;
    Button add;
    EmpRole emprole;
    FirebaseAuth mFirebaseAuth;
    DatabaseReference ref,ref2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);
        email=(EditText)findViewById(R.id.add_emp_mail);
        password=(EditText)findViewById(R.id.add_emp_password);
        phn=(EditText)findViewById(R.id.add_emp_phn);
        name=(EditText)findViewById(R.id.add_emp_name);
        mFirebaseAuth=FirebaseAuth.getInstance();
        add=(Button)findViewById(R.id.add_emp_btn);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String mail=email.getText().toString();
                String pwd=password.getText().toString();
                final String ename=name.getText().toString().trim();
                final String phone=phn.getText().toString().trim();
                if(mail.isEmpty())
                {
                    email.setError("Please enter the email id");
                    email.requestFocus();
                }
                else if(pwd.isEmpty())
                {
                    password.setError("Please enter the email id");
                    password.requestFocus();
                }
                else if(mail.isEmpty()&& pwd.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Fields are empty!!", Toast.LENGTH_SHORT).show();
                }
                else if(!(mail.isEmpty()&& pwd.isEmpty()))
                {
                    mFirebaseAuth.createUserWithEmailAndPassword(mail,pwd).addOnCompleteListener(AddEmployee.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(! task.isSuccessful())
                            {

                                Toast.makeText(getApplicationContext(),"sign in unsuccessfull!!", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"added success", Toast.LENGTH_SHORT).show();
                                Intent intent= new Intent(AddEmployee.this,SelectRole.class);
                                intent.putExtra("email",mail);
                                intent.putExtra("name",ename);
                                intent.putExtra("phone",phone);

                                startActivity(intent);

                            }
                        }
                    });
                }

                else
                {
                    Toast.makeText(getApplicationContext(),"Error occured", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }}

