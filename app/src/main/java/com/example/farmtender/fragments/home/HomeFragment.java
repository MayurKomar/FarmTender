package com.example.farmtender.fragments.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.farmtender.adapters.AuctionListAdapter;
import com.example.farmtender.databinding.FragmentHomeBinding;
import com.example.farmtender.interfaces.Apis;
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
    AuctionListAdapter list;
    FragmentHomeBinding fragmentHomeBinding;
    int page = 1;
    int perPage = 3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false);
        auctionsList = new ArrayList<>();
        getData(page,perPage);
        fragmentHomeBinding.listView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(!fragmentHomeBinding.listView.canScrollVertically(1)){
                    Log.d(TAG, "onScrolled: +Scrolled");
                    page++;
                    fragmentHomeBinding.progress.setVisibility(View.VISIBLE);
                    getData(page,perPage);
                }
            }
        });
        return fragmentHomeBinding.getRoot();
    }

    private void getData(int pageNumber,int recordPerPage) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://ft.webwingz.com.au/api-v2/").addConverterFactory(GsonConverterFactory.create()).build();
        Apis auctionsApi = retrofit.create(Apis.class);
        Call<AuctionsResponse> call = auctionsApi.getAuctions(pageNumber,recordPerPage);
        call.enqueue(new Callback<AuctionsResponse>() {
            @Override
            public void onResponse(Call<AuctionsResponse> call, Response<AuctionsResponse> response) {
                if(pageNumber<=response.body().getAuctionsData().getPagingData().get(0).getTotalpages()){
                    for (int i = 0; i < response.body().getAuctionsData().getAuctions().size(); i++) {
                        auctionsList.add(response.body().getAuctionsData().getAuctions().get(i));

                    }
                }else{
                    return;
                }
                fragmentHomeBinding.progress.setVisibility(View.GONE);
                fragmentHomeBinding.listView.setLayoutManager(new LinearLayoutManager(getActivity()));
                list = new AuctionListAdapter(getActivity(), auctionsList);
                fragmentHomeBinding.listView.setAdapter(list);
            }

            @Override
            public void onFailure(Call<AuctionsResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });

    }
}