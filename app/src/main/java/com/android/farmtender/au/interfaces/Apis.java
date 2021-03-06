package com.android.farmtender.au.interfaces;

import com.android.farmtender.au.models.AboutUsResponse;
import com.android.farmtender.au.models.AuctionsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface Apis {
    @Headers("x-api-key: FT@V2sb2#dYPa$zN")
    @GET("pages/about_us")
    Call<AboutUsResponse> getAbout();

    @Headers("x-api-key: FT@V2sb2#dYPa$zN")
    @GET("auctions?auction=past")
    Call<AuctionsResponse> getAuctions(@Query("page")int page, @Query("per_page")int perPage);
}
