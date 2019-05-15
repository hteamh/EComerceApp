package com.example.a2019.ecomerceapp.Admin.Fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a2019.ecomerceapp.Admin.Activiteis.AdminPanel;
import com.example.a2019.ecomerceapp.Admin.Activiteis.OrderDetails;
import com.example.a2019.ecomerceapp.Admin.Adapters.OrderAdapter;
import com.example.a2019.ecomerceapp.Admin.Models.OrderModel;
import com.example.a2019.ecomerceapp.Admin.ViewModel.orderViewModel;
import com.example.a2019.ecomerceapp.Base.BaseFragment;
import com.example.a2019.ecomerceapp.Customers.Activities.Home;
import com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase.OrderBranches;
import com.example.a2019.ecomerceapp.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Oreders extends BaseFragment {
 RecyclerView.LayoutManager layoutManager;
 OrderAdapter orderAdapter;
 View view;
 orderViewModel viewModel;
  RecyclerView recycler;



    public Oreders() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
   view= inflater.inflate(R.layout.fragment_oreders, container, false);
        IntiData();
        Observe();
        viewModel.GetAllOrders();
        return view;
    }


    private void IntiData()
    {
    viewModel = ViewModelProviders.of(this).get(orderViewModel.class);
    recycler = view.findViewById(R.id.OrderRecyclerView);
    orderAdapter = new OrderAdapter(null);
    layoutManager = new LinearLayoutManager(getContext());
    recycler.setLayoutManager(layoutManager);
    recycler.setAdapter(orderAdapter);
    orderAdapter.setOnOrderClick(new OrderAdapter.OnOrderClick() {
        @Override
        public void InorderClick(OrderModel order) {
            OrderDetails.MyOrder=order;
            startActivity(new Intent(getContext(),OrderDetails.class));
        }
    });

    }
    private void Observe()
    {


        viewModel.getMyOrders().observe(this, new Observer<List<OrderModel>>() {
            @Override
            public void onChanged(@Nullable List<OrderModel> orderModels) {
                orderAdapter.ChangeData(orderModels);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if(OrderDetails.DoRefresh == true)
        {
            OrderDetails.DoRefresh = false;
            getActivity().finish();
            startActivity(new Intent(getContext(), AdminPanel.class));
            getActivity().getSupportFragmentManager().beginTransaction()
                    .addToBackStack(null).replace(R.id.add_category, this).commit();
        }
    }
}
