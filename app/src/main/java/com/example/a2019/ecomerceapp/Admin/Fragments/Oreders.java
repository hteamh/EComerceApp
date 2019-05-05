package com.example.a2019.ecomerceapp.Admin.Fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a2019.ecomerceapp.Admin.Activiteis.AdminPanel;
import com.example.a2019.ecomerceapp.Admin.Adapters.orderAdapter;
import com.example.a2019.ecomerceapp.Admin.Models.ItemModel;
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
    RecyclerView Subrecycler;
    orderAdapter SubAdapter;
    TextView name,phone,Adrees,Date,totalPrice;
    Button Send ;


    public Oreders() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
   view= inflater.inflate(R.layout.fragment_oreders, container, false);
        IntiData();
        myViewModel.GetOneOrder();
        Observe();

        return view;
    }


    private void IntiData()
    {
        myViewModel =ViewModelProviders.of(this).get(OrdersViewModels.class);
        SubAdapter = new orderAdapter(null);
        layoutManager = new LinearLayoutManager(getActivity());
        Subrecycler = view.findViewById(R.id.SubRecyclerView);
        Subrecycler.setAdapter(SubAdapter);
        Subrecycler.setLayoutManager(layoutManager);
       name = view.findViewById(R.id.newCustomerName);
       phone = view.findViewById(R.id.newCustomerPhone);
       Adrees=view.findViewById(R.id.newCustomerAdrees);
       Date = view.findViewById(R.id.newDate);
       totalPrice=view.findViewById(R.id.newTotalprice);
       Send = view.findViewById(R.id.newButton);
    }
    private void Observe()
    {
      myViewModel.getOrderGetter().observe(this, new Observer<OrdersCountainer>() {
          @Override
          public void onChanged(@Nullable final OrdersCountainer ordersCountainer) {
              if(ordersCountainer==null)
              {
                  Toast.makeText(getActivity(), "No Order To Show ", Toast.LENGTH_LONG).show();
                  getActivity().finish();
                  startActivity(new Intent(getContext(), AdminPanel.class));
              }
              else
              {
                  name.setText("Name :"+ordersCountainer.getName());
                  phone.setText("Phone :"+ordersCountainer.getPhone());
                  Adrees.setText("Adrees :"+ordersCountainer.getAdrees());
                  Date.setText("Date :"+ordersCountainer.getDate());
                  totalPrice.setText("Total Price :"+ordersCountainer.getTotalPrice());
                  final List<ItemModel> MyItem = ordersCountainer.getItemModels();
                  SubAdapter.ChangeData(MyItem);
                  Send.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          myViewModel.DeleteOrderToMoveToSalesBasket(ordersCountainer);
                          myViewModel.GetOneOrder();

                      }
                  });
              }


          }
      });

    }



    @Override
    public void onResume() {
        super.onResume();
    }
}
