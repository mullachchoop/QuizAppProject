package com.mullachproject.projectquizapp;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import java.sql.SQLException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class QuizActivity extends AppCompatActivity {

    /*
    protected TextView questionNum;
    protected TextView question;
    protected AppCompatButton option1, option2, option3, option4;
    protected AppCompatButton nextBtn;
    protected Timer quizTimer;
    protected int totalTimeInMins = 1;
    protected int seconds = 0;
    protected List<QuestionsList> questionsLists;
    protected int currentQuestionPosition = 0;
    protected String selectedOptionByUser = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        final ImageView backBtn = findViewById(R.id.ic_back);
        final TextView timer = findViewById(R.id.txt_timer);
        final TextView selectedTopicName = findViewById(R.id.txt_topic);

        questionNum = findViewById(R.id.txt_question);
        question = findViewById(R.id.question);
        option1 = findViewById(R.id.btn_opt1);
        option2 = findViewById(R.id.btn_opt2);
        option3 = findViewById(R.id.btn_opt3);
        option4 = findViewById(R.id.btn_opt4);
        nextBtn = findViewById(R.id.btn_next);

        final String getSelectedTopicName = getIntent().getStringExtra("selectedTopic");
        QuestionBank q = new QuestionBank();

        selectedTopicName.setText(getSelectedTopicName);

        try {
            questionsLists = q.getQuestions(getSelectedTopicName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        startTimer(timer);

        questionNum.setText((currentQuestionPosition+1)+"/"+questionsLists.size());
        question.setText(questionsLists.get(0).getQuestion());
        option1.setText(questionsLists.get(0).getOption1());
        option2.setText(questionsLists.get(0).getOption2());
        option3.setText(questionsLists.get(0).getOption3());
        option4.setText(questionsLists.get(0).getOption4());

        option1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                if (selectedOptionByUser.isEmpty()){
                    selectedOptionByUser = option1.getText().toString();

                    option1.setBackgroundResource(R.drawable.wrong_bg_red);
                    option1.setTextColor(Color.WHITE);

                    revealAnswer();

                    questionsLists.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
                }
            }
        });

        option2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (selectedOptionByUser.isEmpty()){
                    selectedOptionByUser = option2.getText().toString();

                    option2.setBackgroundResource(R.drawable.wrong_bg_red);
                    option2.setTextColor(Color.WHITE);

                    revealAnswer();

                    questionsLists.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
                }

            }
        });

        option3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (selectedOptionByUser.isEmpty()){
                    selectedOptionByUser = option3.getText().toString();

                    option3.setBackgroundResource(R.drawable.wrong_bg_red);
                    option3.setTextColor(Color.WHITE);

                    revealAnswer();

                    questionsLists.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
                }
            }
        });

        option4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (selectedOptionByUser.isEmpty()){
                    selectedOptionByUser = option4.getText().toString();

                    option4.setBackgroundResource(R.drawable.wrong_bg_red);
                    option4.setTextColor(Color.WHITE);

                    revealAnswer();

                    questionsLists.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
                }
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedOptionByUser.isEmpty()){
                    Toast.makeText(QuizActivity.this, "Please select an option", Toast.LENGTH_SHORT).show();

                }
                else {
                    changeNextQuestion();
                }
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quizTimer.purge();
                quizTimer.cancel();

                startActivity(new Intent(QuizActivity.this, MainActivity.class));
                finish();
            }
        });

    }
    protected void changeNextQuestion(){
        currentQuestionPosition++;

        if ((currentQuestionPosition+1) == questionsLists.size()){
            nextBtn.setText("Submit Quiz");

        }

        if (currentQuestionPosition < questionsLists.size()){

            selectedOptionByUser = "";

            option1.setBackgroundResource(R.drawable.round_white_bg);
            option1.setTextColor(Color.parseColor("#1f6bb8"));

            option2.setBackgroundResource(R.drawable.round_white_bg);
            option2.setTextColor(Color.parseColor("#1f6bb8"));

            option3.setBackgroundResource(R.drawable.round_white_bg);
            option3.setTextColor(Color.parseColor("#1f6bb8"));

            option4.setBackgroundResource(R.drawable.round_white_bg);
            option4.setTextColor(Color.parseColor("#1f6bb8"));

            questionNum.setText((currentQuestionPosition+1)+"/"+questionsLists.size());
            question.setText(questionsLists.get(currentQuestionPosition).getQuestion());
            option1.setText(questionsLists.get(currentQuestionPosition).getOption1());
            option2.setText(questionsLists.get(currentQuestionPosition).getOption2());
            option3.setText(questionsLists.get(currentQuestionPosition).getOption3());
            option4.setText(questionsLists.get(currentQuestionPosition).getOption4());

        }
        else {
            Intent intent = new Intent(QuizActivity.this, QuizResults.class);
            intent.putExtra("correct", getCorrectAnswers());
            intent.putExtra("incorrect", getIncorrectAnswers());
            startActivity(intent);

            finish();
        }
    }
    protected void startTimer(TextView timerTextView){
        quizTimer = new Timer();

        quizTimer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){

                if(seconds  == 0 && totalTimeInMins == 0){
                    quizTimer.purge();
                    quizTimer.cancel();

                    Toast.makeText(QuizActivity.this, "Time Over", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(QuizActivity.this, QuizResults.class);
                    intent.putExtra("correct", getCorrectAnswers());
                    intent.putExtra("incorrect", getIncorrectAnswers());
                    startActivity(intent);

                    finish();

                }
                else if (seconds == 0){
                    totalTimeInMins--;
                    seconds = 59;
                }
                else {
                    seconds--;
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {


                        String finalMinutes = String.valueOf(totalTimeInMins);
                        String finalSeconds = String.valueOf(seconds);

                        if (finalMinutes.length() == 1){
                            finalMinutes = "0" + finalMinutes;
                        }

                        if (finalSeconds.length() == 1){
                            finalSeconds = "0" + finalSeconds;
                        }

                        timerTextView.setText(finalMinutes + ":" + finalSeconds);
                    }
                });
            }
        }, 1000, 1000);
    }

    protected int getCorrectAnswers(){
        int correctAnswers = 0;

        for(int i= 0; i<questionsLists.size();i++){
            final String getUserSelectedAnswer = questionsLists.get(i).getUserSelectedAnswer();
            final String getAnswer = questionsLists.get(i).getAnswer();

            if (getUserSelectedAnswer.equals(getAnswer)){
                correctAnswers++;

            }
        }
        return correctAnswers;
    }

    protected int getIncorrectAnswers(){
        int incorrectAnswers = 0;

        for(int i= 0; i<questionsLists.size();i++){
            final String getUserSelectedAnswer = questionsLists.get(i).getUserSelectedAnswer();
            final String getAnswer = questionsLists.get(i).getAnswer();

            if (!getUserSelectedAnswer.equals(getAnswer)){
                incorrectAnswers++;
            }
        }
        return incorrectAnswers;
    }

    @Override
    public void onBackPressed() {

        quizTimer.purge();
        quizTimer.cancel();

        startActivity(new Intent(QuizActivity.this, MainActivity.class));
        finish();
    }
    protected void revealAnswer(){
        final String getAnswer = questionsLists.get(currentQuestionPosition).getAnswer();

        if (option1.getText().toString().equals(getAnswer)){
            option1.setBackgroundResource(R.drawable.correct_bg_green);
            option1.setTextColor(Color.WHITE);
        }
        else if (option2.getText().toString().equals(getAnswer)){
            option2.setBackgroundResource(R.drawable.correct_bg_green);
            option2.setTextColor(Color.WHITE);
        }
        else if (option3.getText().toString().equals(getAnswer)){
            option3.setBackgroundResource(R.drawable.correct_bg_green);
            option3.setTextColor(Color.WHITE);
        }
        else if (option4.getText().toString().equals(getAnswer)){
            option4.setBackgroundResource(R.drawable.correct_bg_green);
            option4.setTextColor(Color.WHITE);
        }
    }

     */
}