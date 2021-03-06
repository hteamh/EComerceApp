package com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase;

import com.example.a2019.ecomerceapp.Admin.Models.CategoryModel;
import com.example.a2019.ecomerceapp.FireBaseUtilite.Storge.CategoryImageBranches;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Categorybranches {
    private static final String CategoryBranch = "CategoryBranch";

    private static DatabaseReference GetCategoryBranch() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        return firebaseDatabase.getReference(CategoryBranch);
    }

    public static void AddCategory(CategoryModel categoryModel, OnSuccessListener onSuccessListener, OnFailureListener onFailureListener) {
        GetCategoryBranch().child(categoryModel.getId()).setValue(categoryModel)
                .addOnSuccessListener(onSuccessListener)
                .addOnFailureListener(onFailureListener);
    }

    public static void EditCateory(CategoryModel categoryModel,OnSuccessListener onSuccessListener,OnFailureListener onFailureListener) {
        DatabaseReference myItem =GetCategoryBranch().child(categoryModel.getId());
        myItem.removeValue();
        myItem.setValue(categoryModel)
        .addOnFailureListener(onFailureListener)
        .addOnSuccessListener(onSuccessListener);


    }

    public static void DeleteCategoryByid(String id) {
        GetCategoryBranch().child(id).removeValue();
        CategoryImageBranches.DeleteImage(id);
    }

    public static void GetAllCategoryInDB(ValueEventListener valueEventListener)
    {
        GetCategoryBranch().addListenerForSingleValueEvent(valueEventListener);

    }

}
