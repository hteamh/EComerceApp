package com.example.a2019.ecomerceapp.FireBaseUtilite.Storge;
import android.net.Uri;
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
        return FirebaseStorage.getInstance().getReference(ItemImageBranches);
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
}
