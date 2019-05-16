package com.example.a2019.ecomerceapp.Customers.Activities;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.a2019.ecomerceapp.Admin.Models.UserModel;
import com.example.a2019.ecomerceapp.Admin.RoomDataBaseUtilite.MyDatabase;
import com.example.a2019.ecomerceapp.Customers.ViewModel.ProfileVM;
import com.example.a2019.ecomerceapp.R;

import java.util.List;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    protected EditText pName;
    protected EditText pPhone;
    protected EditText pAddress;
    protected Button pEdit;
    ProfileVM vm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_profile);
        initView();


        vm= ViewModelProviders.of(this).get(ProfileVM.class);
        vm.UserProfile(new ProfileVM.OnUserPreparedListener() {
            @Override
            public void OnUserPrepared(List<UserModel> List) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.pEdit) {

        }
    }

    private void initView() {
        pName = (EditText) findViewById(R.id.pName);
        pPhone = (EditText) findViewById(R.id.pPhone);
        pAddress = (EditText) findViewById(R.id.pAddress);
        pEdit = (Button) findViewById(R.id.pEdit);
        pEdit.setOnClickListener(ProfileActivity.this);
    }


}
