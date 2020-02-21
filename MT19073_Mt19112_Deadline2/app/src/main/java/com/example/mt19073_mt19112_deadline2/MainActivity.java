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
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    EditText email, password;
    Button btnLogin;

    Button btnForgotPassword;

    FirebaseAuth mFirebaseAuth;

    private FirebaseAuth.AuthStateListener mAuthStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAuth=FirebaseAuth.getInstance();
        email=(EditText)findViewById(R.id.email_edit_text_login);
        password=(EditText)findViewById(R.id.password_edit_text_login);
        btnLogin=(Button)findViewById(R.id.login_btn);

        btnForgotPassword=(Button)findViewById(R.id.btn_forgot_password_in_login);


        mAuthStateListener=new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFireBaseUser=mFirebaseAuth.getCurrentUser();
                if(mFireBaseUser!=null)
                {
                    Toast.makeText(MainActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
//                    Intent homeIntent=new Intent(MainActivity.this,MyRole.class);
//
//                    startActivity(homeIntent);
                }
                else {
                    Toast.makeText(MainActivity.this, "Please log in!", Toast.LENGTH_SHORT).show();
                }
            }
        };

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String emailId=email.getText().toString();
                String pwd=password.getText().toString();
                if(emailId.isEmpty())
                {
                    email.setError("Please enter the email id");
                    email.requestFocus();
                }
                else if(pwd.isEmpty())
                {
                    password.setError("Please enter the password");
                }
                else if(emailId.isEmpty()&& pwd.isEmpty())
                {
                    Toast.makeText(MainActivity.this,"Fields are empty!!", Toast.LENGTH_SHORT).show();
                }
                else if(!(emailId.isEmpty()&& pwd.isEmpty()))
                {
                    mFirebaseAuth.signInWithEmailAndPassword(emailId,pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful())
                            {
                                Toast.makeText(MainActivity.this,"Incorrect Password!!",Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Intent intentToHome=new Intent(MainActivity.this,MyRole.class);
                                intentToHome.putExtra("mail",emailId);
                                startActivity(intentToHome);
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Error occured! :(", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    protected void onStart(){
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);

    }
}
