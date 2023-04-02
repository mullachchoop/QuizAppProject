package com.mullachproject.projectquizapp;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.AppCompatButton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static com.mullachproject.projectquizapp.MainActivity.*;

public class Python extends AppCompatActivity {
    // intialization
    private TextView questionNum;
    private TextView question;
    private AppCompatButton option1, option2, option3, option4;
    private AppCompatButton nextBtn;
    private Timer quizTimer;
    private int totalTimeInMins = 1;
    private int seconds = 0;
    private ArrayList<QuestionsList> questions; // initialize questions as QuestionsList which saved as arraylist
    private int currentQuestionPosition = 0;
    private String selectedOptionByUser = "";

    // Overrides the oncreate method of the appcompatactivity class.
    // It sees the content view of the activty to the xml.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_python);

        // Initialize the variables by their corresponding IDs
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
        selectedTopicName.setText("PYTHON"); //display 'python' text

        //call the start timer function which sets the timer for the quiz
        startTimer(timer);

        new Thread(() -> {
            try {
                questions = new ArrayList<>();

                // to call the questions stored in QuestionBank class
                QuestionBank qbank = new QuestionBank();
                questions = qbank.getPythonQuestions(); // get the python questions from QuestionBank

                int allQuestions = questions.size();
                runOnUiThread(() -> {
                    if (allQuestions > 0) {         //get the data of the questions and it's answer from database
                        questionNum.setText((currentQuestionPosition + 1) + "/" + allQuestions);
                        question.setText(questions.get(0).getQuestion());
                        option1.setText(questions.get(0).getOption1());
                        option2.setText(questions.get(0).getOption2());
                        option3.setText(questions.get(0).getOption3());
                        option4.setText(questions.get(0).getOption4());
                    } else {        //error checking. If the apps don't get the data from database, this part won't run
                        option1.setVisibility(View.INVISIBLE);
                        option2.setVisibility(View.INVISIBLE);
                        option3.setVisibility(View.INVISIBLE);
                        option4.setVisibility(View.INVISIBLE);
                        nextBtn.setVisibility(View.INVISIBLE);
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        // For the option 1
        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedOptionByUser.isEmpty()) {
                    // turn the background to red if the option1 is wrong
                    selectedOptionByUser = option1.getText().toString();
                    option1.setBackgroundResource(R.drawable.wrong_bg_red);
                    option1.setTextColor(Color.WHITE);
                    revealAnswer();
                    questions.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
                }
            }
        });

        // For option 2
        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedOptionByUser.isEmpty()) {
                    selectedOptionByUser = option2.getText().toString();
                    option2.setBackgroundResource(R.drawable.wrong_bg_red);
                    option2.setTextColor(Color.WHITE);
                    revealAnswer();
                    questions.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
                }
            }
        });

        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedOptionByUser.isEmpty()) {
                    selectedOptionByUser = option3.getText().toString();
                    option3.setBackgroundResource(R.drawable.wrong_bg_red);
                    option3.setTextColor(Color.WHITE);
                    revealAnswer();
                    questions.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
                }
            }
        });

        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedOptionByUser.isEmpty()) {
                    selectedOptionByUser = option4.getText().toString();
                    option4.setBackgroundResource(R.drawable.wrong_bg_red);
                    option4.setTextColor(Color.WHITE);
                    revealAnswer();
                    questions.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
                }
            }
        });

        // Next button
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // If the selectedOptionByUser is empty, the application will toast a  message
                if (selectedOptionByUser.isEmpty()) {
                    Toast.makeText(Python.this, "Please select an option", Toast.LENGTH_SHORT).show();
                } else { // change to the next question if user has selected an answer
                    changeNextQuestion();
                }
            }
        });

        // Back Button - return to the main activity class
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quizTimer.purge();
                quizTimer.cancel();
                startActivity(new Intent(Python.this, MainActivity.class));
                finish();
            }
        });

    }

    //This section is to change to the next question
    private void changeNextQuestion() {
        currentQuestionPosition++;
        if ((currentQuestionPosition + 1) == questions.size()) {
            nextBtn.setText("Submit Quiz");
        }

        // as long as still within the array size, this part of code will be implemented
        if (currentQuestionPosition < questions.size()) {
            selectedOptionByUser = "";

            option1.setBackgroundResource(R.drawable.round_white_bg);
            option1.setTextColor(Color.parseColor("#1f6bb8"));

            option2.setBackgroundResource(R.drawable.round_white_bg);
            option2.setTextColor(Color.parseColor("#1f6bb8"));

            option3.setBackgroundResource(R.drawable.round_white_bg);
            option3.setTextColor(Color.parseColor("#1f6bb8"));

            option4.setBackgroundResource(R.drawable.round_white_bg);
            option4.setTextColor(Color.parseColor("#1f6bb8"));

            questionNum.setText((currentQuestionPosition + 1) + "/" + questions.size());
            question.setText(questions.get(currentQuestionPosition).getQuestion());
            option1.setText(questions.get(currentQuestionPosition).getOption1());
            option2.setText(questions.get(currentQuestionPosition).getOption2());
            option3.setText(questions.get(currentQuestionPosition).getOption3());
            option4.setText(questions.get(currentQuestionPosition).getOption4());

        } else {
            // this part will display the correct and incorrect answer that the user get
            Intent intent = new Intent(Python.this, QuizResults.class);
            intent.putExtra("correct", getCorrectAnswers());
            intent.putExtra("incorrect", getIncorrectAnswers());
            startActivity(intent);
            finish();
        }
    }

    // whenever the user click on this button, this section will start
    private void startTimer(TextView timerTextView) {
        quizTimer = new Timer();

        quizTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // If the timer has reached 00:00, 'time over' will be displayed
                if (seconds == 0 && totalTimeInMins == 0) {
                    quizTimer.purge();
                    quizTimer.cancel();
                    runOnUiThread(() -> {
                        Toast.makeText(Python.this, "Time Over", Toast.LENGTH_SHORT).show();
                    });
                    Intent intent = new Intent(Python.this, QuizResults.class);
                    intent.putExtra("correct", getCorrectAnswers());
                    intent.putExtra("incorrect", getIncorrectAnswers());
                    startActivity(intent);

                    finish();

                } else if (seconds == 0) {
                    totalTimeInMins--;
                    seconds = 59;
                } else {
                    seconds--;
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        String finalMinutes = String.valueOf(totalTimeInMins);
                        String finalSeconds = String.valueOf(seconds);

                        if (finalMinutes.length() == 1) {
                            finalMinutes = "0" + finalMinutes;
                        }

                        if (finalSeconds.length() == 1) {
                            finalSeconds = "0" + finalSeconds;
                        }

                        timerTextView.setText(finalMinutes + ":" + finalSeconds);
                    }
                });
            }
        }, 1000, 1000);
    }

    // calculating the correct answer
    private int getCorrectAnswers() {
        int correctAnswers = 0;

        //Check from the list. If user selected the same answer as the 'answer',
        // it will add the correctanswer count
        for (int i = 0; i < questions.size(); i++) {
            final String getUserSelectedAnswer = questions.get(i).getUserSelectedAnswer();
            final String getAnswer = questions.get(i).getAnswer();

            if (getUserSelectedAnswer.equals(getAnswer)) {
                correctAnswers++;

            }
        }
        return correctAnswers;
    }

    //Check from the list. If user selected the different answer from the 'answer',
    // it will add the incorrectanswer count
    private int getIncorrectAnswers() {
        int incorrectAnswers = 0;

        for (int i = 0; i < questions.size(); i++) {
            final String getUserSelectedAnswer = questions.get(i).getUserSelectedAnswer();
            final String getAnswer = questions.get(i).getAnswer();

            if (!getUserSelectedAnswer.equals(getAnswer)) {
                incorrectAnswers++;
            }
        }
        return incorrectAnswers;
    }

    //return to the mainactivity and stop the timer
    @Override
    public void onBackPressed() {

        quizTimer.purge();
        quizTimer.cancel();

        startActivity(new Intent(Python.this, MainActivity.class));
        finish();
    }

    // Calling this section will reveal the answer in green background
    private void revealAnswer() {
        final String getAnswer = questions.get(currentQuestionPosition).getAnswer();

        if (option1.getText().toString().equals(getAnswer)) {
            option1.setBackgroundResource(R.drawable.correct_bg_green);
            option1.setTextColor(Color.WHITE);
        } else if (option2.getText().toString().equals(getAnswer)) {
            option2.setBackgroundResource(R.drawable.correct_bg_green);
            option2.setTextColor(Color.WHITE);
        } else if (option3.getText().toString().equals(getAnswer)) {
            option3.setBackgroundResource(R.drawable.correct_bg_green);
            option3.setTextColor(Color.WHITE);
        } else if (option4.getText().toString().equals(getAnswer)) {
            option4.setBackgroundResource(R.drawable.correct_bg_green);
            option4.setTextColor(Color.WHITE);
        }
    }


}