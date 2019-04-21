package com.example.a2019.ecomerceapp.Admin.ViewModel;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.widget.Toast;

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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OrdersViewModels  extends BaseViewModel {
 private     MutableLiveData<List<OrdersCountainer>> OrderGetter;
 public static  MutableLiveData <String> SendToSalesBasket;

    public OrdersViewModels(@NonNull Application application) {
        super(application);
        OrderGetter = new MutableLiveData<>();
        SendToSalesBasket = new MutableLiveData<>();
    }

    public MutableLiveData<String> getSendToSalesBasket() {
        return SendToSalesBasket;
    }

    public MutableLiveData<List<OrdersCountainer>> getOrderGetter() {

        return OrderGetter;
    }
     public  void GetAllItem()
     {
         OrderBranches.GetAllOrder(new OrderBranches.GetAllOrderListner() {
             @Override
             public void onGetAllOrder(List<OrderModel> orderModelList) {
                 for(int I = 0;I<orderModelList.size();I++)
                 {

                     final int xx = I;
                     final int ZZ=orderModelList.size()-1;
  final OrderModel MyOrder = orderModelList.get(I);
          Order_CommedityBranche.GetAllOrderCommedityBrancheByOrderId(MyOrder.getId(), new Order_CommedityBranche.GetAllOrderCommedityBrancheByOrderIdListner() {
              final List<OrdersCountainer>MyOrderCountainer = new ArrayList<>();
              @Override
              public void onAllOrderCommedityBrancheGet(List<Order_Commedity> order_commedities) {
                for(int J = 0;J<order_commedities.size();J++)
                  {
                      if(xx == 0)
                      {
                          MyOrderCountainer.clear();
                      }
                      final List<ItemModel> MyItemModelList = new ArrayList<>();
                      final Order_Commedity MyOC= order_commedities.get(J);
                  final int x=J;
                  final int z=order_commedities.size()-1;
           ItemBranches.GetOneItem(MyOC.getCommdityid(), new ItemBranches.GetOneItemListner() {
               @Override
               public void onitem(ItemModel itemModels) {
                   itemModels.setCount(MyOC.getCounter());
                   MyItemModelList.add(itemModels);
                   if(x == z)
                   {
                       MyOrderCountainer.add(new OrdersCountainer(MyOrder, MyItemModelList));
                   }
                   if(xx ==ZZ)
                   {
                       OrderGetter.postValue(MyOrderCountainer);
                   }

               }
           });




                  }
              }
          });
                 }
             }
         });

     }
    public void DeleteOrderToMoveToSalesBasket(OrdersCountainer ordersCountainer) {
OrderBranches.DeleteOrder(ordersCountainer.getId());
}


    public interface GetCountainer{
        void Countainer(List <OrdersCountainer> ordersCountainers);
    }

}