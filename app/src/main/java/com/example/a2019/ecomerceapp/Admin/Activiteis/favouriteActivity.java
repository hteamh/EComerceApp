package com.example.a2019.ecomerceapp.Admin.Activiteis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.a2019.ecomerceapp.Admin.Adapters.CommoditiesAdapter;
import com.example.a2019.ecomerceapp.Admin.Models.ItemModel;
import com.example.a2019.ecomerceapp.Admin.Models.MyfevItem;
import com.example.a2019.ecomerceapp.Admin.RoomDataBaseUtilite.MyDatabase;
import com.example.a2019.ecomerceapp.Customers.Activities.Commdity;
import com.example.a2019.ecomerceapp.Customers.Activities.Home;
import com.example.a2019.ecomerceapp.Customers.Activities.ShowItemDetailes;
import com.example.a2019.ecomerceapp.Customers.Adapters.HomeCommoditiesAdapter;
import com.example.a2019.ecomerceapp.R;

import java.util.ArrayList;
import java.util.List;

public class favouriteActivity extends AppCompatActivity {
RecyclerView favourite_RecuclerView ;
    HomeCommoditiesAdapter myadapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        Init();
        GetAllFev();
       AdapterListner();
    }

    public void AdapterListner()
    {
        myadapter.setOnItemClick(new HomeCommoditiesAdapter.OnItemClick() {
            @Override
            public void OnClick(ItemModel itemModel) {
                ShowItemDetailes.MyItem =itemModel;
                startActivity(new Intent(favouriteActivity.this,ShowItemDetailes.class));
            }
        });
        myadapter.setOnSHopClick(new HomeCommoditiesAdapter.OnSHopClick() {
            @Override
            public void OnClick(ItemModel itemModel) {
                Home.itemModels.add(itemModel);
                Toast.makeText(favouriteActivity.this, "add to Basket", Toast.LENGTH_SHORT).show();
            }
        });
//        myadapter.setOnHeart(new HomeCommoditiesAdapter.OnHeart() {
//            @Override
//            public void OnClick(ItemModel itemModel) {
//                MyfevItem myfevItem = new MyfevItem(itemModel.getName(),itemModel.getDescription()
//                        ,itemModel.getImageUri(),itemModel.getId(),itemModel.getPrice(),itemModel.getCategoryName(),itemModel.getCount(),itemModel.getBuyingPrice());
//                AddFevThread addFevThread = new AddFevThread(myfevItem);
//                addFevThread.start();
//                Toast.makeText(Commdity.this, "add to favourite", Toast.LENGTH_SHORT).show();
//
//            }
//        });

    }
    public void Init()
    {
        favourite_RecuclerView = findViewById(R.id.favourite_RecuclerView);
        myadapter = new HomeCommoditiesAdapter(null);
        layoutManager = new LinearLayoutManager(this);
        favourite_RecuclerView.setAdapter(myadapter);
        favourite_RecuclerView.setLayoutManager(layoutManager);
    }
    public void GetAllFev()
    {
        final List<ItemModel> itemModels = new ArrayList<>();
        getAllFavouriteThread getAllFavouriteThread = new getAllFavouriteThread(new GetAllFev() {
            @Override
            public void getAll(List<MyfevItem> myfevItems) {
                for(int i =0;i<myfevItems.size();i++)
                {
                    MyfevItem myfevItem = myfevItems.get(i);
                    itemModels.add(new ItemModel(myfevItem));
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        myadapter.ChangeData(itemModels);
                    }
                });
            }
        });
        getAllFavouriteThread.start();
    }
    public class getAllFavouriteThread extends Thread{
        GetAllFev getAllFev;

        public getAllFavouriteThread(GetAllFev getAllFev) {
            this.getAllFev = getAllFev;
        }

        @Override
        public void run() {
            super.run();
           getAllFev.getAll( MyDatabase.getInstance().fevItemDao().GetAllMyfevItem());
        }
    }
    public interface GetAllFev{
     void   getAll(List<MyfevItem> myfevItems);
    }
}
