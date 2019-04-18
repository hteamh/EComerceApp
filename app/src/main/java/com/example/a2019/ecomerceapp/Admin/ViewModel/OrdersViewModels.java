package com.example.a2019.ecomerceapp.Admin.ViewModel;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.a2019.ecomerceapp.Admin.Models.OrdersCountainer;
import com.example.a2019.ecomerceapp.Base.BaseViewModel;
import com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase.Order_CommedityBranche;

import java.util.List;

public class OrdersViewModels  extends BaseViewModel {
 private    MutableLiveData<List<OrdersCountainer>> OrderGetter;
    public OrdersViewModels(@NonNull Application application) {
        super(application);
        OrderGetter = new MutableLiveData<>();
    }

    public MutableLiveData<List<OrdersCountainer>> getOrderGetter() {

        return OrderGetter;
    }

    public void GetAllOrders() {
        Order_CommedityBranche.GetOrderWithProductFBDB(new Order_CommedityBranche.GetOrderWithProductListner() {
            @Override
            public void onGetOrderWithProduct(List<OrdersCountainer> ordersCountainers) {
                if(ordersCountainers!=null)
                {
                    OrderGetter.postValue(ordersCountainers);

                }
            }
        });
}
}
