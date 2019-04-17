package com.example.a2019.ecomerceapp.Customers.ViewModel;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.a2019.ecomerceapp.Admin.Models.UserModel;
import com.example.a2019.ecomerceapp.Base.BaseViewModel;
import com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase.UserBranches;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class RegisterVM extends BaseViewModel {
       MutableLiveData<Boolean> Done;
    public RegisterVM(@NonNull Application application) {
        super(application);
        Done  = new MutableLiveData<>();
    }

    public MutableLiveData<Boolean> getDone() {
        return Done;
    }

    public void login(UserModel userModel) {
        UserBranches.AddUser(userModel, new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
               Done.postValue(true);
            }
        }, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Done.postValue(false);
            }
        });

    }
}
