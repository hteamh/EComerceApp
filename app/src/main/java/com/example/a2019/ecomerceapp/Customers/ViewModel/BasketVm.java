package com.example.a2019.ecomerceapp.Customers.ViewModel;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import com.example.a2019.ecomerceapp.Admin.Models.ItemModel;
import com.example.a2019.ecomerceapp.Admin.Models.OrderModel;
import com.example.a2019.ecomerceapp.Admin.Models.Order_Commedity;
import com.example.a2019.ecomerceapp.Admin.Models.UserModel;
import com.example.a2019.ecomerceapp.Base.BaseViewModel;
import com.example.a2019.ecomerceapp.Customers.Activities.Home;
import com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase.OrderBranches;
import com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase.Order_CommedityBranche;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BasketVm extends BaseViewModel {
 private    MutableLiveData <List<ItemModel> > listMutableLiveData;
  private   List<ItemModel> models;
  private   MutableLiveData<Boolean> Done;
    public BasketVm(@NonNull Application application) {
        super(application);
        listMutableLiveData = new MutableLiveData<>();
        models =new ArrayList<>();
        Done = new MutableLiveData<>();

    }



    public MutableLiveData<Boolean> getDone() {
        return Done;
    }

    public MutableLiveData<List<ItemModel>> getListMutableLiveData() {
        return listMutableLiveData;

    }

    public void GetAllFev() {

        if(Home.itemModels!=null)
        {
            for(int i = 0; i< Home.itemModels.size(); i++)
            {
                models.add(Home.itemModels.get(i));
            }
            listMutableLiveData.postValue(models);
        }



    }

    public void SendOrder(UserModel userModel, List<ItemModel> myItemList) {
     final    String  orderid= userModel.getUid()+ System.currentTimeMillis();
        String TotalPrice= "0" ;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String data =    dateFormat.format(date);
        for(int i = 0;i < myItemList.size();i++)
        {
       TotalPrice  = Integer.toString(Integer.parseInt(TotalPrice)
         + Integer.parseInt(myItemList.get(i).getPrice())  * Integer.parseInt(myItemList.get(i).getCount()));
            Order_CommedityBranche.AddOrder(new Order_Commedity(myItemList.get(i).getId(), orderid,myItemList.get(i).getCount()), new OnSuccessListener() {
                @Override
                public void onSuccess(Object o) {
                }
            }, new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });
        }

        OrderBranches.AddOrder(new OrderModel(orderid, userModel.getName(), userModel.getPhone(), userModel.getAdrees(), TotalPrice, data), new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {

            }
        }, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
        Done.postValue(true);
    }

}
