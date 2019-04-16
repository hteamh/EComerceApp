package com.example.a2019.ecomerceapp.Customers.Activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.a2019.ecomerceapp.Admin.Models.ItemModel;
import com.example.a2019.ecomerceapp.Admin.Models.UserModel;
import com.example.a2019.ecomerceapp.Admin.RoomDataBaseUtilite.MyDatabase;
import com.example.a2019.ecomerceapp.Customers.Adapters.BasketAdapter;
import com.example.a2019.ecomerceapp.Customers.ViewModel.BasketVm;
import com.example.a2019.ecomerceapp.R;
import java.util.List;
public class BasktActivity extends AppCompatActivity {
    RecyclerView myRecycler;
    BasketAdapter myAdapter;
    RecyclerView.LayoutManager layoutManager;
    BasketVm basketVm;
    Button Buy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baskt);
        basketVm = ViewModelProviders.of(this).get(BasketVm.class);
        myRecycler = findViewById(R.id.RecyclerBasket);
        Buy = findViewById(R.id.Buy);
        initAdapter();
        basketVm.GetAllFev();
        AdapterLisner();
        Buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MyDatabase.getInstance().userDao().GetAllUser()!=null)
                {

                    UserModel userModel = MyDatabase.getInstance().userDao().GetAllUser();
                    List<ItemModel> myItemList = Home.itemModels;
                    Home.itemModels.clear();
                    basketVm.SendOrder(userModel,myItemList);
                }
                else
                {
                     // /////////

                }
            }
        });
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
