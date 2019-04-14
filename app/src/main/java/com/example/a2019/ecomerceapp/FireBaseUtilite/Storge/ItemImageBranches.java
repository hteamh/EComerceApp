package com.example.a2019.ecomerceapp.FireBaseUtilite.Storge;
import android.arch.persistence.room.Query;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.example.a2019.ecomerceapp.Admin.Models.CategoryModel;
import com.example.a2019.ecomerceapp.Admin.Models.ItemModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

public class ItemImageBranches {
    private static final String  ItemImageBranches = "ItemImage";
    private static StorageReference  ItemReferance()
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
    public static void  GetUri(ItemModel itemModel , final CategoryImageBranches.GetUriListner getUriListner)
    {


        ItemReferance().child(itemModel.getId())
                .getDownloadUrl()
                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        getUriListner.MyUri(uri.toString());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        getUriListner.MyUri(null);
                    }
                });
    }

    public static void Edit(ItemModel itemModel , OnSuccessListener onSuccessListener,OnFailureListener onFailureListener)
    {
        ItemReferance().child(itemModel.getId()).putFile(Uri.parse(itemModel.getImageUri()))
                .addOnSuccessListener(onSuccessListener)
                .addOnFailureListener(onFailureListener)
             ;
    }
    public  static void DeleteImage (String id)
    {
        ItemReferance().child(id).delete();

    }

}
