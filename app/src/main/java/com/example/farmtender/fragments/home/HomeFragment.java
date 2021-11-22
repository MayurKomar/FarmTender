package com.example.farmtender.fragments.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.farmtender.adapters.AuctionListAdapter;
import com.example.farmtender.databinding.FragmentHomeBinding;
import com.example.farmtender.models.Auction;
import com.example.farmtender.viewModels.HomeFragmentViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    public static final String TAG = "HomeFragment";
    List<Auction> auctionsList;
    AuctionListAdapter list;
    FragmentHomeBinding fragmentHomeBinding;
    HomeFragmentViewModel homeFragmentViewModel;
    int page = 1;
    int perPage = 3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false);
        auctionsList = new ArrayList<>();
//        getData(page,perPage);
        homeFragmentViewModel = new ViewModelProvider(getActivity()).get(HomeFragmentViewModel.class);
        homeFragmentViewModel.getAuctionListObserver().observe(getActivity(), new Observer<List<Auction>>() {
            @Override
            public void onChanged(List<Auction> auctions) {
                if(auctions!=null){
                    for(Auction auction : auctions){
                        auctionsList.add(auction);
                    }
                    fragmentHomeBinding.progress.setVisibility(View.GONE);
                    fragmentHomeBinding.listView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    list = new AuctionListAdapter(getActivity(), auctionsList);
                    list.setAuctionList(auctionsList);
                    fragmentHomeBinding.listView.setAdapter(list);
                }
            }
        });
        homeFragmentViewModel.makeApiCall(page,perPage);
        fragmentHomeBinding.listView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(!fragmentHomeBinding.listView.canScrollVertically(1)){
                    Log.d(TAG, "onScrolled: +Scrolled");
                    page++;
                    fragmentHomeBinding.progress.setVisibility(View.VISIBLE);
                    homeFragmentViewModel.makeApiCall(page,perPage);
                }
            }
        });
        return fragmentHomeBinding.getRoot();
    }

//    private void getData(int pageNumber,int recordPerPage) {
//        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://ft.webwingz.com.au/api-v2/").addConverterFactory(GsonConverterFactory.create()).build();
//        Apis auctionsApi = retrofit.create(Apis.class);
//        Call<AuctionsResponse> call = auctionsApi.getAuctions(pageNumber,recordPerPage);
//        call.enqueue(new Callback<AuctionsResponse>() {
//            @Override
//            public void onResponse(Call<AuctionsResponse> call, Response<AuctionsResponse> response) {
//                if(pageNumber<=response.body().getAuctionsData().getPagingData().get(0).getTotalpages()){
//                    for (int i = 0; i < response.body().getAuctionsData().getAuctions().size(); i++) {
//                        auctionsList.add(response.body().getAuctionsData().getAuctions().get(i));
//
//                    }
//                }else{
//                    return;
//                }
//                fragmentHomeBinding.progress.setVisibility(View.GONE);
//                fragmentHomeBinding.listView.setLayoutManager(new LinearLayoutManager(getActivity()));
//                list = new AuctionListAdapter(getActivity(), auctionsList);
//                fragmentHomeBinding.listView.setAdapter(list);
//            }
//
//            @Override
//            public void onFailure(Call<AuctionsResponse> call, Throwable t) {
//                Log.d(TAG, "onFailure: "+t.getMessage());
//            }
//        });
//
//    }
}