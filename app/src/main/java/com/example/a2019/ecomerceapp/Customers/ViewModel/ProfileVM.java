package com.example.a2019.ecomerceapp.Customers.ViewModel;

import android.app.Application;
import android.support.annotation.NonNull;

import com.example.a2019.ecomerceapp.Admin.Models.UserModel;
import com.example.a2019.ecomerceapp.Admin.RoomDataBaseUtilite.MyDatabase;
import com.example.a2019.ecomerceapp.Base.BaseViewModel;

import java.util.List;

public class ProfileVM extends BaseViewModel {

    public ProfileVM(@NonNull Application application) {
        super(application);
    }

    public void UserProfile(OnUserPreparedListener onUserPreparedListener){
        getUserThread thread= new getUserThread(onUserPreparedListener);
        thread.start();
    }

    public interface OnUserPreparedListener {

        void OnUserPrepared(List<UserModel> List);
    }

    public class getUserThread extends Thread {
        OnUserPreparedListener listener;

        getUserThread(OnUserPreparedListener listener) {
            this.listener = listener;
        }

        @Override
        public void run() {
            super.run();
            List<UserModel> list = MyDatabase.getInstance()
                    .userDao()
                    .GetAllUser();
            listener.OnUserPrepared(list);
        }
    }
}
