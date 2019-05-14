package com.example.a2019.ecomerceapp.Admin.ViewModel;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.a2019.ecomerceapp.Admin.Fragments.Oreders;
import com.example.a2019.ecomerceapp.Admin.Models.ItemModel;
import com.example.a2019.ecomerceapp.Admin.Models.OrderModel;
import com.example.a2019.ecomerceapp.Admin.Models.Order_Commedity;
import com.example.a2019.ecomerceapp.Admin.Models.OrdersCountainer;
import com.example.a2019.ecomerceapp.Admin.Models.SalesItem;
import com.example.a2019.ecomerceapp.Admin.Models.SalesModel;
import com.example.a2019.ecomerceapp.Admin.Models.UserModel;
import com.example.a2019.ecomerceapp.Base.BaseViewModel;
import com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase.ItemBranches;
import com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase.OrderBranches;
import com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase.Order_CommedityBranche;
import com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase.SalesBranches;
import com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase.SalesItemBranches;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrdersViewModels extends BaseViewModel {
    private MutableLiveData<OrdersCountainer> OrderGetter;
    public static OrdersCountainer MyOrderCountainer;


    public OrdersViewModels(@NonNull Application application) {
        super(application);
        OrderGetter = new MutableLiveData<>();
    }

    public MutableLiveData<OrdersCountainer> getOrderGetter() {

        return OrderGetter;
    }

    public void GetOneOrder() {
        OrderBranches.GetAllOrder(new OrderBranches.GetAllOrderListner() {
            @Override
            public void onGetAllOrder(List<OrderModel> orderModelList) {
                if (orderModelList.size() <= 0) {
                    OrderGetter.postValue(null);
                }
                for (int I = 0; I < orderModelList.size(); I++) {
                    final List<ItemModel> MyItemModelList = new ArrayList<>();
                    MyItemModelList.clear();
                    final OrderModel MyOrder = orderModelList.get(I);
                    Order_CommedityBranche.GetAllOrderCommedityBrancheByOrderId(MyOrder.getId(), new Order_CommedityBranche.GetAllOrderCommedityBrancheByOrderIdListner() {
                        @Override
                        public void onAllOrderCommedityBrancheGet(List<Order_Commedity> order_commedities) {
                            for (int J = 0; J < order_commedities.size(); J++) {
                                final Order_Commedity MyOC = order_commedities.get(J);
                                final int x = J;
                                final int z = order_commedities.size() - 1;
                                ItemBranches.GetOneItem(MyOC.getCommdityid(), new ItemBranches.GetOneItemListner() {
                                    @Override
                                    public void onitem(ItemModel itemModels) {
                                        itemModels.setCount(MyOC.getCounter());
                                        MyItemModelList.add(itemModels);
                                        if (x == z) {
                                            MyOrderCountainer = new OrdersCountainer(MyOrder, MyItemModelList);
                                            OrderGetter.postValue(MyOrderCountainer);


                                        }


                                    }
                                });


                            }
                        }
                    });
                    return;
                }
            }
        });

    }

    public void DeleteOrderToMoveToSalesBasket(OrdersCountainer ordersCountainer) {
        OrderBranches.DeleteOrder(ordersCountainer.getId());
        SendOrderToSalesBasket(ordersCountainer);
    }

    void SendOrderToSalesBasket(final OrdersCountainer ordersCountainer) {
        UserModel MyuserModel = new UserModel(ordersCountainer.getUid(), ordersCountainer.getName()
                , ordersCountainer.getPhone(), ordersCountainer.getAdrees());
        final SalesModel MysalesModel = new SalesModel(MyuserModel, ordersCountainer.getId());
        SalesBranches.AddSalesBell(MysalesModel, new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                List<ItemModel> MyItemList = new ArrayList<>();
                MyItemList = ordersCountainer.getItemModels();
                for (int i = 0; i < MyItemList.size(); i++) {
                    ItemModel MyItemModel = MyItemList.get(i);
                    SalesItem salesItem = new SalesItem(MyItemModel, MysalesModel.getId());
                    SalesItemBranches.AddSalesItem(salesItem, new OnSuccessListener() {
                        @Override
                        public void onSuccess(Object o) {
                        }
                    }, new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            SetMessage(e.getMessage() + "Failure to add new salesItem");

                        }
                    });
                }
            }
        }, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                SetMessage(e.getMessage() + "Failure to add new sales bill");
            }
        });


    }

}
