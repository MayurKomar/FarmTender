package com.example.farmtender.fragments.featured;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.farmtender.databinding.FragmentFeaturedBinding;
import com.example.farmtender.interfaces.AboutUsApi;
import com.example.farmtender.models.AboutUsResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FeaturedFragment extends Fragment {

    FragmentFeaturedBinding featuredBinding;
    public static final String TAG = "Featured";
    private String webPageCode;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        featuredBinding = FragmentFeaturedBinding.inflate(getLayoutInflater());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ft.webwingz.com.au/api-v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AboutUsApi aboutUsApi = retrofit.create(AboutUsApi.class);
        Call<AboutUsResponse> call = aboutUsApi.getAbout();
        call.enqueue(webViewCallback);
        return featuredBinding.getRoot();
    }

    Callback<AboutUsResponse> webViewCallback = new Callback<AboutUsResponse>() {
        @Override
        public void onResponse(Call<AboutUsResponse> call, Response<AboutUsResponse> response) {
            featuredBinding.webView.loadData(response.body().getData().getAboutUs().get(0).getDescription(),"text/html","UTF-8");
        }

        @Override
        public void onFailure(Call<AboutUsResponse> call, Throwable t) {
            Log.d(TAG, "onFailure: " + t.getMessage());
        }
    };


}