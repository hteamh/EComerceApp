package com.example.a2019.ecomerceapp.FireBaseUtilite.Storge;

import android.net.Uri;

import com.example.a2019.ecomerceapp.Admin.Models.CategoryModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
public class CategoryImageBranches  {
    public static final String  CategoryImageBranches = "CategoryImage";


    public static StorageReference CategoryReferance()
    {
       return FirebaseStorage.getInstance().getReference(CategoryImageBranches);
    }
    public static StorageTask AddCategoryImage(CategoryModel categoryModel, OnSuccessListener onSuccessListener, OnFailureListener onFailureListener)
    {
        return  CategoryReferance().child(categoryModel.getId()).putFile(Uri.parse(categoryModel.getImageUri()))
                .addOnSuccessListener(onSuccessListener)
                .addOnFailureListener(onFailureListener)
                 ;
    }
    public  static Uri GetCategoryImageUri(String id)
    {
     Task<Uri> uri=CategoryReferance().child(id).getDownloadUrl();
       Uri uri1 = uri.getResult();
       return uri1;
    }
}
