package com.mullachproject.projectquizapp;

import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;


public class Music extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        final Button musicOn = findViewById(R.id.btn_musicOn);
        final Button musicOff = findViewById(R.id.btn_musicOff);
        final Button exit = findViewById(R.id.btn_exit);

        Intent intentMusic = new Intent(Music.this, BGMMyService.class);
        Intent intentMainPage = new Intent(Music.this, StartPage.class);

        musicOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(intentMusic);
            }
        });

        musicOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(intentMusic);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentMainPage);
                finish();
            }
        });

    }
}
