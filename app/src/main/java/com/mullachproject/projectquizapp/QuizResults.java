package com.mullachproject.projectquizapp;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.AppCompatButton;

public class QuizResults extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_results);

        // get references to UI elements
        final AppCompatButton startNewBtn = findViewById(R.id.startNewQuizBtn);
        final TextView correctAnswer = findViewById(R.id.correctAnswers);
        final TextView incorrectAnswers = findViewById(R.id.incorrectAnswers);

        // get results from previous activity
        final int getCorrectAnswers = getIntent().getIntExtra("correct", 0);
        final int getIncorrectAnswers = getIntent().getIntExtra("incorrect",0);

        // display results on UI
        String correctAns = "Correct answers: " + String.valueOf(getCorrectAnswers);
        String incorrectAns = "Incorrect answers: " + String.valueOf(getIncorrectAnswers);


        correctAnswer.setText(correctAns);
        incorrectAnswers.setText(incorrectAns);

        //incorrectAnswers.setText(String.valueOf(getIncorrectAnswers));

        // set click listener on start new quiz button
        startNewBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                startActivity(new Intent(QuizResults.this, StartPage.class));
                finish();
            }
        });
    }
    // override back button press to go to start page
    @Override
    public void onBackPressed(){
        startActivity(new Intent(QuizResults.this, StartPage.class));
        finish();
    }
}
