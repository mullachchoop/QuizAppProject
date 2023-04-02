package com.mullachproject.projectquizapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import com.mullachproject.projectquizapp.databinding.ActivityMainBinding;
import com.mullachproject.projectquizapp.databinding.ActivityStartpageBinding;

import java.util.ArrayList;

public class StartPage extends AppCompatActivity {
    private String selectedTopicName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startpage);

        final ImageView java = findViewById(R.id.btn_Java);
        final ImageView c = findViewById(R.id.btn_c);
        final ImageView vb = findViewById(R.id.btn_vb);
        final ImageView python = findViewById(R.id.btn_python);
        final Button startBtn = findViewById(R.id.btn_start);
        final ImageView music = findViewById(R.id.music);
        final ImageView theme = findViewById(R.id.theme);

        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Music.class);
                startActivity(i);
                finish();
            }
        });

        theme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Theme.class);
                startActivity(i);
                finish();
            }
        });

        java.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                selectedTopicName = "JAVA";
                java.setBackgroundResource(R.drawable.round_green_bgstroke);
                c.setBackgroundResource(R.drawable.round_blueblack_bg);
                vb.setBackgroundResource(R.drawable.round_blueblack_bg);
                python.setBackgroundResource(R.drawable.round_blueblack_bg);
            }
        });


        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                selectedTopicName = "C++";
                c.setBackgroundResource(R.drawable.round_green_bgstroke);
                java.setBackgroundResource(R.drawable.round_blueblack_bg);
                vb.setBackgroundResource(R.drawable.round_blueblack_bg);
                python.setBackgroundResource(R.drawable.round_blueblack_bg);
                ArrayList<QuestionsList> questions = new ArrayList<>();

            }
        });


        vb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                selectedTopicName = "VISUAL BASIC";
                vb.setBackgroundResource(R.drawable.round_green_bgstroke);
                c.setBackgroundResource(R.drawable.round_blueblack_bg);
                java.setBackgroundResource(R.drawable.round_blueblack_bg);
                python.setBackgroundResource(R.drawable.round_blueblack_bg);

            }
        });

        python.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                selectedTopicName = "PYTHON";
                python.setBackgroundResource(R.drawable.round_green_bgstroke);
                c.setBackgroundResource(R.drawable.round_blueblack_bg);
                vb.setBackgroundResource(R.drawable.round_blueblack_bg);
                java.setBackgroundResource(R.drawable.round_blueblack_bg);

            }
        });

        startBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(selectedTopicName.isEmpty()){
                    Toast.makeText(StartPage.this, "Please select the topic", Toast.LENGTH_SHORT).show();
                }
                else if(selectedTopicName.equals("JAVA")){
                    Intent intent = new Intent(StartPage.this, Java.class);
                    startActivity(intent);
                }
                else if(selectedTopicName.equals("C++")){
                    Intent intent = new Intent(StartPage.this, CPlusPlus.class);

                    startActivity(intent);
                }
                else if(selectedTopicName.equals("PYTHON")){
                    Intent intent = new Intent(StartPage.this, Python.class);

                    startActivity(intent);
                }
                else if(selectedTopicName.equals("VISUAL BASIC")){
                    Intent intent = new Intent(StartPage.this, VB.class);
                    startActivity(intent);
                }
            }
        });

    }}
