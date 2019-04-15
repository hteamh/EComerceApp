package com.example.a2019.ecomerceapp.Customers.Activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.a2019.ecomerceapp.Admin.Models.ItemModel;
import com.example.a2019.ecomerceapp.Customers.Adapters.BasketAdapter;
import com.example.a2019.ecomerceapp.Customers.ViewModel.BasketVm;
import com.example.a2019.ecomerceapp.R;

import java.util.List;

public class BasktActivity extends AppCompatActivity {
    RecyclerView myRecycler;
    BasketAdapter myAdapter;
    RecyclerView.LayoutManager layoutManager;
    BasketVm basketVm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baskt);
        basketVm = ViewModelProviders.of(this).get(BasketVm.class);
        myRecycler = findViewById(R.id.RecyclerBasket);
        initAdapter();
        basketVm.GetAllFev();
        AdapterLisner();
        Observe();


    }
    private void initAdapter()
    {
        myAdapter = new BasketAdapter(null);
        layoutManager=new LinearLayoutManager(this);

    }
    private void AdapterLisner()
    {
      myAdapter.setOnDeleteClick(new BasketAdapter.onDeleteClick() {
          @Override
          public void OnClick(int pos, ItemModel itemModel) {
             Home.itemModels.remove(pos);
             finish();
             startActivity(new Intent(BasktActivity.this,BasktActivity.class));
          }
      });
    }
    private void Observe()
    {
      basketVm.getListMutableLiveData().observe(this, new Observer<List<ItemModel>>() {
          @Override
          public void onChanged(@Nullable List<ItemModel> itemModels) {
              myAdapter.ChangeData(itemModels);
              myRecycler.setAdapter(myAdapter);
              myRecycler.setLayoutManager(layoutManager);
          }
      });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
