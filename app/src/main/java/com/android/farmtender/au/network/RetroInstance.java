package com.android.farmtender.au.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroInstance {
    public static final String BASE_URL = "https://ft.webwingz.com.au/api-v2/";

    public static Retrofit retrofit;

    public static Retrofit getRetrofitClient() {
        if(retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
