package com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.a2019.ecomerceapp.Admin.Models.CategoryModel;
import com.example.a2019.ecomerceapp.Admin.Models.ItemModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MyFIreBaseDataBase {
    public static final String CategoryBranch = "CategoryBranch";
    public  static final String ItemBranch = "ItemBranch";
    public static DatabaseReference GetCategoryBranch()
    {
        return FirebaseDatabase.getInstance().getReference(CategoryBranch);
    }
    public  static DatabaseReference GetItemBranch()
    {
        return FirebaseDatabase.getInstance().getReference(ItemBranch);

    }

    public void AddCategory(CategoryModel categoryModel, OnSuccessListener onSuccessListener, OnFailureListener onFailureListener)
    {
        GetCategoryBranch().child(categoryModel.getId()).setValue(categoryModel)
                .addOnSuccessListener(onSuccessListener)
                .addOnFailureListener(onFailureListener);
    }
    public void AddItem(ItemModel itemModel,OnSuccessListener onSuccessListener, OnFailureListener onFailureListener)
    {
        GetItemBranch().child(itemModel.getId()).setValue(itemModel)
                .addOnSuccessListener(onSuccessListener)
                .addOnFailureListener(onFailureListener);
    }

    public Query GetCategoryByName(String Category_name)
    {
       Query query =  GetCategoryBranch().orderByChild("name").equalTo(Category_name);
       return query;
    }
    public  Query GetAllItemByCategoryName(String Category_name)
    {
        Query query =  GetItemBranch().orderByChild("CategoryName").equalTo(Category_name);
        return query;
    }
    public void DeleteItemByItemId(String id)
    {
        GetItemBranch().child(id).removeValue();
    }
    public void DeleteCategoryByid(String id)
    {
        GetCategoryBranch().child( id) .removeValue();
    }
    public void DeleteCategoryAndHisItemBYCategoryId(String id)
    {
       Query query = GetCategoryBranch().orderByChild("id").equalTo(id);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               CategoryModel categoryModel = dataSnapshot.getValue(CategoryModel.class);
               String  Name = categoryModel.getName();
               DeleteCategoryByid(categoryModel.getId());
               Query query2 = GetAllItemByCategoryName(Name);
               query2.addListenerForSingleValueEvent(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                       for (DataSnapshot item: dataSnapshot.getChildren()) {
                           ItemModel itemModel = item.getValue(ItemModel.class);
                           DeleteItemByItemId(itemModel.getId());
                       }
                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError databaseError) {

                   }
               });
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });
    }


}
