package com.example.a2019.ecomerceapp.Admin.Fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a2019.ecomerceapp.Admin.Activiteis.AddCategory;
import com.example.a2019.ecomerceapp.Admin.Models.CategoryModel;
import com.example.a2019.ecomerceapp.Admin.ViewModel.CategoryFragmentVm;
import com.example.a2019.ecomerceapp.R;

import java.util.List;


public class Categories extends Fragment {

    FloatingActionButton button;
    CategoryFragmentVm MyViewModel ;


    public Categories() {
        // Required empty public constructor
    }

    View view;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_categories, container, false);
        button=view.findViewById(R.id.add);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),AddCategory.class));
            }
        });
        MyViewModel = ViewModelProviders.of(this).get(CategoryFragmentVm.class);
        MyViewModel.getMyCategoryItem().observe(this, new Observer<List<CategoryModel>>() {
            @Override
            public void onChanged(@Nullable List<CategoryModel> categoryModels) {
                  /// here you will send your categoryModels to the Adapter 
            }
        });
        return view;
    }



}
