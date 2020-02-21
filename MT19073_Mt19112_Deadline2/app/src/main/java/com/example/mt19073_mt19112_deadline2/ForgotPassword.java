package com.example.mt19073_mt19112_deadline2;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

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

public class ForgotPassword extends AppCompatActivity {

    Button sendEmailBtn;
    EditText emailEt;
    ProgressBar forgotPasswordPbr;
    Toolbar toolbar;
    FirebaseAuth mFireBaseAuth;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        sendEmailBtn=(Button)findViewById(R.id.btn_forget_password);
        emailEt=(EditText)findViewById(R.id.et_forgot_password);

        toolbar=(Toolbar)findViewById(R.id.tb_forget_password);

        toolbar.setTitle("Forgot Password");
        mFireBaseAuth=FirebaseAuth.getInstance();
        sendEmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=emailEt.getText().toString();
                mFireBaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {

                            Toast.makeText(ForgotPassword.this,"Password sent to your email",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(ForgotPassword.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });


            }
        });



    }
}
