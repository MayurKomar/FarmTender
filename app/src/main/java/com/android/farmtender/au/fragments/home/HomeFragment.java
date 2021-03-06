package com.android.farmtender.au.fragments.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.farmtender.au.adapters.AuctionListAdapter;
import com.android.farmtender.au.databinding.FragmentHomeBinding;
import com.android.farmtender.au.models.Auction;
import com.android.farmtender.au.viewModels.HomeFragmentViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";
    public List<Auction> auctionsList = new ArrayList<>();
    AuctionListAdapter list;
    FragmentHomeBinding fragmentHomeBinding;
    HomeFragmentViewModel homeFragmentViewModel;
    LinearLayoutManager linearLayoutManager;
    


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        Log.d(TAG, "onViewCreated: " + auctionsList.size());
        homeFragmentViewModel = new ViewModelProvider(getActivity()).get(HomeFragmentViewModel.class);
        linearLayoutManager = new LinearLayoutManager(getContext());
        homeFragmentViewModel.getAuctionListObserver().observe(getActivity(), new Observer<List<Auction>>() {
            @Override
            public void onChanged(List<Auction> auctions) {
                if (auctions != null) {
                    for (Auction auction : auctions) {
                        auctionsList.add(auction);
                    }
                }
                fragmentHomeBinding.progress.setVisibility(View.GONE);
                if (getContext() != null) {
                    fragmentHomeBinding.listView.setLayoutManager(linearLayoutManager);
                    list = new AuctionListAdapter(getContext(), auctionsList);
                }
                list.setAuctionList(auctionsList);
                fragmentHomeBinding.listView.setAdapter(list);
            }
        });

        if(homeFragmentViewModel.getAuctionListObserver().getValue()==null){
            Log.d(TAG, "onViewCreated: Load");
            homeFragmentViewModel.makeApiCall();
        }

        fragmentHomeBinding.listView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if (!fragmentHomeBinding.listView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    fragmentHomeBinding.progress.setVisibility(View.VISIBLE);
                    homeFragmentViewModel.makeApiCall();
                    fragmentHomeBinding.progress.setVisibility(View.GONE);
                }
                super.onScrollStateChanged(recyclerView, newState);

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false);

        return fragmentHomeBinding.getRoot();
    }
}