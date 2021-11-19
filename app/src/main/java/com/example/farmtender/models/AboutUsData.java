package com.example.farmtender.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class AboutUsData {

    @SerializedName("about_us")
    private ArrayList<AboutUsDescription> aboutUs;
    private String url;

    public ArrayList<AboutUsDescription> getAboutUs() {
        return aboutUs;
    }

    public String getUrl() {
        return url;
    }
}
