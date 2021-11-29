package com.android.farmtender.au.fragments.categories;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.android.farmtender.au.databinding.FragmentCategoriesBinding;

public class CategoriesFragment extends Fragment {

    FragmentCategoriesBinding categoriesBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        categoriesBinding = FragmentCategoriesBinding.inflate(getLayoutInflater());
        return categoriesBinding.getRoot();
    }
}