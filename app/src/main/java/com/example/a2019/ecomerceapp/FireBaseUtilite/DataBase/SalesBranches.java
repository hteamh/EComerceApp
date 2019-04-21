package com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase;

import android.support.annotation.NonNull;

import com.example.a2019.ecomerceapp.Admin.Models.SalesItem;
import com.example.a2019.ecomerceapp.Admin.Models.SalesModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SalesBranches {
    private static String SalesName = "Sales";
    private static DatabaseReference GetSalesBranches()
    {
        return FirebaseDatabase.getInstance().getReference(SalesName);
    }
    public static void AddSalesBell(SalesModel salesModel, OnSuccessListener onSuccessListener,
                                    OnFailureListener onFailureListener)
    {
        GetSalesBranches().child(salesModel.getId()).setValue(salesModel)
     .addOnFailureListener(onFailureListener).addOnSuccessListener(onSuccessListener);
    }
    public  static void DeleteSlaesBell(SalesModel salesModel)
    {
        GetSalesBranches().child(salesModel.getId()).removeValue();
    }
    public  static  void EditSlaesBill(SalesModel salesModel,OnFailureListener onFailureListener , OnSuccessListener onSuccessListener)
    {
        DeleteSlaesBell(salesModel);
        AddSalesBell(salesModel,onSuccessListener,onFailureListener);
    }
    public  static void GetAllSales(final GetAllSalesListner getAllSalesListner)
    {
        final List<SalesModel> SalesModel = new ArrayList<>();
        GetSalesBranches().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                SalesModel.clear();
                for(DataSnapshot mydatasnapshot : dataSnapshot.getChildren())
                {
                    SalesModel.add(mydatasnapshot.getValue(SalesModel.class));
                }
                getAllSalesListner.onGetAllSales(SalesModel);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                getAllSalesListner.onGetAllSales(null);
            }
        });
    }
    public interface GetAllSalesListner{
        public void onGetAllSales(List<SalesModel> MyList);
    }
}
