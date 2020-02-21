package com.example.paathshaala;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class  Signup_Userinfo extends AppCompatActivity {

    final Calendar mCalender = Calendar.getInstance();
    DatePickerDialog picker;
    private Button mSignUp_btn;
    private DatabaseReference mDatabase;
    private EditText mName, mPhoneNo, mDOB, mExperience;
    private MultiAutoCompleteTextView mSkills;
    private String mGender;
    private Spinner mQualification;
    private String text;
    Studentinfo studentInfo;
    String[] Skills;
    String url2="";
    String ekey="";

    //----UploadFile variables----
    Button selectFile,upload;
    TextView notification;

    Uri pdfUri;

    FirebaseStorage storage;
    FirebaseDatabase database;

    ProgressDialog progressDialog;
    //----------------------------

    public void onRadioButtonClicked(View v) {

        boolean checked = ((RadioButton) v).isChecked();
        switch (v.getId()) {
            case R.id.radioMale:
                if (checked) {
                    mGender = "Male";
                    break;
                }
            case R.id.radioFemale:
                if (checked) {
                    mGender = "Female";
                }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        setContentView(R.layout.activity_signup__userinfo);
        mSignUp_btn = (Button) findViewById(R.id.btnSignUp);
        mName = (EditText) findViewById(R.id.edName);
        mPhoneNo = (EditText) findViewById(R.id.edPhoneno);
        mDOB = (EditText) findViewById(R.id.edDOB);
        mQualification = (Spinner) findViewById(R.id.spinQual);
        mSkills = (MultiAutoCompleteTextView) findViewById(R.id.edSkills);
        Skills=getResources().getStringArray(R.array.skills);
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Skills);
        mSkills.setAdapter(arrayAdapter);
        mSkills.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        mExperience = (EditText) findViewById(R.id.edExp);

        studentInfo = new Studentinfo();
        Intent it=getIntent();
        String s1=it.getStringExtra("email");
        String s2="";
        for(int i=0;i<s1.length();i++)
        {
            if(s1.charAt(i)!='@')
            {
                s2=s2+s1.charAt(i);
            }
            else
            {
                break;
            }
        }

        ekey=s2;
        mSignUp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //===================================
                if(!isConnected()) {
                    new AlertDialog.Builder(Signup_Userinfo.this)
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

                if (!(mName.getText().toString().equals("") || mDOB.getText().toString() .equals("") || mPhoneNo.getText().toString().equals("") || mQualification.getSelectedItem().toString().equals("") || mSkills.getText().toString().equals("") || mExperience.getText().toString().equals(""))) {

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference ref1 = database.getReference().child("Studentinfo");
                DatabaseReference myRef = database.getReference();
                studentInfo.setName(mName.getText().toString().trim());
                studentInfo.setDOB(mDOB.getText().toString().trim());
                studentInfo.setPhoneNo(mPhoneNo.getText().toString().trim());
                text = mQualification.getSelectedItem().toString();
                studentInfo.setQualification(text);
                studentInfo.setCoursepublished("");
                studentInfo.setCoursetaken("");
                studentInfo.setCertificateurl(url2);
                studentInfo.setSkills(mSkills.getText().toString().trim());
                studentInfo.setExperience(mExperience.getText().toString().trim());
                studentInfo.setGender(mGender);
                Intent it=getIntent();

                 String s1=it.getStringExtra("email");
                 String s2="";
                 for(int i=0;i<s1.length();i++)
                 {
                     if(s1.charAt(i)!='@')
                     {
                         s2=s2+s1.charAt(i);
                     }
                     else
                     {
                         break;
                     }
                 }

                //ekey=s2;
                myRef.child("Studentinfo").child(s2).setValue(studentInfo);


                 //===================================

                    completedcourseinfo cci=new completedcourseinfo();
                    cci.setCoursestudied("");
                    cci.setCoursetaught("");
                    myRef.child("StudentCourseCompleted").child(s2).setValue(cci);


                    //================================






                Intent intToHome=new Intent(Signup_Userinfo.this,MainActivity.class);
                startActivity(intToHome);
                }

                else
                {
                    Toast.makeText(getApplicationContext(),"Fill All details",Toast.LENGTH_LONG).show();
                }
            }
        });
        mPhoneNo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(!validatePhoneNo()){
                    mSignUp_btn.setEnabled(false);
                } else {
                    mSignUp_btn.setEnabled(true);
                }

            }



            @Override
            public void afterTextChanged(Editable editable) {
                if(validatePhoneNo()){

                }
                else{
                    Toast.makeText(getApplicationContext(), "Invalid Phone Number", Toast.LENGTH_SHORT).show();
                }
            }
        });



        mDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                picker = new DatePickerDialog(Signup_Userinfo.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                mDOB.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });


        //-----------UploadFile------------

        storage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();

        selectFile = findViewById(R.id.selectFile);
        upload = findViewById(R.id.upload);
        notification = findViewById(R.id.notification);

        selectFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(ContextCompat.checkSelfPermission(Signup_Userinfo.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                    selectPdf();
                }
                else{
                    ActivityCompat.requestPermissions(Signup_Userinfo.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},9);
                }

            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(pdfUri != null){
                    uploadFile(pdfUri);
                }
                else{
                    Toast.makeText(Signup_Userinfo.this,"Select a File",Toast.LENGTH_SHORT).show();
                }

            }
        });
        //---------------------------------

    }



    private boolean validatePhoneNo(){
        String PHONE_PATTERN = "[789][0-9]{9}";
        String phoneno =  mPhoneNo.getText().toString().trim();

        Pattern phonePattern = Pattern.compile(PHONE_PATTERN);
        Matcher match = phonePattern.matcher(phoneno);

        return match.matches();
    }
    public  void onCertificate(View view){


        Intent it=new Intent(Signup_Userinfo.this,UploadCertificates.class);
        startActivity(it);

    }

    //---------------------UploadFile----------------------
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 9 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            selectPdf();
        }
        else{
            Toast.makeText(Signup_Userinfo.this,"Please Provide Permissions",Toast.LENGTH_SHORT).show();
        }
    }

    private void selectPdf(){

        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 86);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 86 && resultCode == RESULT_OK && data != null){
            pdfUri = data.getData();

            notification.setText(data.getData().getLastPathSegment());


        }
        else{
            Toast.makeText(Signup_Userinfo.this,"Please Select a File",Toast.LENGTH_SHORT).show();
        }
    }

    private void uploadFile(Uri pdfUri){
        Toast.makeText(Signup_Userinfo.this, "In upload file Uploaded", Toast.LENGTH_SHORT).show();
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgress(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("Uploading File...");
        progressDialog.setProgress(0);
        progressDialog.show();
        final String[] url1 = {""};

        //final String fileName = System.currentTimeMillis()+"";
        final String fileName = ekey+"Certi";
        StorageReference storageReference = storage.getReference();

        storageReference.child("Uploads").child(fileName).putFile(pdfUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        String url = taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();
                        //url1[0] =url;
                        url2=url;
                        DatabaseReference reference = database.getReference();
                        reference.child("Studentinfo").child("f1").child("certificateurl").setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {

                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()) {
                                    Toast.makeText(Signup_Userinfo.this, "File Successfully Uploaded", Toast.LENGTH_SHORT).show();
                                    progressDialog.hide();
                                }
                                else
                                    Toast.makeText(Signup_Userinfo.this, "File Not Uploaded", Toast.LENGTH_SHORT).show();
                            }
                        });

                        //studentInfo.setCertificateurl(url);
                        Toast.makeText(Signup_Userinfo.this, "File Successfully Uploaded", Toast.LENGTH_SHORT).show();
                        progressDialog.hide();


                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(Signup_Userinfo.this, "File Not Uploaded", Toast.LENGTH_SHORT).show();

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                int currentProgress = (int) (100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                progressDialog.setProgress(currentProgress);


            }
        });

       // studentInfo.setCertificateurl(url1[0]);

    }


    public void onBackPressed() {
        SharedPreferences sp=getSharedPreferences("sp1",MODE_PRIVATE);
        SharedPreferences.Editor ed=sp.edit();
        ed.putString("email","");
        ed.commit();
        Intent startMain = new Intent(this,MainActivity.class);
        //startMain.addCategory(Intent.CATEGORY_HOME);
        //startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }
    public boolean isConnected()
    {
        ConnectivityManager connectivityManager= (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo= connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
    //-----------------------------------------------------
}


