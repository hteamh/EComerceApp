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
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.a2019.ecomerceapp.Admin.Fragments.Categories;
import com.example.a2019.ecomerceapp.Admin.Models.CategoryModel;
import com.example.a2019.ecomerceapp.Admin.ViewModel.EditCategoryVm;
import com.example.a2019.ecomerceapp.Base.BaseActivity;
import com.example.a2019.ecomerceapp.R;

public class EditCategory extends BaseActivity {

    protected ImageView displayImageCategory;
    protected TextInputLayout categoryName;
    protected TextInputLayout description;
    protected Button Upload;
    protected  Button Choose;
    EditCategoryVm myViewModel;
    Uri MyImageUri ;
    protected static final int PICK_IMAGE_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_edit_category);
        initView();
        Upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HandleData();
                GetNewData();
                Observe();
            }

        });
        ////////////////////////////////////////////////////////////////////////
        Choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenImageGalary();
            }
        });
        /////////////////////////////////////////////////////////////////////
        myViewModel = ViewModelProviders.of(this).get(EditCategoryVm.class);

    }

    private void GetNewData() {
        CategoryModel categoryModel = new CategoryModel(categoryName.getEditText().getText().toString(),
                MyImageUri.toString(),Categories.categoryModeWeWantToUpdate.getId(),
                description.getEditText().getText().toString()
                );
        myViewModel.UpdateCategry(categoryModel);
    }

    private void HandleData() {

        String name = this.categoryName.getEditText().getText().toString().trim();
        String Des =this.description.getEditText().getText().toString().trim();
        if(name.length()<1)
        {
            showMessage("error","Enter Name","Yes");
            return;
        }
        if(Des.length()<1)
        {
            showMessage("error","Enter Des","Yes");
            return;
        }
        if(MyImageUri ==null)
        {
            showMessage("error","Select Image","Yes");

        }
    }


    private void initView() {
        MyImageUri = Uri.parse(Categories.categoryModeWeWantToUpdate.getImageUri());
        Choose = findViewById(R.id.choose_image);
        displayImageCategory =  findViewById(R.id.display_Image_Category);
        categoryName =  findViewById(R.id.category_name);
        description =  findViewById(R.id.description);
        Upload =  findViewById(R.id.Upload);
        categoryName.getEditText().setText(Categories.categoryModeWeWantToUpdate.getName());
        description.getEditText().setText(Categories.categoryModeWeWantToUpdate.getDescription());
       displayImageCategory.setImageURI(MyImageUri);
        Glide.with(this).load(MyImageUri).into(displayImageCategory);

    }
    public void OpenImageGalary() {
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
            Glide.with(this).load(MyImageUri).into(displayImageCategory);
        }
    }
    public  void Observe()
    {
        myViewModel.getMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                showMessage("error",s,"Yes");
            }
        });
        myViewModel.getDone().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if(aBoolean !=null)
                {
                    if(aBoolean)
                    {
                        finish();
                    }
                }

            }
        });
        myViewModel.getHideProgress().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean!=null)
                {
                    if (aBoolean)
                    {
                      hideProgressBar();
                    }
                    if(aBoolean==false)
                    {
                       showProgressBar(R.string.Loading);
                    }
                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
