package com.example.paathshaala;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPasswordActivity extends AppCompatActivity {

    Button sendEmailBtn;
    EditText emailEt;
    ProgressBar forgotPasswordPbr;
    Toolbar toolbar;
    FirebaseAuth mFireBaseAuth;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
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
        sendEmailBtn=(Button)findViewById(R.id.btn_forget_password);
        emailEt=(EditText)findViewById(R.id.et_forgot_password);
        forgotPasswordPbr=(ProgressBar)findViewById(R.id.pb_forget_password);
        toolbar=(Toolbar)findViewById(R.id.tb_forget_password);

        toolbar.setTitle("Forgot Password");
        mFireBaseAuth=FirebaseAuth.getInstance();
        sendEmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forgotPasswordPbr.setVisibility(View.VISIBLE);
                String email = emailEt.getText().toString();

if(!email.equals("")){
                mFireBaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        forgotPasswordPbr.setVisibility(View.GONE);
                        if (task.isSuccessful()) {

                            Toast.makeText(ForgetPasswordActivity.this, "Password sent to your email", Toast.LENGTH_LONG).show();

                            Intent it = new Intent(ForgetPasswordActivity.this, MainActivity.class);
                            startActivity(it);
                        } else {
                            Toast.makeText(ForgetPasswordActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
else {
    Toast.makeText(ForgetPasswordActivity.this, "Please Enter email!", Toast.LENGTH_LONG).show();


}




            }
        });



    }
    public boolean isConnected()
    {
        ConnectivityManager connectivityManager= (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo= connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
