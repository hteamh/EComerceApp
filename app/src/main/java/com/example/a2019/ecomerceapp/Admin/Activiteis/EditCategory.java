package com.example.a2019.ecomerceapp.Admin.Activiteis;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;

import com.example.a2019.ecomerceapp.R;

public class EditCategory extends AppCompatActivity {

    protected ImageView displayImageCategory;
    protected TextInputLayout categoryName;
    protected TextInputLayout description;
    protected Button Upload;

    String uri, name, desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_edit_category);
        initView();





    }

    private void initView() {
        displayImageCategory = (ImageView) findViewById(R.id.display_Image_Category);
        categoryName =  findViewById(R.id.category_name);
        description = (TextInputLayout) findViewById(R.id.description);
        Upload = (Button) findViewById(R.id.Upload);
    }
}
