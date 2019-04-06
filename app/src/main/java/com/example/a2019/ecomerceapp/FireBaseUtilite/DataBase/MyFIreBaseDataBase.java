package com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase;

import com.example.a2019.ecomerceapp.Admin.Models.CategoryModel;
import com.example.a2019.ecomerceapp.Admin.Models.ItemModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

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
    public void DeleteItem(String id)
    {
        GetItemBranch().child(id).removeValue();
    }
    public void DeleteCategory(String id)
    {
        GetCategoryBranch().child( id) .removeValue();
    }

}
