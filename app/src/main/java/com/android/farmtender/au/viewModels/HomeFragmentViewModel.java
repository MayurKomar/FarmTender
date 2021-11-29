package com.android.farmtender.au.viewModels;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.farmtender.au.interfaces.Apis;
import com.android.farmtender.au.models.Auction;
import com.android.farmtender.au.models.AuctionsResponse;
import com.android.farmtender.au.network.RetroInstance;

import java.util.List;

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
