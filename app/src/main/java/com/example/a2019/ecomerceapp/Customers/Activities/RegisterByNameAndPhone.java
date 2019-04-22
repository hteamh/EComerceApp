package com.example.a2019.ecomerceapp.Customers.Activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.Toast;

import com.example.a2019.ecomerceapp.Admin.Models.UserModel;
import com.example.a2019.ecomerceapp.Admin.RoomDataBaseUtilite.MyDatabase;
import com.example.a2019.ecomerceapp.Base.BaseActivity;
import com.example.a2019.ecomerceapp.Customers.ViewModel.RegisterVM;
import com.example.a2019.ecomerceapp.R;

public class RegisterByNameAndPhone extends BaseActivity {
    TextInputLayout Username,UserPhone,UserAdrees;
    Button Login;
    RegisterVM myviewModel;
    public static UserModel Us;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_by_name_and_phone);
        myviewModel= ViewModelProviders.of(this).get(RegisterVM.class);
        Init();
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HandelData();
                Observe();
            }
        });

    }
    private void Init()
    {
        this.Username = findViewById(R.id.user_Name);
        this.UserPhone=findViewById(R.id.user_phone);
        this.Login = findViewById(R.id.Login);
        this.UserAdrees = findViewById(R.id.user_Adrees);
    }
    private void HandelData()
    {
        final String Username,Userphone,UserAdrees;
        Username =this.Username.getEditText().getText().toString().trim();
        Userphone=this.UserPhone.getEditText().getText().toString().trim();
        UserAdrees = this.UserAdrees.getEditText().getText().toString().trim();
        if(UserAdrees.isEmpty() || Username.isEmpty() || Userphone.isEmpty())
                {
                    showMessage("message","You should Full All Data Filed","ok");
                }
                else
                {
                    String Uid = System.currentTimeMillis()+Username;
                    UserModel userModel = new UserModel(Uid,Username,Userphone,UserAdrees);
                    myviewModel.login(userModel);
                    Us = userModel;
                }
            }

    private void Observe()
    {
        myviewModel.getDone().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if(aBoolean !=null)
                {
                    if(aBoolean)
                    {
                        Toast.makeText(activity, "Your Login is success", Toast.LENGTH_LONG).show();
                        AddUserThread addUserThread = new AddUserThread(Us);
                        addUserThread.start();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        },2000);
                    }
                    else
                    {
                        Toast.makeText(activity, "Your Login is filed", Toast.LENGTH_LONG).show();
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

    public class AddUserThread extends Thread
    {
        UserModel userModel;

        public AddUserThread(UserModel userModel) {
            this.userModel = userModel;
        }

        @Override
        public void run() {
            super.run();
            MyDatabase.getInstance().userDao().AddUser(userModel);
        }
    }
}
