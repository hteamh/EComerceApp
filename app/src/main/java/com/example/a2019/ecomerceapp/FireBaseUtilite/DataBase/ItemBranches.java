package com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase;
import android.support.annotation.NonNull;

import com.example.a2019.ecomerceapp.Admin.Models.ItemModel;
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

public class ItemBranches {
    public static final String CategoryBranch = "CategoryBranch";
    public  static final String ItemBranch = "ItemBranch";

    public  static DatabaseReference GetItemBranch()
    {

        return FirebaseDatabase.getInstance().getReference(ItemBranch);

    }

    public static void AddItem(ItemModel itemModel,OnSuccessListener onSuccessListener, OnFailureListener onFailureListener)
    {
        GetItemBranch().child(itemModel.getId()).setValue(itemModel)
                .addOnSuccessListener(onSuccessListener)
                .addOnFailureListener(onFailureListener);
    }


    public Query GetITemByName(String Item_name)
    {
        Query query =  GetItemBranch().orderByChild("name").equalTo(Item_name);
        return query;
    }
    public static Query GetAllItemByCategoryName(String Category_name)
    {
        Query query =  GetItemBranch().orderByChild("CategoryName").equalTo(Category_name);
        return query;
    }
    public static void DeleteItemByItemId(String id)
    {
        GetItemBranch().child(id).removeValue();
    }

    public static void EditItem(ItemModel itemModel,OnSuccessListener onSuccessListener,OnFailureListener onFailureListener)
    {
        DatabaseReference myitem=   GetItemBranch().child(itemModel.getId());
        myitem.removeValue();
        myitem.setValue(itemModel)
        .addOnFailureListener(onFailureListener)
            .addOnSuccessListener(onSuccessListener);
    }
    public static List<ItemModel> GetAllItemInDb()
    {
        final List<ItemModel> AllItem = new ArrayList<>();
        GetItemBranch().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot mydataSnapshot :dataSnapshot.getChildren())
                {
                   AllItem.add(mydataSnapshot.getValue(ItemModel.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return AllItem;
    }
}
