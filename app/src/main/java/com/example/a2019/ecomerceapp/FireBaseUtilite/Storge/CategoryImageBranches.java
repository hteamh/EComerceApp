package com.example.a2019.ecomerceapp.FireBaseUtilite.Storge;

import android.app.DownloadManager;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.example.a2019.ecomerceapp.Admin.Models.CategoryModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.net.URI;

public class CategoryImageBranches  {
    public static final String  CategoryImageBranches = "CategoryImage";


    public static StorageReference CategoryReferance()
    {
        FirebaseStorage  CategoryReferance = FirebaseStorage.getInstance();
        CategoryReferance.setMaxDownloadRetryTimeMillis(10000);  // wait 1 min for downloads
        CategoryReferance.setMaxOperationRetryTimeMillis(10000);  // wait 10s for normal ops
        CategoryReferance.setMaxUploadRetryTimeMillis(10000);  // wait 2 mins for uploads
       return CategoryReferance.getReference(CategoryImageBranches);

    }
    public static StorageTask AddCategoryImage(CategoryModel categoryModel, OnSuccessListener onSuccessListener, OnFailureListener onFailureListener)
    {
        return  CategoryReferance().child(categoryModel.getId()).putFile(Uri.parse(categoryModel.getImageUri()))
                .addOnSuccessListener(onSuccessListener)
                .addOnFailureListener(onFailureListener)
                 ;
    }
    public  static void DeleteImage (String id)
    {
         CategoryReferance().child(id).delete();
    }
    public static void DeleteAll()
    {
        CategoryReferance().delete();
    }
    public static void  GetUri(CategoryModel categoryModel , final GetUriListner getUriListner)
    {


        CategoryReferance().child(categoryModel.getId())
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
    public static void Edit(CategoryModel categoryModel,OnSuccessListener onSuccessListener ,OnFailureListener onFailureListener)
    {
        StorageReference myitem =  CategoryReferance().child(categoryModel.getId());
        myitem.delete();
        CategoryReferance().child(categoryModel.getId()).putFile(Uri.parse(categoryModel.getImageUri()))
        .addOnSuccessListener(onSuccessListener)
        .addOnFailureListener(onFailureListener);

    }

    ///  Make Interface Listner to Get Data From Back Ground Threed
    public interface GetUriListner{

        public void MyUri(String Uri);
    }

}
