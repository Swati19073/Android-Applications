package com.example.paathshaala;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.dynamitechetan.flowinggradient.FlowingGradientClass;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    EditText email, password;
    Button btnSignIn;
    TextView tvSignIn;
    FirebaseAuth mFirebaseAuth;
    int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

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


        if(android.os.Build.VERSION.SDK_INT >=21)
        {
            getWindow().setStatusBarColor(getResources().getColor(R.color.purple_500));
        }

        SharedPreferences sp=getSharedPreferences("sp1",MODE_PRIVATE);
        String s1=sp.getString("email","");
        if(s1.length()==0){}
        else {
            Intent intentToHome=new Intent(MainActivity.this,Home.class);
            startActivity(intentToHome);
        }


        mFirebaseAuth=FirebaseAuth.getInstance();


        RelativeLayout rl = (RelativeLayout) findViewById(R.id.activity_main);
        FlowingGradientClass grad = new FlowingGradientClass();
        grad.setBackgroundResource(R.drawable.translate)
                .onRelativeLayout(rl)
                .setTransitionDuration(4000)
                .start();



        email=(EditText)findViewById(R.id.email_edit_text_signup);
        password=(EditText)findViewById(R.id.password_edit_text_signup);
        btnSignIn=(Button)findViewById(R.id.sign_in_btn);
        tvSignIn=(TextView)findViewById(R.id.tv_signin);

       // Intent it = new Intent(this.getApplicationContext(), Login.class);
       // startActivity(it);



        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {






Intent it=new Intent(getApplicationContext(),Signup.class);
startActivity(it);










            }
        });







    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onBackPressed()
    {
        //super.onBackPressed();
        finishAffinity();
        finish();
        //return;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onLogin(View view)
    {

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
            Toast.makeText(MainActivity.this,"Fields are empty!!", Toast.LENGTH_SHORT).show();
        }
        else if(!(emailId.isEmpty()&& pwd.isEmpty()))
        {
            mFirebaseAuth.signInWithEmailAndPassword(emailId,pwd).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful())
                    {
                        Toast.makeText(MainActivity.this,"Incorrect Credentials!!",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {

                        //==================

                        //int flag2=onCheck();

                        //=================
                       //for login


                        SharedPreferences sp=getSharedPreferences("sp1",MODE_PRIVATE);

                        String s1=sp.getString("email","");

                        SharedPreferences.Editor ed=sp.edit();
                        ed.putString("email",email.getText().toString());

                       ed.commit();

                       if(flag==0)
                       {
                           onCheck();
                       }


                    }
                }
            });
        }
        else
        {
            Toast.makeText(MainActivity.this,"Error occured! :(", Toast.LENGTH_SHORT).show();
        }

    }
    public void onforwardtouinfo(int flag1){

       /* if(flag==0)
        {
            SharedPreferences sp=getSharedPreferences("sp1",MODE_PRIVATE);
            final String s1 =sp.getString("email","");
            Intent it=new Intent(this,Signup_Userinfo.class);
            it.putExtra("email",s1);
            startActivity(it);

        }
        else {
            Intent loginIntent=new Intent(MainActivity.this,Home.class);
            startActivity(loginIntent);
        }*/

       flag=flag1;
       Log.d("msg",String.valueOf(flag));
    }

    public int onCheck()
    {
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

        String s2=email.getText().toString();
        String semail ="";
        for(int i=0;i<s2.length();i++)
        {
            if(s2.charAt(i)!='@')
            {
                semail=semail+s2.charAt(i);
            }
            else
            {
                break;
            }
        }


        final String temp=semail;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref1 = database.getReference().child("Studentinfo");

        try {
            Query q = ref1.orderByKey();
            // For counting Users
            q.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    for (DataSnapshot sn : dataSnapshot.getChildren()) {


                        if(sn.getKey().toString().equals(temp))
                        {
                            flag=1;

                        }


                    }
                    if(flag!=1)
                    {
                        flag=2;
                    }
                    SharedPreferences sp=getSharedPreferences("sp1",MODE_PRIVATE);

                    String s1=sp.getString("email","");
                    if(flag==2)
                    {

                        Intent it=new Intent(MainActivity.this,Signup_Userinfo.class);
                        it.putExtra("email",s1);
                        startActivity(it);

                    }
                    else if(flag==1){
                        Intent loginIntent=new Intent(MainActivity.this,Home.class);
                        startActivity(loginIntent);
                    }
                    else{
                        //onCheck();
                    }
                    onforwardtouinfo(flag);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }


                public void onCancelled(FirebaseError firebaseError) {

                }
            });


        }catch(Exception ex){}



       /* if(flag==2)
        {

            Intent it=new Intent(MainActivity.this,Signup_Userinfo.class);
            it.putExtra("email",s1);
            startActivity(it);

        }
        else if(flag==1){
            Intent loginIntent=new Intent(MainActivity.this,Home.class);
            startActivity(loginIntent);
        }
        else{
            //onCheck();
        }*/

        return flag;

    }
    public void onforgotpass(View view)
    {
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
        Intent it =new Intent(MainActivity.this,ForgetPasswordActivity.class);
        startActivity(it);


    }
    public boolean isConnected()
    {
        ConnectivityManager connectivityManager= (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo= connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
