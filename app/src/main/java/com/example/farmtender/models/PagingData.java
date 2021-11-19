package com.example.farmtender.models;

public class PagingData {
    private int from;
    private int to;
    private int totalrecords;
    private int totalpages;
    private int currentpage;
    private int offset;
    private int length;
    private int remainingrecords;

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public int getTotalrecords() {
        return totalrecords;
    }

    public int getTotalpages() {
        return totalpages;
    }

    public int getCurrentpage() {
        return currentpage;
    }

    public int getOffset() {
        return offset;
    }

    public int getLength() {
        return length;
    }

    public int getRemainingrecords() {
        return remainingrecords;
    }
}
