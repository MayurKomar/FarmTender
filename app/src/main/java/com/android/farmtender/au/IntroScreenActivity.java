package com.android.farmtender.au;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.android.farmtender.au.adapters.SplashSliderAdapter;
import com.android.farmtender.au.databinding.ActivityIntroScreenBinding;

public class IntroScreenActivity extends AppCompatActivity {

    private SplashSliderAdapter sliderAdapter;
    public String[] splashTitles;
    ActivityIntroScreenBinding activityIntroScreenBinding;
    private final int LAST_SCREEN_POSITION =2;
    public static final String SHARED_PREFS_KEY= "shared";
    public static final String IS_NEW = "isNew";

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

        sliderAdapter = new SplashSliderAdapter(this, splashTitles);

        activityIntroScreenBinding.viewPager.setAdapter(sliderAdapter);
        activityIntroScreenBinding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position == LAST_SCREEN_POSITION){
                    activityIntroScreenBinding.next.setVisibility(View.GONE);
                    activityIntroScreenBinding.letsGo.setVisibility(View.VISIBLE);
                }else{
                    activityIntroScreenBinding.next.setVisibility(View.VISIBLE);
                    activityIntroScreenBinding.letsGo.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



        activityIntroScreenBinding.tabLayout.setupWithViewPager(activityIntroScreenBinding.viewPager);
    }

    private int getItem() {
        return activityIntroScreenBinding.viewPager.getCurrentItem();
    }

    public void onNextClick(View view) {
        activityIntroScreenBinding.viewPager.setCurrentItem(getItem() + 1, true);

    }

    public void onSkipClicked(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_KEY,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(IS_NEW,false);
        editor.apply();
        startActivity(new Intent(IntroScreenActivity.this, HomeScreenActivity.class));
        finish();
    }

}