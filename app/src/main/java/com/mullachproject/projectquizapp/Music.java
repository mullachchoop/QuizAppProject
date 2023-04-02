package com.mullachproject.projectquizapp;

import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;


public class Music extends AppCompatActivity {

    // Set content view
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        // initialize button for music on, music off and exit button
        final Button musicOn = findViewById(R.id.btn_musicOn);
        final Button musicOff = findViewById(R.id.btn_musicOff);
        final Button exit = findViewById(R.id.btn_exit);

        // Start "BGMMyService" activity
        Intent intentMusic = new Intent(Music.this, BGMMyService.class);

        // Return to "StartPage" activity
        Intent intentMainPage = new Intent(Music.this, StartPage.class);

        // Turn music on when button is clicked
        musicOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(intentMusic);
            }
        });


        // Turn off music when button is clicked
        musicOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(intentMusic);
            }
        });


        // Exit the Music interface when button is clicked
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentMainPage);
                finish();
            }
        });

    }
}
