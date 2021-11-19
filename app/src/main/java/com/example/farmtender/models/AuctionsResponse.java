package com.example.farmtender.models;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class AuctionsResponse {
    private boolean success;
    private String message;
    @SerializedName("data")
    private AuctionsData auctionsData;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public AuctionsData getAuctionsData() {
        return auctionsData;
    }
}
