package com.android.farmtender.au;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.android.farmtender.au.databinding.ActivityMainBinding;


public class SplashScreenActivity extends AppCompatActivity {


    ActivityMainBinding activityMainBinding;
    Handler handler;
    boolean isNew = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        getSupportActionBar().hide();
        SharedPreferences sharedPreferences = getSharedPreferences(IntroScreenActivity.SHARED_PREFS_KEY,MODE_PRIVATE);
        isNew = sharedPreferences.getBoolean(IntroScreenActivity.IS_NEW,true);
        handler= new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(isNew){
                    startActivity(new Intent(SplashScreenActivity.this,IntroScreenActivity.class));
                    finish();
                }else{
                    startActivity(new Intent(SplashScreenActivity.this,HomeScreenActivity.class));
                    finish();
                }
            }
        },3000);
    }
}