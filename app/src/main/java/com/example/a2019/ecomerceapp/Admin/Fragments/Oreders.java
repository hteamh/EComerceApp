package com.example.a2019.ecomerceapp.Admin.Fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.a2019.ecomerceapp.Admin.Adapters.OrdarAdapter;
import com.example.a2019.ecomerceapp.Admin.Adapters.orderAdapter;
import com.example.a2019.ecomerceapp.Admin.Models.OrdersCountainer;
import com.example.a2019.ecomerceapp.Admin.ViewModel.OrdersViewModels;
import com.example.a2019.ecomerceapp.Base.BaseFragment;
import com.example.a2019.ecomerceapp.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Oreders extends BaseFragment {
 RecyclerView.LayoutManager layoutManager;
 View view;
 OrdersViewModels myViewModel;
    RecyclerView mainrecycler;
    OrdarAdapter MainAdapter;
    orderAdapter SubAdapter;

    public Oreders() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
   view= inflater.inflate(R.layout.fragment_oreders, container, false);
        IntiData();
        myViewModel.GetAllItem();
        ListneAdapter();
        Observe();
        return view;
    }


    private void IntiData()
    {
        myViewModel =ViewModelProviders.of(this).get(OrdersViewModels.class);
        mainrecycler = view.findViewById(R.id.MainRecyclerVIew);
        SubAdapter = new orderAdapter(null);
        MainAdapter = new OrdarAdapter(null,SubAdapter);
        layoutManager = new LinearLayoutManager(getActivity());
        mainrecycler.setLayoutManager(layoutManager);
        mainrecycler.setAdapter(MainAdapter);


    }
    private void Observe()
    {
      myViewModel.getOrderGetter().observe(this, new Observer<List<OrdersCountainer>>() {
          @Override
          public void onChanged(@Nullable List<OrdersCountainer> ordersCountainers) {
              MainAdapter.ChangeData(ordersCountainers);

          }

      });

    }
    private void ListneAdapter()
    {

        MainAdapter.setOnSendClickListner(new OrdarAdapter.OnSendClickListner() {
            @Override
            public void OnSendClick(OrdersCountainer ordersCountainer, int Pos) {
                myViewModel.DeleteOrderToMoveToSalesBasket(ordersCountainer);
                myViewModel.SendOrderToSalesBasket(ordersCountainer);

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
