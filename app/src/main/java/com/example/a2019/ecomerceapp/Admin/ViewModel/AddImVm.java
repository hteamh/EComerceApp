package com.example.a2019.ecomerceapp.Admin.ViewModel;

import android.app.Application;
import android.support.annotation.NonNull;

import com.example.a2019.ecomerceapp.Admin.Models.Item_Images_Models;
import com.example.a2019.ecomerceapp.Base.BaseViewModel;
import com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase.Item_Image;
import com.example.a2019.ecomerceapp.FireBaseUtilite.Storge.Item_Image_SD;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class AddImVm extends BaseViewModel {
    public AddImVm(@NonNull Application application) {
        super(application);
    }

    public void AddImage(final Item_Images_Models item_images_models) {
        SetHideProgrees(false);
        Item_Image_SD.AddImage(item_images_models, new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                Item_Image_SD.GetUri(item_images_models, new Item_Image_SD.GetUriListner() {
                    @Override
                    public void MyUri(String Uri) {
                      item_images_models.setUrl(Uri);
                        Item_Image.AddImage(item_images_models, new OnSuccessListener() {
                            @Override
                            public void onSuccess(Object o) {
                                SetHideProgrees(true);
                            }
                        }, new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                SetHideProgrees(true);
                                SetMessage(e.getMessage());
                            }
                        });

                    }
                });
            }
        }, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                SetHideProgrees(true);
                SetMessage(e.getMessage());
            }
        }) ;
    }
}
