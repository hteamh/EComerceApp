package com.example.a2019.ecomerceapp.Admin.ViewModel;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.a2019.ecomerceapp.Admin.Models.OrderModel;
import com.example.a2019.ecomerceapp.Base.BaseViewModel;
import com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase.OrderBranches;

import java.util.List;

public class orderViewModel extends BaseViewModel {
    MutableLiveData<List<OrderModel> >MyOrders ;
    public orderViewModel(@NonNull Application application) {
        super(application);
        MyOrders = new MutableLiveData<>();
    }

    public MutableLiveData<List<OrderModel>> getMyOrders() {
        return MyOrders;
    }

    public void GetAllOrders() {
        OrderBranches.GetAllOrder(new OrderBranches.GetAllOrderListner() {
            @Override
            public void onGetAllOrder(List<OrderModel> orderModelList) {
                MyOrders.postValue(orderModelList);
            }
        });
    }
}
