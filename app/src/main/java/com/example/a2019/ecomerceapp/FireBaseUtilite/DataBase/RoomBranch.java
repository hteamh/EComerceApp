package com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase;

import com.example.a2019.ecomerceapp.Admin.Models.UserModel;
import com.example.a2019.ecomerceapp.Customers.Models.RoomModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class RoomBranch {
    private static final String RoomBranch ="Rooms";

    public static DatabaseReference getRoomRef (){

        return FirebaseDatabase.getInstance().getReference(RoomBranch);
    }

    public static void AddRoom(RoomModel roomModel,UserModel userModel, OnSuccessListener onSuccessListener, OnFailureListener onFailureListener){
       getRoomRef().child(userModel.getUid()).setValue(roomModel)
               .addOnSuccessListener(onSuccessListener)
               .addOnFailureListener(onFailureListener);
    }

    public static Query GetAllRoomsByUserId(String userId) {

        return getRoomRef().orderByChild(userId);
    }
}
