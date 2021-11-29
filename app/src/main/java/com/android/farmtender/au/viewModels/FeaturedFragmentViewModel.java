package com.android.farmtender.au.viewModels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.farmtender.au.interfaces.Apis;
import com.android.farmtender.au.models.AboutUsDescription;
import com.android.farmtender.au.models.AboutUsResponse;
import com.android.farmtender.au.network.RetroInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeaturedFragmentViewModel extends ViewModel {
    private MutableLiveData<AboutUsDescription> aboutUs;

    public FeaturedFragmentViewModel() {
        aboutUs = new MutableLiveData<AboutUsDescription>();
    }

    public MutableLiveData<AboutUsDescription> getAboutUsObserver() {
        return aboutUs;
    }

    public void getAboutUs() {
        Apis apis = RetroInstance.getRetrofitClient().create(Apis.class);
        Call<AboutUsResponse> call = apis.getAbout();
        call.enqueue(new Callback<AboutUsResponse>() {
            @Override
            public void onResponse(Call<AboutUsResponse> call, Response<AboutUsResponse> response) {
                aboutUs.postValue(response.body().getData().getAboutUs().get(0));
            }

            @Override
            public void onFailure(Call<AboutUsResponse> call, Throwable t) {
                Log.d("FeaturedViewModel", "onFailure: Failed");
            }
        });
    }
}
