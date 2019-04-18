package com.example.a2019.ecomerceapp.Customers.Fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.a2019.ecomerceapp.Admin.Activiteis.AdminPanel;
import com.example.a2019.ecomerceapp.Admin.Activiteis.Commodities;
import com.example.a2019.ecomerceapp.Admin.Activiteis.EditCategory;
import com.example.a2019.ecomerceapp.Admin.Adapters.CategoriesAdapter;
import com.example.a2019.ecomerceapp.Admin.Fragments.Categories;
import com.example.a2019.ecomerceapp.Admin.Models.CategoryModel;
import com.example.a2019.ecomerceapp.Admin.ViewModel.CategoryFragmentVm;
import com.example.a2019.ecomerceapp.Base.BaseFragment;
import com.example.a2019.ecomerceapp.Customers.Activities.Commdity;
import com.example.a2019.ecomerceapp.Customers.Activities.Home;
import com.example.a2019.ecomerceapp.Customers.Adapters.HomeCategoriesAdapter;
import com.example.a2019.ecomerceapp.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryHomeFragment extends BaseFragment {

    CategoryFragmentVm MyViewModel ;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    HomeCategoriesAdapter adapter;


    public CategoryHomeFragment() {
        // Required empty public constructor
    }

    View view ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView=view.findViewById(R.id.homeRecycleView);
        layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setHasFixedSize(true);
        IntiAdapter();
        MyViewModel = ViewModelProviders.of(this).get(CategoryFragmentVm.class);


        return view;
    }

    private void Observe() {
        MyViewModel.getMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                showMessage("error",s,"yes");
            }
        });
        MyViewModel.getMyCategoryItem().observe(this, new Observer<List<CategoryModel>>() {
            @Override
            public void onChanged(@Nullable List<CategoryModel> categoryModels) {

                AdapterClickLisner();
                adapter.ChangeData(categoryModels);

            }
        });
        MyViewModel.getHideProgress().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if(aBoolean!=null)
                {
                    if(aBoolean)
                    {
                        hideProgressBar();
                    }
                    else
                    {
                        showProgressBar(R.string.Loading) ;
                    }
                }
            }
        });
    }

    public void IntiAdapter() {
        adapter=new HomeCategoriesAdapter(null);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    public void AdapterClickLisner() {

        adapter.setOnCategoryClickedListener(new HomeCategoriesAdapter.OnCategoryClickedListener() {
            @Override
            public void onItemClicked(int pos, CategoryModel MyCategory) {
                Categories.categoryModeWeWantToSHowHisItem = MyCategory;
             startActivity(new Intent(getContext(), Commdity.class));
            }
        });


    }

    @Override
    public void onDetach() {
        super.onDetach();
        getActivity().finish();
    }

    @Override
    public void onResume() {
        super.onResume();
        MyViewModel.SetData();
        Observe();
    }
}
