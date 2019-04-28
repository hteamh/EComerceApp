package com.example.a2019.ecomerceapp.Customers.ViewModel;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.a2019.ecomerceapp.Admin.Models.UserModel;
import com.example.a2019.ecomerceapp.Admin.RoomDataBaseUtilite.MyDatabase;
import com.example.a2019.ecomerceapp.Base.BaseViewModel;
import com.example.a2019.ecomerceapp.Customers.Activities.Home;

import java.util.List;

public class HomeViewModel extends BaseViewModel {
    MutableLiveData <Boolean> ISRegister;
    public HomeViewModel(@NonNull Application application) {
        super(application);
        ISRegister = new MutableLiveData<>();
    }

    public MutableLiveData<Boolean> getISRegister() {
        return ISRegister;
    }

    public void CheekRegister() {
        GetUserThreadToCheek getUserThreadToCheek = new GetUserThreadToCheek(new OnUserGet() {
            @Override
            public void OnUserGet(UserModel userModel) {
                if(userModel.getPhone().length()<1)
                {
                    Home.User_name=userModel.getName();
                   ISRegister.postValue(false);
                }
                else
                {
                    ISRegister.postValue(true);
                }
            }
        }) ;
    }
    public class GetUserThreadToCheek extends Thread{
        UserModel userModel;
        OnUserGet onUserGet;
        public GetUserThreadToCheek(OnUserGet onUserGet)
        {
          this.onUserGet=onUserGet;
        }
        @Override
        public void run()
        {
         List<UserModel> userModelList =  MyDatabase.getInstance().userDao().GetAllUser();
         onUserGet.OnUserGet(userModelList.get(0));
        }

    }
    public interface OnUserGet{
        void OnUserGet(UserModel userModel);
    }

}
