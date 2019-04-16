package com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase;
import android.support.annotation.NonNull;
import com.example.a2019.ecomerceapp.Admin.Models.OrderItem;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OrdersBranches {

   private static String OrdersBranches = "OrdersBranches";
    private   static DatabaseReference GetOrderBranches()
    {

        return FirebaseDatabase.getInstance().getReference(OrdersBranches);

    }
    public static void AddOrderItem(OrderItem orderItem, OnSuccessListener onSuccessListener, OnFailureListener onFailureListener)
    {
        GetOrderBranches().child(orderItem.getItemId()).setValue(orderItem)
                .addOnSuccessListener(onSuccessListener)
                .addOnFailureListener(onFailureListener);
    }

    public static void DeleteOrderItemId(String id)
    {
        GetOrderBranches().child(id).removeValue();
    }
    public static void EditItem(OrderItem orderItem,OnSuccessListener onSuccessListener,OnFailureListener onFailureListener)
    {
        DatabaseReference myitem=   GetOrderBranches().child(orderItem.getItemId());
        myitem.removeValue();
        myitem.setValue(orderItem)
                .addOnFailureListener(onFailureListener)
                .addOnSuccessListener(onSuccessListener);
    }
    public static List<OrderItem> GetAllOrderInDb()
    {
        final List<OrderItem> AllItem = new ArrayList<>();
        GetOrderBranches().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot mydataSnapshot :dataSnapshot.getChildren())
                {
                    AllItem.add(mydataSnapshot.getValue(OrderItem.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return AllItem;
    }

}
