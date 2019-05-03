package com.example.a2019.ecomerceapp.Admin.ViewModel;

import android.app.Application;
import android.support.annotation.NonNull;
import com.example.a2019.ecomerceapp.Base.BaseViewModel;
import com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase.RoomBranch;
import com.google.firebase.database.ChildEventListener;
public class MessageFragmentVm extends BaseViewModel {
    public MessageFragmentVm(@NonNull Application application) {
        super(application);
    }


    public void GetAllRooms(ChildEventListener childEventListener) {
        RoomBranch.getRoomRef().addChildEventListener(childEventListener);
    }


}
