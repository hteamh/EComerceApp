package com.example.a2019.ecomerceapp.Customers.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a2019.ecomerceapp.Admin.Models.ItemModel;
import com.example.a2019.ecomerceapp.Admin.Models.Item_Images_Models;
import com.example.a2019.ecomerceapp.Customers.Adapters.ImageAdapter;
import com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase.Item_Image;
import com.example.a2019.ecomerceapp.R;
import com.rbrooks.indefinitepagerindicator.IndefinitePagerIndicator;

import java.util.List;

public class ShowItemDetailes extends AppCompatActivity {
     public static ItemModel MyItem;
     com.example.a2019.ecomerceapp.Customers.Adapters.ImageAdapter adapter;
     RecyclerView.LayoutManager layoutManager;
     RecyclerView ImageRecycler2;
     TextView name,Des,price;
     ImageView imageView;
     IndefinitePagerIndicator indicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_item_detailes);
       Init();
        GetAllImage();

    }

    private void Init() {
        indicator=findViewById(R.id.recyclerview_pager_indicator);

        imageView = findViewById(R.id.MainImage2);
        adapter = new ImageAdapter(null);
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        ImageRecycler2 = findViewById(R.id.ImageRecycler2);
        ImageRecycler2.setLayoutManager(layoutManager);
        ImageRecycler2.setAdapter(adapter);
        indicator.attachToRecyclerView(ImageRecycler2);

        name = findViewById(R.id.Item_Name);
        Des = findViewById(R.id.Item_Description);
        price= findViewById(R.id.Item_Price);
        name.setText(MyItem.getName());
        Des.setText(MyItem.getDescription());
        price.setText(MyItem.getPrice());
        Glide.with(this)
                .load(MyItem.getImageUri())
                .into(imageView);
    }
    public void GetAllImage()
    {
        Item_Image.GetAllImageByItemId(MyItem.getId(), new Item_Image.GetAllImageToTheItem() {
            @Override
            public void AllImage(List<Item_Images_Models> MyList) {
                adapter.ChangeData(MyList);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
