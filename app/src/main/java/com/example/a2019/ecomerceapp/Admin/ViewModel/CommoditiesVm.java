package com.example.a2019.ecomerceapp.Admin.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.a2019.ecomerceapp.Admin.Fragments.Categories;
import com.example.a2019.ecomerceapp.Admin.Models.ItemModel;
import com.example.a2019.ecomerceapp.Admin.RoomDataBaseUtilite.MyDatabase;
import com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase.ItemBranches;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CommoditiesVm extends AndroidViewModel {
    MutableLiveData<String> showMessage;
    List<ItemModel> list;
    MutableLiveData<List<ItemModel>> listMutableLiveData;

    public MutableLiveData<String> getShowMessage() {
        return showMessage;
    }

    public MutableLiveData<List<ItemModel>> getListMutableLiveData() {
        return listMutableLiveData;
    }

    public CommoditiesVm(@NonNull Application application) {
        super(application);
        showMessage=new MutableLiveData<>();
        listMutableLiveData=new MutableLiveData<>();
    }

    public void getData(final call call){
        list=new ArrayList<>();
        if (internetIsConnected()){
       Query query =ItemBranches.GetAllItemByCategoryName(Categories.categoryModeWeWantToSHowHisItem.getName());
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                        list.add(snapshot.getValue(ItemModel.class));


                    }
                    call.mycall(list);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    showMessage.postValue(databaseError.getMessage());

                }
            });

        }else {
            // get data from room
            getDataFromRoomDB th=new getDataFromRoomDB(list);
            th.start();
        }
    }
    // core fun
    public void setData(){

        getData(new call() {
            @Override
            public void mycall(List<ItemModel> list) {
                listMutableLiveData.postValue(list);

            }
        });

    }

    public class getDataFromRoomDB extends Thread{
        List<ItemModel> list;

        public getDataFromRoomDB(List<ItemModel> list){
            this.list=list;
        }

        @Override
        public void run() {
            super.run();
          list=  MyDatabase.getInstance().itemDao().GetAllITem();
        }
    }

    public boolean internetIsConnected() {
        try {
            String command = "ping -c 1 google.com";
            return (Runtime.getRuntime().exec(command).waitFor() == 0);
        } catch (Exception e) {
            return false;
        }
    }

    public interface call {
        void mycall(List<ItemModel> list);
    }
}
