package com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase;
import com.example.a2019.ecomerceapp.Admin.Models.ItemModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

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

    public static void EditItem(ItemModel itemModel)
    {
        GetItemBranch().child(itemModel.getId()).setValue(itemModel);
    }
}
