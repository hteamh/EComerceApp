package com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase;

import android.support.annotation.NonNull;

import com.example.a2019.ecomerceapp.Admin.Models.OrderModel;
import com.example.a2019.ecomerceapp.Admin.Models.Order_Commedity;
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

public class OrderBranches {
    private static String Orders = "Orders";
    public static DatabaseReference GetOrderBranches()
    {
        return FirebaseDatabase.getInstance().getReference(Orders);
    }
    public static void AddOrder(OrderModel orderModel, OnSuccessListener onSuccessListener,
       OnFailureListener onFailureListener) {
        GetOrderBranches().child(orderModel.getId()).setValue(orderModel)
         .addOnSuccessListener(onSuccessListener)
          .addOnFailureListener(onFailureListener);
    }
    public static void DeleteOrder(String ordersid)
    {
        GetOrderBranches().child(ordersid).removeValue();
     Query query =Order_CommedityBranche.GetOrderCommedityBranches().orderByChild("orderid").equalTo(ordersid);
     query.addListenerForSingleValueEvent(new ValueEventListener() {
         @Override
         public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
             for (DataSnapshot mydata:
                 dataSnapshot.getChildren() ) {
                 String id = mydata.getKey();
                 Order_CommedityBranche.GetOrderCommedityBranches().child(id).removeValue();
             }
         }

         @Override
         public void onCancelled(@NonNull DatabaseError databaseError) {

         }
     });
    }
    public static void GetAllOrder(final GetAllOrderListner getAllOrderListner)
    {
        final List<OrderModel> myOrder = new ArrayList<>();
        GetOrderBranches().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot mydata : dataSnapshot.getChildren())
                {
                  myOrder.add(mydata.getValue(OrderModel.class));
                }
                getAllOrderListner.onGetAllOrder(myOrder);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                getAllOrderListner.onGetAllOrder(null);
            }
        });
    }
    public interface GetAllOrderListner{
        void onGetAllOrder(List<OrderModel> orderModelList);
    }



}
