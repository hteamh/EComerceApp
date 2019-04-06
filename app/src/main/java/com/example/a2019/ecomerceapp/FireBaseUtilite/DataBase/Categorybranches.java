package com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase;

import android.support.annotation.NonNull;
import com.example.a2019.ecomerceapp.Admin.Models.CategoryModel;
import com.example.a2019.ecomerceapp.Admin.Models.ItemModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Categorybranches {
    public static final String CategoryBranch = "CategoryBranch";

    public static DatabaseReference GetCategoryBranch() {
        return FirebaseDatabase.getInstance().getReference(CategoryBranch);
    }

    public static void AddCategory(CategoryModel categoryModel, OnSuccessListener onSuccessListener, OnFailureListener onFailureListener) {
        GetCategoryBranch().child(categoryModel.getId()).setValue(categoryModel)
                .addOnSuccessListener(onSuccessListener)
                .addOnFailureListener(onFailureListener);
    }

    public static Query  GetCategoryByName(String Category_name) {
        Query query = GetCategoryBranch().orderByChild("name").equalTo(Category_name);
       return  query;
    }

    public static void DeleteCategoryByid(String id) {
        GetCategoryBranch().child(id).removeValue();
    }

    public static void DeleteCategoryAndHisItemBYCategoryId(String id) {
        Query query = GetCategoryBranch().orderByChild("id").equalTo(id);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                CategoryModel categoryModel = dataSnapshot.getValue(CategoryModel.class);
                String Name = categoryModel.getName();
                DeleteCategoryByid(categoryModel.getId());
                Query query2 = ItemBranches.GetAllItemByCategoryName(Name);
                query2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot item : dataSnapshot.getChildren()) {
                            ItemModel itemModel = item.getValue(ItemModel.class);
                            ItemBranches.DeleteItemByItemId(itemModel.getId());
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        /// here we should show message that the item do not Remove
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                /// here we should show message that the Category do not Remove

            }
        });
    }

    public  static void EditCategory(CategoryModel categoryModel)
    {
        GetCategoryBranch().child(categoryModel.getId()).setValue(categoryModel);
    }
}
