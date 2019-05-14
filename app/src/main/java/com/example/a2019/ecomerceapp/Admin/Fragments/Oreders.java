package com.example.a2019.ecomerceapp.Admin.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.a2019.ecomerceapp.Base.BaseFragment;
import com.example.a2019.ecomerceapp.R;

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

    }
    private void Observe()
    {


    }



    @Override
    public void onResume() {
        super.onResume();
    }
}
