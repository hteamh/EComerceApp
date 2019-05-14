package com.example.a2019.ecomerceapp.FireBaseUtilite.Storge;

import android.net.Uri;
import android.support.annotation.NonNull;

import com.example.a2019.ecomerceapp.Admin.Models.CategoryModel;
import com.example.a2019.ecomerceapp.Admin.Models.Item_Images_Models;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

public class Item_Image_SD {
    private static final String ImageBranches = "Image";

    private static StorageReference ImageReferance()
    {
        FirebaseStorage ImageReferance = FirebaseStorage.getInstance();
        ImageReferance.setMaxDownloadRetryTimeMillis(10000);  // wait 1 min for downloads
        ImageReferance.setMaxOperationRetryTimeMillis(10000);  // wait 10s for normal ops
        ImageReferance.setMaxUploadRetryTimeMillis(10000);  // wait 2 mins for uploads
        return ImageReferance.getReference(ImageBranches);

    }
    public static StorageTask AddImage(Item_Images_Models item_images_models, OnSuccessListener onSuccessListener, OnFailureListener onFailureListener)
    {
        return  ImageReferance().child(item_images_models.getId()).putFile(Uri.parse(item_images_models.getUrl()))
                .addOnSuccessListener(onSuccessListener)
                .addOnFailureListener(onFailureListener)
                ;
    }
    public   static void DeleteImage (String id)
    {
        ImageReferance().child(id).delete();
    }
    public   static void DeleteAll()
    {
        ImageReferance().delete();
    }
    public   static void  GetUri(Item_Images_Models item_images_models , final GetUriListner getUriListner)
    {


        ImageReferance().child(item_images_models.getId())
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
    public static void Edit(Item_Images_Models item_images_models,OnSuccessListener onSuccessListener ,OnFailureListener onFailureListener)
    {
        ImageReferance().child(item_images_models.getId()).delete();
        ImageReferance().child(item_images_models.getId()).putFile(Uri.parse(item_images_models.getUrl()))
                .addOnSuccessListener(onSuccessListener)
                .addOnFailureListener(onFailureListener)
        ;

    }

    ///  Make Interface Listner to Get Data From Back Ground Threed
    public interface GetUriListner{

        void MyUri(String Uri);
    }

}
