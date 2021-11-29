package com.android.farmtender.au.adapters;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.android.farmtender.au.R;
import com.android.farmtender.au.models.Auction;

import java.util.List;

public class AuctionListAdapter extends RecyclerView.Adapter<AuctionListAdapter.ViewHolder> {

    LayoutInflater layoutInflater;
    List<Auction> auctionList;
    Context context;

    public void setAuctionList(List<Auction> auctions ){
        this.auctionList = auctions;
        notifyDataSetChanged();
    }

    public AuctionListAdapter(Context context, List<Auction> beersList) {
        this.layoutInflater = LayoutInflater.from(context);
        this.auctionList = beersList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(auctionList.get(position).getNo());
        holder.price.setText(auctionList.get(position).getId());
        if (!auctionList.get(position).getImage().isEmpty()) {
            Glide.with(context).load(auctionList.get(position).getImage()).into(holder.postImage);
        }
        else{
            Glide.with(context).load("https://image.shutterstock.com/image-photo/22052021-riga-latvia-powerful-engine-600w-1978243382.jpg").into(holder.postImage);
        }

    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: " + auctionList.size());
        return auctionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, price;
        ImageView postImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.postTitle);
            price = itemView.findViewById(R.id.postPrice);
            postImage = itemView.findViewById(R.id.postImage);
        }
    }
}
