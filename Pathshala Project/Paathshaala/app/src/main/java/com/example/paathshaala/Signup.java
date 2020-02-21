package com.example.paathshaala;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Signup extends AppCompatActivity {
    EditText email, password;
    Button btnSignIn;
    String s1="";

    FirebaseAuth mFirebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();
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
        mFirebaseAuth=FirebaseAuth.getInstance();
        email=(EditText)findViewById(R.id.email_edit_text_signup);
        password=(EditText)findViewById(R.id.password_edit_text_signup);

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                    if(email.getText().toString().contains("ii")) {
                        if (validateEmail()) {
                            Toast.makeText(getApplicationContext(), "VALID", Toast.LENGTH_SHORT).show();

                        } else
                            Toast.makeText(getApplicationContext(), "INVALID EMAIL", Toast.LENGTH_SHORT).show();
                    }
            }
        });

    }
    public void onSignup(View view)
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref = database.getReference().child("Dashboard");

        String emailId=email.getText().toString();
        String pwd=password.getText().toString();
        if(emailId.isEmpty())
        {
            email.setError("Please enter the email id");
            email.requestFocus();
        }
        else if(pwd.isEmpty())
        {
            password.setError("Please enter the password");
            password.requestFocus();
        }
        else if(emailId.isEmpty()&& pwd.isEmpty())
        {
            Toast.makeText(this,"Fields are empty!!", Toast.LENGTH_SHORT).show();
        }
        else if(!(emailId.isEmpty()&& pwd.isEmpty()))
        {
            if(pwd.length()<=5)
            {
                Toast.makeText(this, "Password must contain atleast 6 characters",Toast.LENGTH_SHORT).show();
            }
            else
            {
                mFirebaseAuth.createUserWithEmailAndPassword(emailId,pwd).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful())
                        {
                            Toast.makeText(Signup.this,"Sign up unsuccessful!! Please try again..", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {







                            try{
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                               final DatabaseReference ref1 = database.getReference().child("Dashboard").child("users");
                                Query q=ref1.orderByKey();


                                q.addListenerForSingleValueEvent(new ValueEventListener() {

                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {

                                        for(DataSnapshot sn:dataSnapshot.getChildren())
                                        {
                                           // Dashboardinfo p1=sn.getValue(Dashboardinfo.class);
                                            String t1=sn.getValue().toString();
                                            s1= String.valueOf(Integer.valueOf(t1)+1);
                                           // Toast.makeText(Signup.this," After s1 : "+s1,Toast.LENGTH_LONG).show();
                                            //Dashboardinfo d1=new Dashboardinfo();
                                           // d1.setCountuser(s1);
                                            ref1.child("countuser").setValue(s1);

                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }


                                    public void onCancelled(FirebaseError firebaseError) {

                                    }
                                });
                            }
                            catch (Exception e)
                            {
                                Toast.makeText(Signup.this," exception : "+e,Toast.LENGTH_LONG).show();

                            }









                          //  Toast.makeText(Signup.this," just before : "+s1,Toast.LENGTH_LONG).show();








                            Intent it=new Intent(Signup.this,Signup_Userinfo.class);
                            it.putExtra("email",email.getText().toString());
                            startActivity(it);
                        }
                    }
                });
            }

        }
        else
        {
            Toast.makeText(Signup.this,"Error occured! :(", Toast.LENGTH_SHORT).show();
        }


    }
    private boolean validateEmail(){
        String EMAIL_PATTERN = "[a-zA-Z]+[0-9]*@iiitd\\.ac\\.in";
        String emailCheck =  email.getText().toString().trim();

        Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);
        Matcher match = emailPattern.matcher(emailCheck);

        return match.matches();
    }
    public boolean isConnected()
    {
        ConnectivityManager connectivityManager= (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo= connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

}
