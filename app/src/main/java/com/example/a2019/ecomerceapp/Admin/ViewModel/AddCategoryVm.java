package com.example.a2019.ecomerceapp.Admin.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.example.a2019.ecomerceapp.Admin.Models.CategoryModel;
import com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase.Categorybranches;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;

public class AddCategoryVm extends AndroidViewModel {

    MutableLiveData <String> ShowThisMessage;
    MutableLiveData <Boolean> HideBrogressBar;
    MutableLiveData <Boolean> OpenPanelActivity;
    MutableLiveData <List<String>>ToastMessages;
    List<String>MyToastMessagesList;

    public AddCategoryVm(@NonNull Application application) {
        super(application);
        ShowThisMessage = new MutableLiveData<>();
        HideBrogressBar = new MutableLiveData<>();
        OpenPanelActivity = new MutableLiveData<>();
        MyToastMessagesList =new ArrayList<>();
        ToastMessages = new MutableLiveData<>();
    }

    public MutableLiveData<String> getShowThisMessage() {
        return ShowThisMessage;
    }

    public MutableLiveData<Boolean> getHideBrogressBar() {
        return HideBrogressBar;
    }

    public MutableLiveData<Boolean> getOpenPanelActivity() {
        return OpenPanelActivity;
    }

    public MutableLiveData<List<String>> getToastMessages() {
        return ToastMessages;
    }
     OnSuccessListener MySuccessListenerForFireBaseDB = new OnSuccessListener() {
         @Override
         public void onSuccess(Object o) {

         }
     };
     OnFailureListener MyFailureListenerForFireBaseDB = new OnFailureListener() {
         @Override
         public void onFailure(@NonNull Exception e) {

         }
     };
     OnSuccessListener MySuccessListenerForFireBaseSC = new OnSuccessListener() {
         @Override
         public void onSuccess(Object o) {

         }
     };
     OnFailureListener MyFailureListenerForFireBaseSC = new OnFailureListener() {
         @Override
         public void onFailure(@NonNull Exception e) {

         }
     };
   public void InsertNewCategory(String name, String id, Uri ImageUri,String Description)
   {
       CategoryModel categoryModel = new CategoryModel(name,ImageUri,id,Description);
       Categorybranches.AddCategory(categoryModel,MySuccessListenerForFireBaseDB,MyFailureListenerForFireBaseDB);
       // First We Will Add Category in RealTime DataBase

   }
}
