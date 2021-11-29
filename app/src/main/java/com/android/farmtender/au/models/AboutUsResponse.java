package com.android.farmtender.au.models;

import com.google.gson.annotations.SerializedName;

public class AboutUsResponse {
    private boolean success;
    private String message;
    @SerializedName("data")
    private AboutUsData data;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public AboutUsData getData() {
        return data;
    }
}
