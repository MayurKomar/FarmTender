package com.example.farmtender.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.farmtender.R;
import com.google.android.material.slider.Slider;

public class SplashSliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;
    String [] splashTitles;

    public SplashSliderAdapter(Context context, String [] splashTitles) {
        this.context = context;
        this.splashTitles = splashTitles;
    }

    @Override
    public int getCount() {
        return splashTitles.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_item,container,false);
        TextView textView = view.findViewById(R.id.splashTextView);
        textView.setText(splashTitles[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
