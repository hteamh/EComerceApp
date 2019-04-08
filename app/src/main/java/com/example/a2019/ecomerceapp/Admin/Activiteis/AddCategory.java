package com.example.a2019.ecomerceapp.Admin.Activiteis;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.a2019.ecomerceapp.Admin.ViewModel.AddCategoryVm;
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
    AddCategoryVm myViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        MyimageView = findViewById(R.id.display_Image_Category);
        Select_Image_Button = findViewById(R.id.choose_image);
        ImageName = findViewById(R.id.textInputLayout);
        Upload = findViewById(R.id.Upload);
        Description = findViewById(R.id.description);
        myViewModel = ViewModelProviders.of(this).get(AddCategoryVm.class);
        Select_Image_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenImageGalary();
            }
        });
        Upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HandelData();
                Observable();
            }// end OnCLick
        }); // end On Click Listener

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
        }
    }
 public  void HandelData()
    {
        String name = this.ImageName.getEditText().getText().toString().trim();
        String Des =this.Description.getEditText().getText().toString().trim();
        String M = System.currentTimeMillis()+"";
        String id = M+1;
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
            return;
        }
       String Uri=MyImageUri.toString();
          myViewModel.InsertNewCategory(name,id,Uri,Des);
    }
    public void Observable()
    {

               myViewModel.getOpenPanelActivity().observe(AddCategory.this, new Observer<Boolean>() {
                   @Override
                   public void onChanged(@Nullable Boolean aBoolean) {
                       if(aBoolean!=null && aBoolean==true)
                       {
                           startActivity(new Intent(AddCategory.this,AdminPanel.class));
                           finish();
                           return;
                       }

           }
       });

                myViewModel.getShowThisMessage().observe(AddCategory.this, new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String s) {
                        showMessage("error",s,"yes");
                    }


        });

                myViewModel.getHideBrogressBar().observe(AddCategory.this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(@Nullable Boolean aBoolean) {
                        if(aBoolean!=null)
                        {
                            if(aBoolean)
                            {
                             hideProgressBar();
                            }
                            else
                            {
                                showProgressBar(R.string.Loading);
                            }
                        }
            }
        });

    }

}
