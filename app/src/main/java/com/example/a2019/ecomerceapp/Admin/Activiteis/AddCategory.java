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
                // Here We Will Call Function THat It Upload Image TO AddCategoryViewModel
               if(HandelData()==false)
               {
                   return;
               }

                myViewModel.getHideBrogressBar().observe(AddCategory.this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(@Nullable Boolean aBoolean) {
                        if(aBoolean!=null &&aBoolean == true)
                        {
                            hideProgressBar();
                        }
                    }
                });
                myViewModel.getOpenPanelActivity().observe(AddCategory.this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(@Nullable Boolean aBoolean) {

                        startActivity(new Intent(AddCategory.this,AdminPanel.class));
                    }
                });
                myViewModel.getShowThisMessage().observe(AddCategory.this, new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String s) {
                        showMessage("Error",s,"Yes");
                    }
                });
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
        }

    }
 public  Boolean  HandelData()
    {
     String name = ImageName.getEditText().getText().toString();
     String Uri = MyImageUri.toString();
     String Des = Description.getEditText().toString();
     String id = System.currentTimeMillis()+"";
     if(name.trim().length()<5) {  ImageName.setError("Name Of Category Must Be More Than 5 char"); return false; }
     if(Uri==null) {showMessage(R.string.Error,R.string.No_Photo_For_this_Category,R.string.Yes); return false;}
     if (Des.trim().length()<10) {Description.setError("Description for Category Should By More Than 10 char"); return false;}
        showProgressBar(R.string.Wait);
        myViewModel.InsertNewCategory(name,id,Uri,Des);
     return true;
 }

}
