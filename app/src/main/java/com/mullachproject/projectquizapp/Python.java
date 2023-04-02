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
    String tableName = "pythonQuestion";
    private TextView questionNum;
    private TextView question;
    private AppCompatButton option1, option2, option3, option4;
    private AppCompatButton nextBtn;
    private Timer quizTimer;
    private int totalTimeInMins = 1;
    private int seconds = 0;
    private ArrayList<QuestionsList> questions;
    private int currentQuestionPosition = 0;
    private String selectedOptionByUser = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_python);

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
        selectedTopicName.setText("PYTHON");

        startTimer(timer);


        new Thread(() -> {
            try {
                questions = new ArrayList<>();

                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

                Statement statement = connection.createStatement();
                String query = "select * from " + tableName + " ORDER BY RAND() LIMIT 5";
                ResultSet rs = statement.executeQuery(query);

                while (rs.next()) {
                    QuestionsList question = new QuestionsList(
                            rs.getString("question"),
                            rs.getString("option1"),
                            rs.getString("option2"),
                            rs.getString("option3"),
                            rs.getString("option4"),
                            rs.getString("answer"),
                            rs.getString("userSelectedAnswer"));
                    questions.add(question);
                }

                int allQuestions = questions.size();
                runOnUiThread(() -> {
                    if (allQuestions > 0) {
                        questionNum.setText((currentQuestionPosition + 1) + "/" + allQuestions);
                        question.setText(questions.get(0).getQuestion());
                        option1.setText(questions.get(0).getOption1());
                        option2.setText(questions.get(0).getOption2());
                        option3.setText(questions.get(0).getOption3());
                        option4.setText(questions.get(0).getOption4());
                    } else {
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


        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedOptionByUser.isEmpty()) {
                    selectedOptionByUser = option1.getText().toString();
                    option1.setBackgroundResource(R.drawable.wrong_bg_red);
                    option1.setTextColor(Color.WHITE);
                    revealAnswer();
                    questions.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
                }
            }
        });

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

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedOptionByUser.isEmpty()) {
                    Toast.makeText(Python.this, "Please select an option", Toast.LENGTH_SHORT).show();
                } else {
                    changeNextQuestion();
                }
            }
        });

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

    private void changeNextQuestion() {
        currentQuestionPosition++;
        if ((currentQuestionPosition + 1) == questions.size()) {
            nextBtn.setText("Submit Quiz");
        }

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
            Intent intent = new Intent(Python.this, QuizResults.class);
            intent.putExtra("correct", getCorrectAnswers());
            intent.putExtra("incorrect", getIncorrectAnswers());
            startActivity(intent);
            finish();
        }
    }

    private void startTimer(TextView timerTextView) {
        quizTimer = new Timer();

        quizTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

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

    private int getCorrectAnswers() {
        int correctAnswers = 0;

        //CEK DARI QUESTIONLIST
        for (int i = 0; i < questions.size(); i++) {
            final String getUserSelectedAnswer = questions.get(i).getUserSelectedAnswer();
            final String getAnswer = questions.get(i).getAnswer();

            if (getUserSelectedAnswer.equals(getAnswer)) {
                correctAnswers++;

            }
        }
        return correctAnswers;
    }

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

    @Override
    public void onBackPressed() {

        quizTimer.purge();
        quizTimer.cancel();

        startActivity(new Intent(Python.this, MainActivity.class));
        finish();
    }

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