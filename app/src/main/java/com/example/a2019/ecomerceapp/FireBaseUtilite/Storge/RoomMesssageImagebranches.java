package com.example.a2019.ecomerceapp.FireBaseUtilite.Storge;

import android.net.Uri;
import android.support.annotation.NonNull;

import com.example.a2019.ecomerceapp.Customers.Activities.Home;
import com.example.a2019.ecomerceapp.Customers.Models.MessageModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

public class RoomMesssageImagebranches {
    public static String name ="MessageImage";
    public static StorageReference GetMessageImageBranche() {
        return FirebaseStorage.getInstance().getReference(name);
    }
    public static StorageReference GetRoomFolderMessageImage() {
      return GetMessageImageBranche().child(Home.userModel.getUid());
    }
    public static StorageTask AddMessageImage( MessageModel messageModel
    , OnSuccessListener onSuccessListener, OnFailureListener onFailureListener) {
 return  GetRoomFolderMessageImage().child(messageModel.getData())
     .putFile(Uri.parse(messageModel.getImageUri()))
     .addOnSuccessListener(onSuccessListener)
     .addOnFailureListener(onFailureListener);
    }
    public static void DeleteAllMessageImageByRoomId(String RoomId) {
        GetMessageImageBranche().child(RoomId).delete();
    }
    public static void GetUri(MessageModel messageModel, final GetUriListener MyListener) {
        GetRoomFolderMessageImage().child(messageModel.getData()).getDownloadUrl()
        .addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                MyListener.OnGetUri(uri);
            }
        })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                MyListener.OnGetUri(null);
            }
        });
    }
    public interface GetUriListener{
        void OnGetUri(Uri uri);
    }

}
