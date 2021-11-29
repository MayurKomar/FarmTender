package com.android.farmtender.au.fragments.listings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.android.farmtender.au.databinding.FragmentListingsBinding;

public class ListingsFragment extends Fragment {

    FragmentListingsBinding listingsBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        listingsBinding = FragmentListingsBinding.inflate(getLayoutInflater());
        return listingsBinding.getRoot();
    }
}