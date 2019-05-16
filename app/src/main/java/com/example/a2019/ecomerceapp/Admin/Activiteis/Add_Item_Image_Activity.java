package com.example.a2019.ecomerceapp.Admin.Activiteis;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.a2019.ecomerceapp.Admin.Adapters.ImageAdapter;
import com.example.a2019.ecomerceapp.Admin.Models.ItemModel;
import com.example.a2019.ecomerceapp.Admin.Models.Item_Images_Models;
import com.example.a2019.ecomerceapp.Admin.ViewModel.Add_Item_Image_VM;
import com.example.a2019.ecomerceapp.Base.BaseActivity;
import com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase.Item_Image;
import com.example.a2019.ecomerceapp.R;

import java.util.List;

public class Add_Item_Image_Activity extends BaseActivity {
    public static ItemModel itemModel;
    public Button mybutton;
    public RecyclerView myRecycler;
    ImageAdapter imageAdapter;
    RecyclerView.LayoutManager layoutManager;
    Add_Item_Image_VM ViewModel;
    ImageView MainImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__item__image_);
        init();
        imageAdapter.setOnDeleteClick(new ImageAdapter.onDeleteClick() {
            @Override
            public void OnCLick(Item_Images_Models item_images_models) {
                Item_Image.DeleteImageyByid(item_images_models.getId());
                finish();
                startActivity(new Intent(Add_Item_Image_Activity.this, Add_Item_Image_Activity.class));

            }
        });
        ViewModel.GetAllImageByItemId(itemModel.getId());
        observe();
    }

    private void observe() {
        ViewModel.getMyList().observe(this, new Observer<List<Item_Images_Models>>() {
            @Override
            public void onChanged(@Nullable List<Item_Images_Models> item_images_models) {
                imageAdapter.ChangeData(item_images_models);
            }
        });
    }

    public void init() {
        MainImage = findViewById(R.id.MainImage);
        Glide.with(this).load(itemModel.getImageUri()).into(MainImage);
        ViewModel = ViewModelProviders.of(this).get(Add_Item_Image_VM.class);
        mybutton = findViewById(R.id.add_new_image);
        myRecycler = findViewById(R.id.ImageRecycler);
        imageAdapter = new ImageAdapter(null);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        myRecycler.setAdapter(imageAdapter);
        myRecycler.setLayoutManager(layoutManager);
        mybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Add_Item_Image_Activity.this, AddMoreImages.class));

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
