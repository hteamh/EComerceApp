package com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase;

import android.support.annotation.NonNull;

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

public class Order_CommedityBranche {
    private static String Orders_Commedity = "Orders_Commedity";
    public static DatabaseReference GetOrderCommedityBranches()
    {
        return FirebaseDatabase.getInstance().getReference(Orders_Commedity);
    }
    public static void AddOrder(Order_Commedity order_commedity, OnSuccessListener onSuccessListener,
                                OnFailureListener onFailureListener) {
        GetOrderCommedityBranches().push().setValue(order_commedity)
                .addOnSuccessListener(onSuccessListener)
                .addOnFailureListener(onFailureListener);
    }
    public static void GetAllOrderCommedityBrancheByOrderId(String Orderid,
     final GetAllOrderCommedityBrancheByOrderIdListner getAllOrderCommedityBrancheByOrderIdListner )
    {
        final List<Order_Commedity> orderModelList = new ArrayList<>();
        Query query = GetOrderCommedityBranches().orderByChild("orderid").equalTo(Orderid);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot mydata : dataSnapshot.getChildren())
                {
                  orderModelList.add(mydata.getValue(Order_Commedity.class));
                }
                getAllOrderCommedityBrancheByOrderIdListner.onAllOrderCommedityBrancheGet(orderModelList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public interface GetAllOrderCommedityBrancheByOrderIdListner{

       void onAllOrderCommedityBrancheGet(List<Order_Commedity> order_commedities);

    }

    }



