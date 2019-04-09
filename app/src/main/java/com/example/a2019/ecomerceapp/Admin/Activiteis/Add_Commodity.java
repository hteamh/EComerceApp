package com.example.a2019.ecomerceapp.Admin.Activiteis;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.a2019.ecomerceapp.R;

public class Add_Commodity extends AppCompatActivity implements View.OnClickListener {
    protected ImageView commodityImage;
    protected TextInputLayout commodityName;
    protected Button chooseImageComm;
    protected TextInputLayout descriptionComm;
    protected Button Upload;
    protected EditText price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_add__commodity);
        initView();

    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.choose_image_comm) {

        } else if (view.getId() == R.id.Upload) {

        }
    }

    private void initView() {
        commodityImage = (ImageView) findViewById(R.id.commodity_image);
        commodityName = (TextInputLayout) findViewById(R.id.commodity_name);
        chooseImageComm = (Button) findViewById(R.id.choose_image_comm);
        chooseImageComm.setOnClickListener(Add_Commodity.this);
        descriptionComm = (TextInputLayout) findViewById(R.id.description_comm);
        Upload = (Button) findViewById(R.id.Upload);
        Upload.setOnClickListener(Add_Commodity.this);
        price = (EditText) findViewById(R.id.price);
    }
}
