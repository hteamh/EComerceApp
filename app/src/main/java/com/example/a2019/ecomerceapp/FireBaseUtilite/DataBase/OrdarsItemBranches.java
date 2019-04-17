package com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase;

import com.example.a2019.ecomerceapp.Admin.Models.Order;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OrdarsItemBranches {
    private static String OrdarsBranches = "Ordars";
    private static DatabaseReference GetInstance()
    {
        return FirebaseDatabase.getInstance().getReference(OrdarsBranches);
    }

    public static void AddOrder(Order order , OnSuccessListener onSuccessListener,
                                OnFailureListener onFailureListener)
    {
        GetInstance().child(order.getId()).setValue(order)
        .addOnFailureListener(onFailureListener)
        .addOnSuccessListener(onSuccessListener);

    }
}
