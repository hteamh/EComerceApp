package com.example.a2019.ecomerceapp.Admin.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.a2019.ecomerceapp.Admin.Activiteis.Commodities;
import com.example.a2019.ecomerceapp.Admin.Models.ItemModel;
import com.example.a2019.ecomerceapp.Admin.RoomDataBaseUtilite.MyDatabase;
import com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase.ItemBranches;
import com.example.a2019.ecomerceapp.FireBaseUtilite.Storge.CategoryImageBranches;
import com.example.a2019.ecomerceapp.FireBaseUtilite.Storge.ItemImageBranches;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class EditComdityVm extends AndroidViewModel {
    MutableLiveData<String> showMessage;
    MutableLiveData <Boolean> HideProgress;
    MutableLiveData <Boolean> Done;
    public EditComdityVm(@NonNull Application application) {
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

    // core fun

    public void Update(ItemModel itemModel){

        if (internetIsConnected()){
            HideProgress.postValue(false);
            if (itemModel.getImageUri()==Commodities.comodityWeWantEdit.getImageUri()){

                UpdateIntoFireBaseDB(itemModel);

            }else {
                UpdateIntoFireBaseStorge(itemModel);

            }

        }else {
            showMessage.postValue("check your internet connection");
        }


    }

   public void UpdateIntoFireBaseStorge(final ItemModel itemModel){
       ItemImageBranches.Edit(itemModel, new OnSuccessListener() {
           @Override
           public void onSuccess(Object o) {
            ItemImageBranches.GetUri(itemModel, new CategoryImageBranches.GetUriListner() {
                @Override
                public void MyUri(String Uri) {
                    ItemModel model=new ItemModel(itemModel.getName(),itemModel.getDescription(),
                            Uri,itemModel.getId(),itemModel.getCategoryName(),itemModel.getCategoryName());
                    UpdateIntoFireBaseDB(model);
                }
            });
           }
       }, new OnFailureListener() {
           @Override
           public void onFailure(@NonNull Exception e) {
               HideProgress.postValue(true);
               showMessage.postValue(e.getMessage()+"fail to add new image uri to firebase storge");
           }
       });



    }

    public void UpdateIntoFireBaseDB(final ItemModel itemModel){

        ItemBranches.EditItem(itemModel, new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                ThreadUpdateRoom th= new ThreadUpdateRoom(itemModel);
                th.start();
                HideProgress.postValue(true);
                Done.postValue(true);

            }
        }, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                HideProgress.postValue(true);
                showMessage.postValue(e.getMessage()+"fail to add new image uri to firebase db");

            }
        });

    }

    public class ThreadUpdateRoom extends Thread{
        ItemModel model;

        public ThreadUpdateRoom (ItemModel model){
            this.model=model;
        }

        @Override
        public void run() {
            super.run();
            MyDatabase.getInstance().itemDao().UpdataItem(model);
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
}
