package com.example.a2019.ecomerceapp.Admin.Activiteis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.a2019.ecomerceapp.Admin.Adapters.CommoditiesAdapter;
import com.example.a2019.ecomerceapp.Admin.Models.ItemModel;
import com.example.a2019.ecomerceapp.Admin.Models.MyfevItem;
import com.example.a2019.ecomerceapp.Admin.RoomDataBaseUtilite.MyDatabase;
import com.example.a2019.ecomerceapp.R;

import java.util.ArrayList;
import java.util.List;

public class favouriteActivity extends AppCompatActivity {
RecyclerView favourite_RecuclerView ;
    CommoditiesAdapter commoditiesAdapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        Init();
        GetAllFev();
    }
    public void Init()
    {
        favourite_RecuclerView = findViewById(R.id.favourite_RecuclerView);
        commoditiesAdapter = new CommoditiesAdapter(null);
        layoutManager = new LinearLayoutManager(this);
        favourite_RecuclerView.setAdapter(commoditiesAdapter);
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
                        commoditiesAdapter.ChangeData(itemModels);
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
