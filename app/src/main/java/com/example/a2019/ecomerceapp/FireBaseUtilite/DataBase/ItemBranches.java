package com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase;
import android.support.annotation.NonNull;

import com.example.a2019.ecomerceapp.Admin.Models.ItemModel;
import com.example.a2019.ecomerceapp.FireBaseUtilite.Storge.ItemImageBranches;
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
    private   static final String ItemBranch = "ItemBranch";

    private   static DatabaseReference GetItemBranch()
    {

        return FirebaseDatabase.getInstance().getReference(ItemBranch);

    }
    public static void AddItem(ItemModel itemModel,OnSuccessListener onSuccessListener, OnFailureListener onFailureListener)
    {
        GetItemBranch().child(itemModel.getId()).setValue(itemModel)
                .addOnSuccessListener(onSuccessListener)
                .addOnFailureListener(onFailureListener);
    }
    public static Query GetAllItemByCategoryName(String Category_name)
    {

        return GetItemBranch().orderByChild("categoryName").equalTo(Category_name);
    }
    public static void DeleteItemByItemId(String id)
    {
        GetItemBranch().child(id).removeValue();
        ItemImageBranches.DeleteImage(id);
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
