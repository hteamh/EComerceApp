package com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase;

import android.support.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.a2019.ecomerceapp.Admin.Models.SalesCountainer;
import com.example.a2019.ecomerceapp.Admin.Models.SalesItem;
import com.example.a2019.ecomerceapp.Admin.Models.SalesModel;
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

public class SalesItemBranches {
    private static String SalesName = "SalesItem";
    private static DatabaseReference GetSalesItemBranches()
    {
        return FirebaseDatabase.getInstance().getReference(SalesName);
    }
    public static void AddSalesItem(SalesItem salesItem, OnSuccessListener onSuccessListener,
                                    OnFailureListener onFailureListener)
    {
        GetSalesItemBranches().push().setValue(salesItem)
                .addOnFailureListener(onFailureListener).addOnSuccessListener(onSuccessListener);
    }

    public static void GetAllSalesItemBySalesOrderId(String SalesOrderID,
                                                     final GetAllSalesItemBySalesOrderIdListner getAllSalesItemBySalesOrderIdListner)
    {
        final List<SalesItem> salesItems = new ArrayList<>();
       Query query =  GetSalesItemBranches().orderByChild("salesid").equalTo(SalesOrderID);
       query.addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               salesItems.clear();
               for(DataSnapshot mydata : dataSnapshot.getChildren())
               {
                salesItems.add(mydata.getValue(SalesItem.class));
               }
               getAllSalesItemBySalesOrderIdListner.OnGetAllSalesItemBySalesOrder(salesItems);
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {
            getAllSalesItemBySalesOrderIdListner.OnGetAllSalesItemBySalesOrder(null);
           }
       });
    }
    public interface GetAllSalesItemBySalesOrderIdListner{
         void OnGetAllSalesItemBySalesOrder(List<SalesItem> MyList);
    }

}
