package com.example.a2019.ecomerceapp.Customers.ViewModel;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import com.example.a2019.ecomerceapp.Admin.Models.ItemModel;
import com.example.a2019.ecomerceapp.Admin.Models.UserModel;
import com.example.a2019.ecomerceapp.Base.BaseViewModel;
import com.example.a2019.ecomerceapp.Customers.Activities.Home;

import java.util.ArrayList;
import java.util.List;

public class BasketVm extends BaseViewModel {
    MutableLiveData <List<ItemModel> > listMutableLiveData;
    List<ItemModel> models;
    public BasketVm(@NonNull Application application) {
        super(application);
        listMutableLiveData = new MutableLiveData<>();
        models =new ArrayList<>();
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
    }
}
