package com.example.farmtender.interfaces;

import com.example.farmtender.models.AboutUsResponse;
import com.example.farmtender.models.AuctionsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface Apis {
    @Headers("x-api-key: FT@V2sb2#dYPa$zN")
    @GET("pages/about_us")
    Call<AboutUsResponse> getAbout();

    @Headers("x-api-key: FT@V2sb2#dYPa$zN")
    @GET("auctions")
    Call<AuctionsResponse> getAuctions(@Query("page")int page, @Query("per_page")int perPage);
}
