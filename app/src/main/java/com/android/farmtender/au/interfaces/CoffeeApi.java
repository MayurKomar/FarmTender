package com.android.farmtender.au.interfaces;

import com.android.farmtender.au.models.CoffeeModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CoffeeApi {
    @GET("coffee/hot")
    Call<List<CoffeeModel>> getCoffee();

}
