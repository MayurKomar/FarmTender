package com.example.farmtender.interfaces;

import com.example.farmtender.models.CoffeeModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CoffeeApi {
    @GET("coffee/hot")
    Call<List<CoffeeModel>> getCoffee();

}
