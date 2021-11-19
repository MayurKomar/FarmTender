package com.example.farmtender;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.farmtender.adapters.SliderAdapter;
import com.example.farmtender.databinding.ActivityIntroScreenBinding;

public class IntroScreenActivity extends AppCompatActivity {

    private SliderAdapter sliderAdapter;
    public String[] splashTitles;
    ActivityIntroScreenBinding activityIntroScreenBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityIntroScreenBinding = ActivityIntroScreenBinding.inflate(getLayoutInflater());
        View view = activityIntroScreenBinding.getRoot();
        setContentView(view);
        getSupportActionBar().hide();


        splashTitles = new String[]{
                getResources().getString(R.string.splash_screen1Title),
                getResources().getString(R.string.splash_screen2Title),
                getResources().getString(R.string.splash_screen3Title),
        };

        sliderAdapter = new SliderAdapter(this, splashTitles);

        activityIntroScreenBinding.viewPager.setAdapter(sliderAdapter);

        activityIntroScreenBinding.tabLayout.setupWithViewPager(activityIntroScreenBinding.viewPager);
    }

    private int getItem() {
        return activityIntroScreenBinding.viewPager.getCurrentItem();
    }

    public void onNextClick(View view) {
        activityIntroScreenBinding.viewPager.setCurrentItem(getItem() + 1, true);
    }

    public void onSkipClicked(View view) {
        startActivity(new Intent(IntroScreenActivity.this, HomeScreenActivity.class));
        finish();
    }
}