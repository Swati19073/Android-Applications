package com.example.mt19073_assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MT19073_MainActivity extends AppCompatActivity {

    Button button_calculate, button_quiz, button_rate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mt19073_activity_main);

        button_calculate=(Button)findViewById(R.id.calculator);
        button_quiz=(Button)findViewById(R.id.quiz);
        button_rate=(Button)findViewById(R.id.rate_us);

        button_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1= new Intent(MT19073_MainActivity.this,MT19073_CalculatorActivity.class);
                startActivity(intent1);
            }
        });

    button_quiz.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent2= new Intent(MT19073_MainActivity.this, MT19073_QuizActivity.class);
            startActivity(intent2);
        }
    });

    button_rate.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent3= new Intent(MT19073_MainActivity.this, MT19073_NotesActivity.class);
            startActivity(intent3);
        }
    });
    }
}
