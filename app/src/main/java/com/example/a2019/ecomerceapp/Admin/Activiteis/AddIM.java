package com.example.a2019.ecomerceapp.Admin.Activiteis;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.a2019.ecomerceapp.Admin.Models.Item_Images_Models;
import com.example.a2019.ecomerceapp.Admin.ViewModel.AddImVm;
import com.example.a2019.ecomerceapp.Base.BaseActivity;
import com.example.a2019.ecomerceapp.R;

public class AddIM extends BaseActivity {
    ImageView AddImage;
    Button  upload_Image,ChooseImage;
    Uri uri;
    AddImVm ViewModel;
    public static int Image_Code = 88;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_im);
        init();
    }

    private void init() {
        ViewModel = ViewModelProviders.of(this).get(AddImVm.class);
        AddImage = findViewById(R.id.Add_Image_Item) ;
        upload_Image= findViewById(R.id.Add_Image_Item_button);
        ChooseImage=findViewById(R.id.Add_Image_Item_button2);
        ChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenImageGallary();
            }
        });
        upload_Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(uri !=null)
                {
                    String Url =  uri.toString();
                    String ItemId= Add_Item_Image_Activity.itemModel.getId();
                    String id= Long.toString(System.currentTimeMillis());
                    Item_Images_Models item_images_models =  new Item_Images_Models(ItemId,id,Url);
                    ViewModel.AddImage(item_images_models);
                   Observe();
                }
                else
                {
                    Toast.makeText(activity, "No Image Selected", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void Observe() {
        ViewModel.getMessage().observe(AddIM.this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                showMessage("error",s,"yes");
            }


        });

        ViewModel.getHideProgress().observe(AddIM.this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if(aBoolean!=null)
                {
                    if(aBoolean)
                    {
                        hideProgressBar();
                        finish();
                    }
                    else
                    {
                        showProgressBar(R.string.Loading);
                    }
                }
            }
        });
    }

    public void OpenImageGallary()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, Image_Code);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Image_Code && resultCode == RESULT_OK && data != null && data.getData() != null) {
         uri = data.getData();
            Glide.with(AddIM.this).load(uri).into(AddImage);
        }

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
