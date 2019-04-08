package com.example.a2019.ecomerceapp.Admin.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.a2019.ecomerceapp.Admin.Models.CategoryModel;
import com.example.a2019.ecomerceapp.Admin.RoomDataBaseUtilite.MyDatabase;
import com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase.Categorybranches;
import com.example.a2019.ecomerceapp.FireBaseUtilite.Storge.CategoryImageBranches;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class EditCategoryVm extends AndroidViewModel {
    MutableLiveData <String> showMessage;
    MutableLiveData <Boolean> HideProgress;
    MutableLiveData <Boolean> Done;
    public EditCategoryVm(@NonNull Application application) {
        super(application);
        showMessage = new MutableLiveData<>();
        HideProgress = new MutableLiveData<>();
        Done = new MutableLiveData<>();
    }

    public MutableLiveData<Boolean> getHideProgress() {
        return HideProgress;
    }

    public MutableLiveData<Boolean> getDone() {
        return Done;
    }

    public MutableLiveData<String> getShowMessage() {
        return showMessage;
    }

    public   void UpdateCategry(CategoryModel categoryModel)
    {
        HideProgress.postValue(false);
        if(internetIsConnected())
        {
            CategoryImageBranches.Edit(categoryModel, new OnSuccessListener() {
                @Override
                public void onSuccess(Object o) {

                }
            }, new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });
            Categorybranches.EditCateory(categoryModel, new OnSuccessListener() {
                @Override
                public void onSuccess(Object o) {

                }
            }, new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });
            MyThreed myThreed =new MyThreed(categoryModel);
            HideProgress.postValue(true);
            Done.postValue(true);
            myThreed.start();

        }
        else
        {
            HideProgress.postValue(true);
          showMessage.postValue("No Internet Connection");
        }

    }
    public  boolean internetIsConnected() {
        try {
            String command = "ping -c 1 google.com";
            return (Runtime.getRuntime().exec(command).waitFor() == 0);
        } catch (Exception e) {
            return false;
        }
    }
    public class MyThreed extends Thread
    {
        CategoryModel categoryModel;

        public MyThreed(CategoryModel categoryModel) {
            this.categoryModel = categoryModel;
        }

        @Override
        public void run() {
            super.run();
            MyDatabase.getInstance().categoryDao().
     Update(categoryModel.getName(),categoryModel.getImageUri(),categoryModel.getId(),categoryModel.getDescription());
        }
    }
}
