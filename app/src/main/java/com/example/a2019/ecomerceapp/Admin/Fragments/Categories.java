package com.example.a2019.ecomerceapp.Admin.Fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.a2019.ecomerceapp.Admin.Activiteis.AddCategory;
import com.example.a2019.ecomerceapp.Admin.Activiteis.Commodities;
import com.example.a2019.ecomerceapp.Admin.Activiteis.EditCategory;
import com.example.a2019.ecomerceapp.Admin.Adapters.CategoriesAdapter;
import com.example.a2019.ecomerceapp.Admin.Models.CategoryModel;
import com.example.a2019.ecomerceapp.Admin.ViewModel.CategoryFragmentVm;
import com.example.a2019.ecomerceapp.Base.BaseFragment;
import com.example.a2019.ecomerceapp.R;

import java.util.List;

import static android.support.v4.content.ContextCompat.getSystemService;


public class Categories extends BaseFragment {

    FloatingActionButton button;
    CategoryFragmentVm MyViewModel ;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    CategoriesAdapter adapter;
    public  static CategoryModel categoryModeWeWantToUpdate;
    public  static  CategoryModel categoryModeWeWantToSHowHisItem;
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
        recyclerView=view.findViewById(R.id.category_RecycleView);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(getContext());



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),AddCategory.class));
            }
        });
        MyViewModel = ViewModelProviders.of(this).get(CategoryFragmentVm.class);
        MyViewModel.SetData();
        MyViewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                showMessage("error",s,"yes");
            }
        });
        MyViewModel.getMyCategoryItem().observe(this, new Observer<List<CategoryModel>>() {
            @Override
            public void onChanged(@Nullable List<CategoryModel> categoryModels) {
                  /// here you will send your categoryModels to the Adapter
                adapter=new CategoriesAdapter(categoryModels);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(layoutManager);
                adapter.setOnCategoreyEditListener(new CategoriesAdapter.OnCategoreyEditListener() {
                    @Override
                    public void onItemEdit(int pos, CategoryModel model) {
                        Intent intent=new Intent(getContext(),EditCategory.class);
                        categoryModeWeWantToUpdate=model;
                        startActivity(intent);


                    }
                });
                adapter.setOnCategoreyClickedListener(new CategoriesAdapter.OnCategoreyClickedListener() {
                    @Override
                    public void onItemClicked(int pos, CategoryModel MyCategory) {
                        categoryModeWeWantToSHowHisItem = MyCategory;
                        Intent intent=new Intent(getContext(), Commodities.class);
                        startActivity(intent);

                    }
                });

            }
        });


        return view;
    }

}
