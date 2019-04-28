package com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase;

import android.support.annotation.NonNull;

import com.example.a2019.ecomerceapp.Customers.Models.RoomModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
public class RoomBranch {
    private static final String RoomBranch ="Rooms";
    public static DatabaseReference getRoomRef (){

        return FirebaseDatabase.getInstance().getReference(RoomBranch);
    }
    public static void AddRoom(RoomModel roomModel, OnSuccessListener onSuccessListener, OnFailureListener onFailureListener){
       getRoomRef().child(roomModel.getUid()).setValue(roomModel)
               .addOnSuccessListener(onSuccessListener)
               .addOnFailureListener(onFailureListener);
    }
    public static void GetRoomByUserId(String userId, final OnGetRoomLisetener onGetRoomLisetener) {

        Query query =  getRoomRef().orderByChild("uid").equalTo(userId);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                RoomModel MyRoom = new RoomModel();
                MyRoom = dataSnapshot.getValue(RoomModel.class);
                onGetRoomLisetener.OnRoomGet(MyRoom);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    public interface OnGetRoomLisetener {
        void OnRoomGet(RoomModel roomModel);
    }
}
