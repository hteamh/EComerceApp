package com.example.a2019.ecomerceapp.FireBaseUtilite.Storge;
import android.net.Uri;

import com.example.a2019.ecomerceapp.Admin.Models.CategoryModel;
import com.example.a2019.ecomerceapp.Admin.Models.ItemModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

public class ItemImageBranches {
    public static final String  ItemImageBranches = "ItemImage";
    public static StorageReference  ItemReferance()
    {
        FirebaseStorage firebaseStorage =  FirebaseStorage.getInstance();

        firebaseStorage.setMaxDownloadRetryTimeMillis(10000);  // wait 1 min for downloads
        firebaseStorage.setMaxOperationRetryTimeMillis(10000);  // wait 10s for normal ops
        firebaseStorage.setMaxUploadRetryTimeMillis(10000);  // wait 2 mins for uploads
        return firebaseStorage.getReference(ItemImageBranches);
    }
    public static StorageTask AddItemImage(ItemModel itemModel, OnSuccessListener onSuccessListener, OnFailureListener onFailureListener)
    {
        return  ItemReferance().child(itemModel.getId()).putFile(Uri.parse(itemModel.getImageUri()))
                .addOnSuccessListener(onSuccessListener)
                .addOnFailureListener(onFailureListener)
                ;
    }
    public  static Uri GetItemImageUri(String id)
    {
        Task<Uri> uri=ItemReferance().child(id).getDownloadUrl();
        Uri uri1 = uri.getResult();
        return uri1;
    }
    public static void Edit(ItemModel itemModel , OnSuccessListener onSuccessListener,OnFailureListener onFailureListener)
    {
        StorageReference myitem =  ItemReferance().child(itemModel.getId());
        StorageReference myImgeitem =ItemReferance().child(itemModel.getId()).child(itemModel.getImageUri());
        myitem.putFile(Uri.parse(itemModel.getImageUri()))
        .addOnSuccessListener(onSuccessListener)
        .addOnFailureListener(onFailureListener);
    }
    public  static void DeleteImage (String id)
    {
        ItemReferance().child(id).delete();
    }
    public static void DeleteAll()
    {
        ItemReferance().delete();
    }

}
