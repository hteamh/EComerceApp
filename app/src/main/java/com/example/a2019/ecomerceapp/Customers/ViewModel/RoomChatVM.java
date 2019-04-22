package com.example.a2019.ecomerceapp.Customers.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.a2019.ecomerceapp.Admin.Fragments.Categories;
import com.example.a2019.ecomerceapp.Admin.Models.ItemModel;
import com.example.a2019.ecomerceapp.Admin.Models.UserModel;
import com.example.a2019.ecomerceapp.Base.BaseViewModel;
import com.example.a2019.ecomerceapp.Customers.Models.RoomModel;
import com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase.ItemBranches;
import com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase.RoomBranch;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class RoomChatVM extends BaseViewModel {
    private List<RoomModel>list;
    private MutableLiveData<List<RoomModel>> listMutableLiveData;

    public RoomChatVM(@NonNull Application application) {
        super(application);
        listMutableLiveData=new MutableLiveData<>();
    }

    public MutableLiveData<List<RoomModel>> getListMutableLiveData() {
        return listMutableLiveData;
    }

    public void InsertNewRoom(RoomModel roomModel,String id){

        SetHideProgrees(false);

        RoomBranch.AddRoom(roomModel,id, new OnSuccessListener() {

            @Override
            public void onSuccess(Object o) {
                SetHideProgrees(true);

            }
        }, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                SetHideProgrees(true);
                SetMessage(" Can Not Add This Chat internet Connection");

            }
        });

    }

    public void GetRoom (UserModel userModel){
        Query query = RoomBranch.GetAllRoomsByUserId(userModel.getUid());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    list.add(snapshot.getValue(RoomModel.class));
                    listMutableLiveData.postValue(list);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                SetMessage(databaseError.getMessage());

            }
        });
    }
}
