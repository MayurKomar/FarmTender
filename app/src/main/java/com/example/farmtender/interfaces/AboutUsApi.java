package com.example.farmtender.interfaces;

import com.example.farmtender.models.AboutUsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface AboutUsApi {
    @Headers("x-api-key: FT@V2sb2#dYPa$zN")
    @GET("pages/about_us")
    Call<AboutUsResponse> getAbout();
}
