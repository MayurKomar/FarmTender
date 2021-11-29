package com.android.farmtender.au.models;

public class Auction {
    private String id;
    private String no;
    private String name;
    private String starts;
    private String ends;
    private String status;
    private String status_text;
    private String ap_url;
    private String image;

    public String getId() {
        return id;
    }

    public String getNo() {
        return no;
    }

    public String getName() {
        return name;
    }

    public String getStarts() {
        return starts;
    }

    public String getEnds() {
        return ends;
    }

    public String getStatus() {
        return status;
    }

    public String getStatus_text() {
        return status_text;
    }

    public String getAp_url() {
        return ap_url;
    }

    public String getImage() {
        return image;
    }
}
