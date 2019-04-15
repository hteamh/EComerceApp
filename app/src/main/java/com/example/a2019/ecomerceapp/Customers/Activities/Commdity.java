package com.example.a2019.ecomerceapp.Customers.Activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.a2019.ecomerceapp.Admin.Models.ItemModel;
import com.example.a2019.ecomerceapp.Admin.ViewModel.CommoditiesVm;
import com.example.a2019.ecomerceapp.Customers.Adapters.HomeCommoditiesAdapter;
import com.example.a2019.ecomerceapp.R;

import java.util.ArrayList;
import java.util.List;

public class Commdity extends AppCompatActivity {
RecyclerView myrecycler;
CommoditiesVm commoditiesVm;
HomeCommoditiesAdapter myadapter;
RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commdity);
        myrecycler = findViewById(R.id.RecyclerCommedity);
        layoutManager= new LinearLayoutManager(this);
        InitAdapter();
        commoditiesVm = ViewModelProviders.of(this).get(CommoditiesVm.class);
        commoditiesVm.setData();
        AdapterListner();
        Observe();
    }
    private void InitAdapter()
    {
        myadapter = new HomeCommoditiesAdapter(null);
        myrecycler.setLayoutManager(layoutManager);
        myrecycler.setAdapter(myadapter);
    }
    private void AdapterListner()
    {
        myadapter.setOnHeartClick(new HomeCommoditiesAdapter.OnHeartClick() {
            @Override
            public void OnClick(ItemModel itemModel) {
                Home.itemModels.add(itemModel);
                Toast.makeText(Commdity.this, "add to Basket", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void Observe() {
        commoditiesVm.getListMutableLiveData().observe(this, new Observer<List<ItemModel>>() {
            @Override
            public void onChanged(@Nullable List<ItemModel> itemModels) {
                myadapter.ChangeData(itemModels);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
