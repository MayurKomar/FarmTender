package com.example.farmtender.models;

import com.google.gson.annotations.SerializedName;

public class AboutUsDescription {
    @SerializedName("description")
    private String description;

    public String getDescription() {
        return description;
    }
}
