package com.example.a2019.ecomerceapp.Admin.Activiteis;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.example.a2019.ecomerceapp.Base.BaseActivity;
import com.example.a2019.ecomerceapp.R;

public class AddCategory extends BaseActivity {
    ImageView MyimageView;
    Button Select_Image_Button;
    Button Upload;
    TextInputLayout ImageName;
    TextInputLayout Description;
    public static int PICK_IMAGE_REQUEST;
    Uri MyImageUri ;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        progressBar = findViewById(R.id.Progress);
        MyimageView = findViewById(R.id.display_Image_Category);
        Select_Image_Button = findViewById(R.id.choose_image);
        ImageName = findViewById(R.id.textInputLayout);
        Upload = findViewById(R.id.Upload);
        Description = findViewById(R.id.description);
        Select_Image_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenImageGalary();
            }
        });
        Upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Here We Will Call Function THat It Upload Image From AddCategoryViewModel
            }
        });

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
            MyimageView.setImageURI(MyImageUri);
            InsertNewCategory();
        }

    }

    private void InsertNewCategory() {
        showProgressBar(R.string.Loading);
        String name = this.ImageName.getEditText().getText().toString();
        Uri ImageUri = this.MyImageUri;
        String Description = this.Description.getEditText().toString();
        String id = System.currentTimeMillis()+"";
        if(name.trim().length()<4)
        {
            ImageName.setError("name should be more than 4 Char");
            return;
        }
        if(Description.trim().length()<10)
        {
            this.Description.setError("Description must by more than 10 char");
            return;
        }


    }// end Insert Func


}
