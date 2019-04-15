package com.example.a2019.ecomerceapp.Admin.Activiteis;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.example.a2019.ecomerceapp.Admin.Fragments.Categories;
import com.example.a2019.ecomerceapp.Admin.Models.ItemModel;
import com.example.a2019.ecomerceapp.Admin.ViewModel.Add_ComodityVm;
import com.example.a2019.ecomerceapp.Base.BaseActivity;
import com.example.a2019.ecomerceapp.R;

public class Add_Commodity extends BaseActivity implements View.OnClickListener {
    protected ImageView commodityImage;
    protected TextInputLayout commodityName;
    protected Button chooseImageComm;
    protected TextInputLayout descriptionComm;
    protected Button Upload;
    protected EditText price;
    Uri MyImageUri;
    Add_ComodityVm myViewModel;
    private static  final int PICK_IMAGE_REQUEST =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_add__commodity);
        initView();
        myViewModel = ViewModelProviders.of(this).get(Add_ComodityVm.class);

    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.choose_image_comm) {
            OpenImageGalary();
        } else if (view.getId() == R.id.Upload) {
            HandelData();
            Observe();
        }
    }

    private void initView() {
        commodityImage =  findViewById(R.id.commodity_image);
        commodityName =  findViewById(R.id.commodity_name);
        chooseImageComm =  findViewById(R.id.choose_image_comm);
        chooseImageComm.setOnClickListener(Add_Commodity.this);
        descriptionComm =  findViewById(R.id.description_comm);
        Upload =  findViewById(R.id.Upload);
        Upload.setOnClickListener(Add_Commodity.this);
        price = findViewById(R.id.price);
    }
    public void  OpenImageGalary() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            MyImageUri = data.getData();
            commodityImage.setImageURI(MyImageUri);
        }
    }
    public  void HandelData()
    {
        String name = this.commodityName.getEditText().getText().toString().trim();
        String Des =this.descriptionComm.getEditText().getText().toString().trim();
        String Price = this.price.getText().toString().trim();
        String M = System.currentTimeMillis()+"";
        String id = M+1;
        if(name.length()<1)
        {
            showMessage("error","Enter Name","Yes");
            return;
        }
        if(Price.length()<1)
        {
            showMessage("error","Enter Price","Yes");return;

        }
        if(Des.length()<1)
        {
            showMessage("error","Enter Des","Yes");
            return;
        }
        if(MyImageUri ==null)
        {
            showMessage("error","Select Image","Yes");
            return;
        }
        String Uri=MyImageUri.toString();
        ItemModel itemModel = new ItemModel(name,Des,Uri,id,Price, Categories.categoryModeWeWantToSHowHisItem.getName());
       myViewModel.InsertNewComModity(itemModel);
    }
    public void Observe() {
        myViewModel.getMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                showMessage("Error", s, "Yes");
            }
        });
        ////
        myViewModel.getHideProgress().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean != null) {
                    if (aBoolean) {
                        hideProgressBar();
                    } else {
                        showProgressBar(R.string.Loading);
                    }
                }

            }
        });
        myViewModel.getDone().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean != null) {
                    if (aBoolean) {
                        finish();
                    }
                }
            }
        });

    }

}
