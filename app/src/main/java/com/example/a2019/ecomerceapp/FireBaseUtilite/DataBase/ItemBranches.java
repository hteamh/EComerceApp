package com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase;
import android.support.annotation.NonNull;

import com.example.a2019.ecomerceapp.Admin.Models.ItemModel;
import com.example.a2019.ecomerceapp.Admin.Models.Order_Commedity;
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
    private static final String ItemBranch = "ItemBranch";

    public static DatabaseReference GetItemBranch() {

        return FirebaseDatabase.getInstance().getReference(ItemBranch);

    }

    public static void AddItem(ItemModel itemModel, OnSuccessListener onSuccessListener, OnFailureListener onFailureListener) {
        GetItemBranch().child(itemModel.getId()).setValue(itemModel)
                .addOnSuccessListener(onSuccessListener)
                .addOnFailureListener(onFailureListener);
    }

    public static Query GetAllItemByCategoryName(String Category_name) {

        return GetItemBranch().orderByChild("categoryName").equalTo(Category_name);
    }

    public static void DeleteItemByItemId(String id) {
        GetItemBranch().child(id).removeValue();
        ItemImageBranches.DeleteImage(id);
    }

    public static void EditItem(ItemModel itemModel, OnSuccessListener onSuccessListener, OnFailureListener onFailureListener) {
        DatabaseReference myitem = GetItemBranch().child(itemModel.getId());
        myitem.removeValue();
        myitem.setValue(itemModel)
                .addOnFailureListener(onFailureListener)
                .addOnSuccessListener(onSuccessListener);
    }

    public static void GetAllItemInDb(final GetAllItemListner getAllItemListner) {
        final List<ItemModel> AllItem = new ArrayList<>();
        GetItemBranch().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot mydataSnapshot : dataSnapshot.getChildren()) {
                    AllItem.add(mydataSnapshot.getValue(ItemModel.class));
                }
                getAllItemListner.GetAll(AllItem);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public static void GetOneItem(final String ItemModelid, final GetOneItemListner getOneItemListner) {
        Query query = GetItemBranch().orderByChild("id").equalTo(ItemModelid);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot Myitem : dataSnapshot.getChildren()) {
                    getOneItemListner.onitem(Myitem.getValue(ItemModel.class));
                    break;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                getOneItemListner.onitem(null);
            }
        });
    }

    public interface GetOneItemListner {
        public void onitem(ItemModel itemModels);
    }

    public interface GetAllItemListner {
        void GetAll(List<ItemModel> itemModels);
    }

}
