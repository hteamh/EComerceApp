package com.example.a2019.ecomerceapp.Admin.ViewModel;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.a2019.ecomerceapp.Admin.Models.OrderModel;
import com.example.a2019.ecomerceapp.Admin.Models.Order_Commedity;
import com.example.a2019.ecomerceapp.Admin.Models.SalesItem;
import com.example.a2019.ecomerceapp.Admin.Models.SalesModel;
import com.example.a2019.ecomerceapp.Admin.Models.UserModel;
import com.example.a2019.ecomerceapp.Base.BaseViewModel;
import com.example.a2019.ecomerceapp.Customers.Activities.Home;
import com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase.OrderBranches;
import com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase.Order_CommedityBranche;
import com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase.SalesBranches;
import com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase.SalesItemBranches;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;

public class Order_details_Vm extends BaseViewModel {
    MutableLiveData <List<Order_Commedity>> MyOrderComedity;
    MutableLiveData <Boolean> Return;
    public Order_details_Vm(@NonNull Application application) {
        super(application);
        MyOrderComedity = new MutableLiveData<>();
        Return = new MutableLiveData<>();
    }

    public MutableLiveData<Boolean> getReturn() {
        return Return;
    }

    public MutableLiveData<List<Order_Commedity>> getMyOrderComedity() {
        return MyOrderComedity;
    }

    public void GetAllOrdersItemByOrderId(String id) {
        Order_CommedityBranche.GetAllOrderCommedityBrancheByOrderId(id, new Order_CommedityBranche.GetAllOrderCommedityBrancheByOrderIdListner() {
            @Override
            public void onAllOrderCommedityBrancheGet(List<Order_Commedity> order_commedities) {
                MyOrderComedity.postValue(order_commedities);

            }
        });
    }

    public void SendToSalesBasket(final OrderModel myOrder) {
        UserModel userModel = new UserModel (myOrder.getUid(),myOrder.getName(),myOrder.getPhone(),
                myOrder.getAdrees(),myOrder.getEmail());
        final SalesModel salesModel = new SalesModel(userModel,myOrder.getId());
        Order_CommedityBranche.GetAllOrderCommedityBrancheByOrderId(myOrder.getId(), new Order_CommedityBranche.GetAllOrderCommedityBrancheByOrderIdListner() {
            @Override
            public void onAllOrderCommedityBrancheGet(List<Order_Commedity> order_commedities) {
                for(int i = 0; i<order_commedities.size();i++)
                {
                    SalesItem salesItem =
                            new SalesItem(order_commedities.get(i),i+salesModel.getId(),salesModel.getId());
                    SalesItemBranches.AddSalesItem(salesItem, new OnSuccessListener() {
                        @Override
                        public void onSuccess(Object o) {
                            OrderBranches.DeleteOrder(myOrder.getId());
                            Return.postValue(true);
                        }
                    }, new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Return.postValue(true);
                            SetMessage(e.getMessage());
                        }
                    });
                }
            }
        });



    }

    public void IgnoreThisOrder(OrderModel myOrder) {
        OrderBranches.DeleteOrder(myOrder.getId());
        Return.postValue(true);

    }
}
