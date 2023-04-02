package com.mullachproject.projectquizapp;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    // initialize variables to be passed to other class to access the database
    static String DB_URL = "jdbc:mysql://db4free.net:3306/mullachdb";
    static String DB_USER = "mullachuser";
    static String DB_PASSWORD = "mullachpass";


    // start button
    // this code will lead the program to the startPage
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button startBtn = findViewById(R.id.btn_enter);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StartPage.class);
                startActivity(intent);
            }
        });


    }

}