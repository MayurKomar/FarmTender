package com.example.farmtender.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Map;

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
