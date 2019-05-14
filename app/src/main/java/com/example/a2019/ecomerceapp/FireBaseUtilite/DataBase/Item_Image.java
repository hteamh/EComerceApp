package com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase;

import android.support.annotation.NonNull;

import com.example.a2019.ecomerceapp.Admin.Models.CategoryModel;
import com.example.a2019.ecomerceapp.Admin.Models.Item_Images_Models;
import com.example.a2019.ecomerceapp.FireBaseUtilite.Storge.Item_Image_SD;
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
import java.util.Queue;

public class Item_Image {
    private static final String Item_Image = "ItemImage";

    private static DatabaseReference GetItemImageBranch() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        return firebaseDatabase.getReference(Item_Image);
    }

    public static void AddImage(Item_Images_Models item_images_models, OnSuccessListener onSuccessListener, OnFailureListener onFailureListener) {
        GetItemImageBranch().child(item_images_models.getId()).setValue(item_images_models)
                .addOnSuccessListener(onSuccessListener)
                .addOnFailureListener(onFailureListener);
    }
    public static void EditImage(Item_Images_Models item_images_models , OnSuccessListener onSuccessListener , OnFailureListener onFailureListener)
    {
        DatabaseReference myItem =GetItemImageBranch().child(item_images_models.getId());
        myItem.removeValue();
        myItem.setValue(item_images_models)
                .addOnFailureListener(onFailureListener)
                .addOnSuccessListener(onSuccessListener);
    }
    public static void DeleteImageyByid(String id) {
        GetItemImageBranch().child(id).removeValue();
        Item_Image_SD.DeleteImage(id);
    }
    public static void GetAllImageByItemId(String ItemId, final GetAllImageToTheItem AllImage)
    {
        final List<Item_Images_Models> item_images_modelsList = new ArrayList<>();
        Query query = GetItemImageBranch().orderByChild("item_id").equalTo(ItemId);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot MyData : dataSnapshot.getChildren())
                {
                    item_images_modelsList.add(MyData.getValue(Item_Images_Models.class)) ;
                }
                AllImage.AllImage(item_images_modelsList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public interface GetAllImageToTheItem{
       void AllImage(List<Item_Images_Models> MyList);
    }
}
