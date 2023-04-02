package com.mullachproject.projectquizapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

public class Theme extends AppCompatActivity{    // defines the Theme class, which extends the AppCompatActivity class
    SwitchCompat switchMode;
    boolean nightMode;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);  // overridden to set to the layout for the activity, get references to the SwitchCompat and Button views
        setContentView(R.layout.activity_theme);

        switchMode = findViewById(R.id.switchMode);
        Button exit = findViewById(R.id.btn_exit);
        Intent intentMainPage = new Intent(Theme.this, StartPage.class);


        sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE);
        nightMode = sharedPreferences.getBoolean("nightMode", false);
        // sets the nightMode variable  to the boolean value stored under the nightMode value

        if (nightMode){
            switchMode.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        switchMode.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(nightMode){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("nightMode", false);
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("nightMode", true);
                }
                editor.apply();
            }
        });

        exit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(intentMainPage);
                finish();
            }
        });




    }


}
