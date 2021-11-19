package com.example.farmtender.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.farmtender.models.Auction;

import java.util.List;

public class HomeFragmentViewModel {
    private MutableLiveData<List<Auction>> auctions;

    public void init() {

    }

    public LiveData<List<Auction>> getAuctions (){
        return auctions;
    }
}
