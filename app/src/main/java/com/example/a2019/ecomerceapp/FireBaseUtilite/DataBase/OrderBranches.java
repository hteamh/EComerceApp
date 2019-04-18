package com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase;

import android.support.annotation.NonNull;

import com.example.a2019.ecomerceapp.Admin.Models.OrderModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OrderBranches {
    private static String Orders = "Orders";
    private static DatabaseReference GetOrderBranches()
    {
        return FirebaseDatabase.getInstance().getReference(Orders);
    }
    public static void AddOrder(OrderModel orderModel, OnSuccessListener onSuccessListener,
       OnFailureListener onFailureListener) {
        GetOrderBranches().child(orderModel.getId()).setValue(orderModel)
         .addOnSuccessListener(onSuccessListener)
          .addOnFailureListener(onFailureListener);
    }
    public static void DeleteOrder(OrderModel orderModel)
    {
        GetOrderBranches().child(orderModel.getId()).removeValue();
    }
    public static void GetAllOrdersFromFBDB(final GetAllOrders getAllOrders)
    {
         GetOrderBranches().addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 List<OrderModel> models = new ArrayList<>();
                 for(DataSnapshot mydata : dataSnapshot.getChildren())
                 {
                   models.add(mydata.getValue(OrderModel.class));
                 }
                 getAllOrders.getallorders(models);
             }

             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {
                 getAllOrders.getallorders(null);
             }
         });
    }
      public interface GetAllOrders{
        void getallorders(List<OrderModel> orderModels);
      }


}
