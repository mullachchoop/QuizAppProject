package com.mullachproject.projectquizapp;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;
import androidx.annotation.Nullable;

public class BGMMyService extends Service {

    // initialize MediaPlayer object
    MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override

    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, R.raw.quiz_bgm);
        mediaPlayer.setLooping(true); // Set looping
        mediaPlayer.setVolume(100,100);
    }

    // Start playing the music when service is started
    public int onStartCommand(Intent intent, int flags, int startId) {
        mediaPlayer.start();
        Toast.makeText(getApplicationContext(), "MUSIC ON",    Toast.LENGTH_SHORT).show();
        return startId;
    }
    public void onStart(Intent intent, int startId) {
    }

    // Stop playing the music when service is stopped
    @Override
    public void onDestroy() {
        Toast.makeText(getApplicationContext(), "MUSIC OFF",    Toast.LENGTH_SHORT).show();
        mediaPlayer.stop();
        mediaPlayer.release();
    }
    @Override
    public void onLowMemory() {
    }
}
