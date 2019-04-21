package com.example.a2019.ecomerceapp.Customers.Activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.a2019.ecomerceapp.Admin.Models.ItemModel;
import com.example.a2019.ecomerceapp.Admin.Models.UserModel;
import com.example.a2019.ecomerceapp.Admin.RoomDataBaseUtilite.MyDatabase;
import com.example.a2019.ecomerceapp.Base.BaseActivity;
import com.example.a2019.ecomerceapp.Customers.Adapters.BasketAdapter;
import com.example.a2019.ecomerceapp.Customers.ViewModel.BasketVm;
import com.example.a2019.ecomerceapp.R;
import com.google.firebase.database.Transaction;

import java.util.ArrayList;
import java.util.List;
public class BasktActivity extends BaseActivity {
    RecyclerView myRecycler;
    BasketAdapter myAdapter;
    RecyclerView.LayoutManager layoutManager;
    BasketVm basketVm;
    Button Buy;
  static   UserModel MyUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baskt);
        basketVm = ViewModelProviders.of(this).get(BasketVm.class);
        myRecycler = findViewById(R.id.RecyclerBasket);
        Buy = findViewById(R.id.Buy);
        initAdapter();
        basketVm.GetAllFev();
        AdapterLisner();
        Buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Home.itemModels!=null)
                {
                    if(Home.itemModels.size()>0)
                    {
                        getUserThread getUserThread = new getUserThread(new OnitemUserGitListner() {
                            @Override
                            public void Userget(UserModel userModel) {
                                MyUser = userModel;
                                if(MyUser==null)
                                {
                                    startActivity(new Intent(BasktActivity.this,RegisterByNameAndPhone.class));
                                }
                                else
                                {
                                    basketVm.SendOrder(MyUser,Home.itemModels);
                                }
                            }
                        });
                        getUserThread.start();


                    }
                    else
                    {
                        showMessage("Message","No item Selected","Ok");

                    }
                }
                else
                {
                    showMessage("Message","No item Selected","Ok");

                }

            }
        });
        Observe();


    }

    private void initAdapter()
    {
        myAdapter = new BasketAdapter(null);
        layoutManager=new LinearLayoutManager(this);

    }
    private void AdapterLisner()
    {
      myAdapter.setOnDeleteClick(new BasketAdapter.onDeleteClick() {
          @Override
          public void OnClick(int pos, ItemModel itemModel) {
             Home.itemModels.remove(pos);
             finish();
             startActivity(new Intent(BasktActivity.this,BasktActivity.class));
          }
      });
    }
    private void Observe()
    {
      basketVm.getListMutableLiveData().observe(this, new Observer<List<ItemModel>>() {
          @Override
          public void onChanged(@Nullable List<ItemModel> itemModels) {
              myAdapter.ChangeData(itemModels);
              myRecycler.setAdapter(myAdapter);
              myRecycler.setLayoutManager(layoutManager);

          }
      });
      basketVm.getDone().observe(this, new Observer<Boolean>() {
          @Override
          public void onChanged(@Nullable Boolean aBoolean) {
              if(aBoolean!=null)
              {
                  if(aBoolean)
                  {
                      Toast.makeText(activity, "Your Order Uploaded", Toast.LENGTH_SHORT).show();
                      Handler handler = new Handler();
                      handler.postDelayed(new Runnable() {
                          @Override
                          public void run() {
                              startActivity(new Intent(BasktActivity.this,Home.class));
                              Home.itemModels.clear();
                              finish();
                          }
                      },1000);
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
public interface OnitemUserGitListner{
        void Userget(UserModel userModel);
}
public class getUserThread extends Thread
{
    OnitemUserGitListner onitemUserGitListner;

    private getUserThread(OnitemUserGitListner onitemUserGitListner) {
        this.onitemUserGitListner = onitemUserGitListner;
    }

    @Override
    public void run() {
        super.run();
        List<UserModel>userModel;
        userModel = new ArrayList<>();
userModel =MyDatabase.getInstance().userDao().GetAllUser();
        if(userModel!=null)
        {
            if(userModel.size()>0)
            {
                onitemUserGitListner.Userget( userModel.get(0));

            }
            else
            {
                onitemUserGitListner.Userget(null);
            }

        }
        else
        {
            onitemUserGitListner.Userget(null);
        }
    }
}
}

