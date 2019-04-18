package com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase;

import android.support.annotation.NonNull;

import com.example.a2019.ecomerceapp.Admin.Models.ItemModel;
import com.example.a2019.ecomerceapp.Admin.Models.OrderModel;
import com.example.a2019.ecomerceapp.Admin.Models.Order_Commedity;
import com.example.a2019.ecomerceapp.Admin.Models.OrdersCountainer;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Order_CommedityBranche {
    private static String Orders_Commedity = "Orders_Commedity";
    private static DatabaseReference GetOrderCommedityBranches()
    {
        return FirebaseDatabase.getInstance().getReference(Orders_Commedity);
    }
    public static void AddOrder(Order_Commedity order_commedity, OnSuccessListener onSuccessListener,
                                OnFailureListener onFailureListener) {
        GetOrderCommedityBranches().push().setValue(order_commedity)
                .addOnSuccessListener(onSuccessListener)
                .addOnFailureListener(onFailureListener);
    }
    private static void GetAllOrderCommedityBrancheByOrderId(String Orderid,
     final GetAllOrderCommedityBrancheByOrderIdListner getAllOrderCommedityBrancheByOrderIdListner )
    {
       Query query = GetOrderCommedityBranches().orderByChild("orderid").equalTo(Orderid);
       query.addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               List<Order_Commedity> order_commedities = new ArrayList<>();
               for(DataSnapshot Mydata : dataSnapshot.getChildren())
               {
                  order_commedities.add(Mydata.getValue(Order_Commedity.class));
               }
               getAllOrderCommedityBrancheByOrderIdListner.onAllOrderCommedityBrancheGet(order_commedities);
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {
               getAllOrderCommedityBrancheByOrderIdListner.onAllOrderCommedityBrancheGet(null);
           }
       });
    }

    public interface GetAllOrderCommedityBrancheByOrderIdListner{

       void onAllOrderCommedityBrancheGet(List<Order_Commedity> order_commedities);

    }
    public static void GetOrderWithProductFBDB(final GetOrderWithProductListner getOrderWithProductListner)
    {
        final List <OrdersCountainer> MainOrdersCountainer = new ArrayList<>();
        final List <ItemModel> MainItemModel= new ArrayList<>();
        MainOrdersCountainer.clear();
        OrderBranches.GetAllOrdersFromFBDB(new OrderBranches.GetAllOrders() {
            @Override
            public void getallorders(List<OrderModel> orderModels) {
                if(orderModels!=null)
                {
                   for (int i=0;i<orderModels.size();i++)
                   {
                       final OrderModel  mynewOrder = orderModels.get(i);
                       GetAllOrderCommedityBrancheByOrderId(mynewOrder.getId(), new GetAllOrderCommedityBrancheByOrderIdListner() {
                           @Override
                           public void onAllOrderCommedityBrancheGet(List<Order_Commedity> order_commedities) {
                               if(order_commedities !=null)
                               {
                                   for(int j=0;j<order_commedities.size();j++)
                                   {
                                       final Order_Commedity newOrder_Commedity = order_commedities.get(j);
                                       ItemBranches.GetOneItem(newOrder_Commedity.getCommdityid(),
                                               new ItemBranches.GetOneItemListner() {
                                                   @Override
                                                   public void onitem(ItemModel itemModel) {
                                                       MainItemModel.add(new ItemModel(itemModel.getName(),
                                                       itemModel.getDescription(),itemModel.getImageUri()
                                                     ,itemModel.getId(),itemModel.getPrice(),itemModel.getCategoryName(),
                                                      newOrder_Commedity.getCounter(),itemModel.getBuyingPrice()));
                                                   }
                                               });
                                   }
                               }

                           }
                       });
                MainOrdersCountainer.add(new OrdersCountainer(mynewOrder.getId(),mynewOrder.getUserName(),mynewOrder.getUserPhone(),
                        mynewOrder.getUserAdrees(),mynewOrder.getTotalPrice(),mynewOrder.getDate(),MainItemModel));
                       MainItemModel.clear();

                   }
                    getOrderWithProductListner.onGetOrderWithProduct(MainOrdersCountainer);

                }// here i Should Add Above It One Element TO MainOrdersCountainer List
            }
        });
    }
    public interface GetOrderWithProductListner{

        void onGetOrderWithProduct(List<OrdersCountainer> ordersCountainers);

    }

}
