package com.example.farmtender.fragments.featured;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.farmtender.databinding.FragmentFeaturedBinding;
import com.example.farmtender.models.AboutUsDescription;
import com.example.farmtender.models.AboutUsResponse;
import com.example.farmtender.viewModels.FeaturedFragmentViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeaturedFragment extends Fragment {

    FragmentFeaturedBinding featuredBinding;
    public static final String TAG = "Featured";
    private String webPageCode;
    FeaturedFragmentViewModel featuredFragmentViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        featuredBinding = FragmentFeaturedBinding.inflate(getLayoutInflater());
        featuredFragmentViewModel = new ViewModelProvider(getActivity()).get(FeaturedFragmentViewModel.class);
        if(featuredFragmentViewModel.getAboutUsObserver().getValue() == null){
            featuredFragmentViewModel.getAboutUs();
        }
        if (savedInstanceState != null) {
            Log.d(TAG, "onCreateView: loaded");
            featuredBinding.webView.restoreState(savedInstanceState);
        }
        else{
            featuredFragmentViewModel.getAboutUsObserver().observe(getActivity(), new Observer<AboutUsDescription>() {
                @Override
                public void onChanged(AboutUsDescription aboutUsDescription) {
                    featuredBinding.webView.loadData(aboutUsDescription.getDescription(), "text/html", "UTF-8");
                }
            });
        }
        return featuredBinding.getRoot();
    }

    Callback<AboutUsResponse> webViewCallback = new Callback<AboutUsResponse>() {
        @Override
        public void onResponse(Call<AboutUsResponse> call, Response<AboutUsResponse> response) {
            featuredBinding.webView.loadData(response.body().getData().getAboutUs().get(0).getDescription(), "text/html", "UTF-8");
        }

        @Override
        public void onFailure(Call<AboutUsResponse> call, Throwable t) {
            Log.d(TAG, "onFailure: " + t.getMessage());
        }
    };


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: saved");
        featuredBinding.webView.saveState(outState);
    }
}