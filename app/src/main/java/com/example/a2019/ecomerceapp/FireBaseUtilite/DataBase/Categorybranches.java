package com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase;

import android.support.annotation.NonNull;
import com.example.a2019.ecomerceapp.Admin.Models.CategoryModel;
import com.example.a2019.ecomerceapp.Admin.Models.ItemModel;
import com.example.a2019.ecomerceapp.Admin.RoomDataBaseUtilite.CategoryDao;
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

public class Categorybranches {
    public static final String CategoryBranch = "CategoryBranch";
    public static DatabaseReference GetCategoryBranch() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        return firebaseDatabase.getReference(CategoryBranch);
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

    public  static void EditCategory(CategoryModel categoryModel)
    {
        GetCategoryBranch().child(categoryModel.getId()).setValue(categoryModel);
    }
    public static List<CategoryModel> GetAllCategoryInDB()
    {
        final List<CategoryModel>AllCategoryInDB = new ArrayList();
        GetCategoryBranch().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot MydataSnapshot : dataSnapshot.getChildren())
                {
                    AllCategoryInDB.add(MydataSnapshot.getValue(CategoryModel.class));

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        return AllCategoryInDB;
    }
    public interface CallWithGetAllCategoryInDBFunction
    {
        public void GetErrorMessage(String error);
    }
}
