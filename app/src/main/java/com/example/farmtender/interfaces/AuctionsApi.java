package com.example.farmtender.interfaces;

import com.example.farmtender.models.AuctionsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface AuctionsApi {
    @Headers("x-api-key: FT@V2sb2#dYPa$zN")
    @GET("auctions")
    Call<AuctionsResponse> getAuctions();
}
