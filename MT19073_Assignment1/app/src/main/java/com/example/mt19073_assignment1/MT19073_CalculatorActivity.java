package com.example.mt19073_assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MT19073_CalculatorActivity extends AppCompatActivity {
    private EditText number1,number2;
    private Button add_button, subtract_button,multiply_button,divide_button;
    float num1,num2, sum, diff, mul,div;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mt19073__calculator);

        number1=(EditText)findViewById(R.id.edit_text1);
        number2=(EditText)findViewById(R.id.edit_text2);
        add_button=(Button)findViewById(R.id.button_add);
        subtract_button=(Button)findViewById(R.id.button_subtract);
        multiply_button=(Button)findViewById(R.id.button_multiply);
        divide_button=(Button)findViewById((R.id.button_divide));

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 num1= Integer.parseInt(number1.getText().toString());
                num2= Integer.parseInt(number2.getText().toString());
                sum=num1+num2;

                Toast.makeText(getApplicationContext(),"Adiition of two numbers is:" +String.valueOf(sum),Toast.LENGTH_LONG).show();
            }
        });

        subtract_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1= Integer.parseInt(number1.getText().toString());
                num2= Integer.parseInt(number2.getText().toString());
                diff=num1-num2;

                Toast.makeText(getApplicationContext(),"Subtraction of two numbers is:" +String.valueOf(diff),Toast.LENGTH_LONG).show();
            }
        });

        multiply_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1= Integer.parseInt(number1.getText().toString());
                num2= Integer.parseInt(number2.getText().toString());
                mul=num1*num2;

                Toast.makeText(getApplicationContext(),"Multiplication of two numbers is:" +String.valueOf(mul),Toast.LENGTH_LONG).show();
            }
        });

        divide_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1= Integer.parseInt(number1.getText().toString());
                num2= Integer.parseInt(number2.getText().toString());
                div=num1/num2;

                Toast.makeText(getApplicationContext(),"Division of two numbers is:" +String.valueOf(div),Toast.LENGTH_LONG).show();
            }
        });
    }
}
