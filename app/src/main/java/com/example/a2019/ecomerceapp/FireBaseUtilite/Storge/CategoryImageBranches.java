package com.example.a2019.ecomerceapp.FireBaseUtilite.Storge;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class CategoryImageBranches  {
    public static final String  CategoryImageBranches = "CategoryImage";
    public  static FirebaseStorage firebaseStorage;

    public static StorageReference CategoryReferance()
    {
       return FirebaseStorage.getInstance().getReference(CategoryImageBranches);
    }
}
