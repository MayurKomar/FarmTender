package com.android.farmtender.au.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AuctionsData {
    @SerializedName("paging")
    private ArrayList<PagingData> pagingData;
    @SerializedName("auctions")
    private ArrayList<Auction> auctions;

    public ArrayList<PagingData> getPagingData() {
        return pagingData;
    }

    public ArrayList<Auction> getAuctions() {
        return auctions;
    }
}
