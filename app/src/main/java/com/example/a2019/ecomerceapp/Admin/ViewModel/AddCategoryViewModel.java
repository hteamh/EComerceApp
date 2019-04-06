package com.example.a2019.ecomerceapp.Admin.ViewModel;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

import android.arch.lifecycle.MutableLiveData;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.example.a2019.ecomerceapp.Admin.Models.CategoryModel;
import com.example.a2019.ecomerceapp.Admin.RoomDataBaseUtilite.MyDatabase;
import com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase.Categorybranches;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;


public class AddCategoryViewModel extends AndroidViewModel {
     MutableLiveData<String> ErrorMessage ;
    MutableLiveData <Boolean> HideProgressPar;
    public AddCategoryViewModel(@NonNull Application application) {
        super(application);
        ErrorMessage = new MutableLiveData<>();
        HideProgressPar = new MutableLiveData<>();
    }

    public MutableLiveData<String> getErrorMessage() {
        return ErrorMessage;
    }

    public MutableLiveData<Boolean> getHideProgressPar() {
        return HideProgressPar;
    }

     final  OnSuccessListener MyOnSuccessListenerForAddedSuccessfulyInFaireBase = new OnSuccessListener() {
        @Override
        public void onSuccess(Object o) { HideProgressPar.postValue(true);
        //
            //
            //***********
            // here we should Send to Activity to start 
            //**********
            //
        }
    };
     final OnFailureListener  MyOnFailureListenerForFailureAddedInFaireBase = new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) { ErrorMessage.postValue("Failure Add Category"); }
    };

    public  void InsertNewCategory(String name, String id, String Description , Uri uri)
    {
        CategoryModel categoryModel = new CategoryModel(name,uri,id,Description);
        // Add In FireBase
       Categorybranches.AddCategory(categoryModel,MyOnSuccessListenerForAddedSuccessfulyInFaireBase,MyOnFailureListenerForFailureAddedInFaireBase);

        // AddImage In Storage

        // Add in room database
    }
    public class Addthread extends Thread
    {
        CategoryModel categoryModel;
        public Addthread ( CategoryModel categoryModel)
        {
            this.categoryModel =categoryModel;
        }

        @Override
        public void run() {
            super.run();
            MyDatabase.getInstance().categoryDao().AddCategory(this.categoryModel);
        }
    }

    }
