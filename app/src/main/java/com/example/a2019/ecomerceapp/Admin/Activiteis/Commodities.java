package com.example.a2019.ecomerceapp.Admin.Activiteis;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.a2019.ecomerceapp.Admin.Adapters.CommoditiesAdapter;
import com.example.a2019.ecomerceapp.Admin.Models.ItemModel;
import com.example.a2019.ecomerceapp.Admin.ViewModel.CommoditiesVm;
import com.example.a2019.ecomerceapp.Base.BaseActivity;
import com.example.a2019.ecomerceapp.R;

import java.util.List;

public class Commodities extends BaseActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    CommoditiesAdapter adapter;
    CommoditiesVm vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodities);
        vm= ViewModelProviders.of(this).get(CommoditiesVm.class);

        recyclerView=findViewById(R.id.commodities_RecycleView);
        layoutManager=new LinearLayoutManager(this);

        vm.setData();

        observe();



        // initialize the adapter when you get the data

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Commodities.this,Add_Commodity.class));
            }
        });
    }

    private void observe() {
        vm.getShowMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

                showMessage("error",s,"yes");

            }
        });

        vm.getListMutableLiveData().observe(this, new Observer<List<ItemModel>>() {
            @Override
            public void onChanged(@Nullable List<ItemModel> list) {
                adapter=new CommoditiesAdapter(list);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(layoutManager);
            }
        });
    }

}
