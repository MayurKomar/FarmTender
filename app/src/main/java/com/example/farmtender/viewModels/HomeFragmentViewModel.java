package com.example.farmtender.viewModels;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.farmtender.interfaces.Apis;
import com.example.farmtender.models.Auction;
import com.example.farmtender.models.AuctionsResponse;
import com.example.farmtender.network.RetroInstance;

import java.io.IOException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okio.BufferedSink;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragmentViewModel extends ViewModel {
    private int page = 1,perPage = 6;
    private MutableLiveData<List<Auction>> auctionList;
    
    public HomeFragmentViewModel() {
        auctionList = new MutableLiveData<>();
    }

    public MutableLiveData<List<Auction>> getAuctionListObserver() {
        return auctionList;
    }

    public void makeApiCall() {
        Apis apis = RetroInstance.getRetrofitClient().create(Apis.class);
        Call<AuctionsResponse> call = apis.getAuctions(page, perPage);
        call.enqueue(new Callback<AuctionsResponse>() {
            @Override
            public void onResponse(Call<AuctionsResponse> call, Response<AuctionsResponse> response) {
                if(page < response.body().getAuctionsData().getPagingData().get(0).getTotalpages()){
                    auctionList.postValue(response.body().getAuctionsData().getAuctions());
                    page++;
                }
            }

            @Override
            public void onFailure(Call<AuctionsResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: Failed");
            }
        });
    }

}
