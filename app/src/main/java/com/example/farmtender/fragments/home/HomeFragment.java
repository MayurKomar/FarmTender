package com.example.farmtender.fragments.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.farmtender.adapters.ListAdapter;
import com.example.farmtender.databinding.FragmentHomeBinding;
import com.example.farmtender.interfaces.AuctionsApi;
import com.example.farmtender.models.Auction;
import com.example.farmtender.models.AuctionsResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    public static final String TAG = "HomeFragment";
    ArrayList<Auction> auctionsList;
    ListAdapter list;
    FragmentHomeBinding fragmentHomeBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false);
        auctionsList = new ArrayList<>();
        getData();
        return fragmentHomeBinding.getRoot();
    }

    private void getData() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://ft.webwingz.com.au/api-v2/").addConverterFactory(GsonConverterFactory.create()).build();
        AuctionsApi auctionsApi = retrofit.create(AuctionsApi.class);
        Call<AuctionsResponse> call = auctionsApi.getAuctions();
        call.enqueue(new Callback<AuctionsResponse>() {
            @Override
            public void onResponse(Call<AuctionsResponse> call, Response<AuctionsResponse> response) {
                for (int i = 0; i < response.body().getAuctionsData().getAuctions().size(); i++) {
                    auctionsList.add(response.body().getAuctionsData().getAuctions().get(i));

                }
                fragmentHomeBinding.listView.setLayoutManager(new LinearLayoutManager(getActivity()));
                list = new ListAdapter(getActivity(), auctionsList);
                fragmentHomeBinding.listView.setAdapter(list);
            }

            @Override
            public void onFailure(Call<AuctionsResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });

    }
}