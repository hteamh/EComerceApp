package com.example.a2019.ecomerceapp.Customers.ViewModel;

import android.app.Application;
import android.arch.persistence.room.RoomDatabase;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.example.a2019.ecomerceapp.Base.BaseViewModel;
import com.example.a2019.ecomerceapp.Customers.Activities.ChatActivity;
import com.example.a2019.ecomerceapp.Customers.Activities.Home;
import com.example.a2019.ecomerceapp.Customers.Models.MessageModel;
import com.example.a2019.ecomerceapp.Customers.Models.RoomModel;
import com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase.MessageBranch;
import com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase.RoomBranch;
import com.example.a2019.ecomerceapp.FireBaseUtilite.Storge.RoomMesssageImagebranches;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageVm extends BaseViewModel {
    public MessageVm(@NonNull Application application) {
        super(application);
    }

    public void SetLocationMessage(Location currentLocatin) {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd HH:mm");
        Date date = new Date();
        String Lat = Double.toString(currentLocatin.getLatitude());
        String lon = Double.toString(currentLocatin.getLongitude());
        String data =    dateFormat.format(date);
        MessageModel messageModel =
                new MessageModel(Home.roomModel.getRoomId(),Home.userModel.getUid(),
                Lat,lon,Home.userModel.getName(),data);
        MessageBranch.AddMessage(messageModel, new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
            }
        }, new OnFailureListener() {
            @Override public void onFailure(@NonNull Exception e) {

            }
        });
    }

    public void SetTextMessage(String message) {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd HH:mm");
        Date date = new Date();
        String data =    dateFormat.format(date);
        MessageModel messageModel = new MessageModel(Home.roomModel.getRoomId(),Home.userModel.getUid(),
                message,Home.userModel.getName(),data);
        MessageBranch.AddMessage(messageModel, new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
            }
        }, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    public void SetImageMessage(final Uri imageUri) {
        SetHideProgrees(false);
        DateFormat dateFormat = new SimpleDateFormat("MM/dd HH:mm");
        Date date = new Date();
        final String data =    dateFormat.format(date);
        final MessageModel messageModel = new MessageModel(imageUri.toString(),Home.userModel.getUid(),
                Home.userModel,data);

        RoomMesssageImagebranches.AddMessageImage(messageModel, new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                RoomMesssageImagebranches.GetUri(messageModel, new RoomMesssageImagebranches.GetUriListener() {
                    @Override
                    public void OnGetUri(Uri uri) {
                        MessageModel message = new MessageModel(uri.toString(),Home.roomModel.getRoomId(),
                                Home.userModel,data);
                        MessageBranch.AddMessage(message, new OnSuccessListener() {
                            @Override
                            public void onSuccess(Object o) {
                                // upload image message success
                                SetHideProgrees(true);
                            }
                        }, new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                SetHideProgrees(true);

                            }
                        });
                    }
                });
            }
        }, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                SetHideProgrees(true);

            }
        });
    }

}
