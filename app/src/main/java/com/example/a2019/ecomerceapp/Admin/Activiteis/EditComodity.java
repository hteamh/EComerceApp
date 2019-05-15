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
import android.widget.Toast;

import com.example.a2019.ecomerceapp.Admin.Fragments.Categories;
import com.example.a2019.ecomerceapp.Admin.Models.ItemModel;
import com.example.a2019.ecomerceapp.Admin.ViewModel.EditComdityVm;
import com.example.a2019.ecomerceapp.Base.BaseActivity;
import com.example.a2019.ecomerceapp.R;

public class EditComodity extends BaseActivity implements View.OnClickListener {

    protected ImageView commodityImage;
    protected TextInputLayout commodityName;
    protected Button chooseImageComm;
    protected TextInputLayout descriptionComm;
    protected Button Upload;
    protected EditText price,BuyingPrice;
    protected Uri MyImageUri;
    private static final int PICK_IMAGE_REQUEST=1;
    private EditComdityVm vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_edit_comodity);
        initView();
        vm=ViewModelProviders.of(this).get(EditComdityVm.class);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.choose_image_comm) {
            OpenImageGalary();
        } else if (view.getId() == R.id.Upload) {

            HandelData();
            observe();
        }
    }

    private void observe() {
        vm.getMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                showMessage("error",s,"yes");

            }
        });

        vm.getHideProgress().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean!=null){
                    if (aBoolean){
                        hideProgressBar();
                    }else {
                        showProgressBar(R.string.Loading);
                    }
                }

            }
        });

        vm.getDone().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                finish();
            }
        });

    }


    public  void HandelData()
    {
        String BuyingPrice = this.BuyingPrice.getText().toString().trim();
        String name = this.commodityName.getEditText().getText().toString().trim();
        String Des =this.descriptionComm.getEditText().getText().toString().trim();
        String Price = this.price.getText().toString().trim();
        if(name.length()<1)
        {
            showMessage("error","Enter Name","Yes");
            return;
        }
        if(Price.length()<1)
        {
            showMessage("error","Enter Price","Yes");return;

        }
        if(BuyingPrice.length()<1)
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
        if(isStringInt(Price) && isStringInt(BuyingPrice))
        {
            ItemModel itemModel = new ItemModel(name,Des,Uri,Commodities.comodityWeWantEdit.getId(),Price,
                    Categories.categoryModeWeWantToSHowHisItem.getName(),"1",BuyingPrice);
            vm.Update(itemModel);
        }
        else
        {
            Toast.makeText(this, "Price And Buying Price Should By Integer Value", Toast.LENGTH_LONG).show();
        }


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

        }
    }

    private void initView() {
        MyImageUri= Uri.parse(Commodities.comodityWeWantEdit.getImageUri());
        commodityImage =  findViewById(R.id.commodity_image);
        commodityName =  findViewById(R.id.commodity_name);
        chooseImageComm =  findViewById(R.id.choose_image_comm);
        chooseImageComm.setOnClickListener(EditComodity.this);
        descriptionComm =  findViewById(R.id.description_comm);
        Upload =  findViewById(R.id.Upload);
        Upload.setOnClickListener(EditComodity.this);
        price = findViewById(R.id.price);
        BuyingPrice = findViewById(R.id.Buingprice);
        BuyingPrice.setText(Commodities.comodityWeWantEdit.getBuyingPrice());
        commodityImage.setImageURI(MyImageUri);
        commodityName.getEditText().setText(Commodities.comodityWeWantEdit.getName());
        descriptionComm.getEditText().setText(Commodities.comodityWeWantEdit.getDescription());
        price.setText(Commodities.comodityWeWantEdit.getPrice());
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    public boolean isStringInt(String s)
    {
        try
        {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex)
        {
            return false;
        }
    }

}
