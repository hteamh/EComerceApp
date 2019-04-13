package com.example.a2019.ecomerceapp.Customers.Fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.a2019.ecomerceapp.Admin.Activiteis.Commodities;
import com.example.a2019.ecomerceapp.Admin.Activiteis.EditComodity;
import com.example.a2019.ecomerceapp.Admin.Adapters.CommoditiesAdapter;
import com.example.a2019.ecomerceapp.Admin.Models.ItemModel;
import com.example.a2019.ecomerceapp.Admin.ViewModel.CommoditiesVm;
import com.example.a2019.ecomerceapp.Base.BaseFragment;
import com.example.a2019.ecomerceapp.Customers.Adapters.HomeCommoditiesAdapter;
import com.example.a2019.ecomerceapp.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeCommodities extends BaseFragment {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    CommoditiesVm vm;
    HomeCommoditiesAdapter adapter;

    public HomeCommodities() {
        // Required empty public constructor
    }

    View view ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home_commodities, container, false);

        vm= ViewModelProviders.of(this).get(CommoditiesVm.class);

        recyclerView=view.findViewById(R.id.HomeCommoditiesRecycleView);
        layoutManager=new GridLayoutManager(getContext(),2);
        intiAdapter();
        vm.setData();
        observe();

        return view;
    }

    private void observe() {
        vm.getMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

                showMessage("error",s,"yes");

            }
        });

        vm.getListMutableLiveData().observe(this, new Observer<List<ItemModel>>() {
            @Override
            public void onChanged(@Nullable List<ItemModel> list) {
                adapter.ChangeData(list);

            }
        });
    }

    public void intiAdapter()
    {
        adapter=new HomeCommoditiesAdapter(null);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }


}
