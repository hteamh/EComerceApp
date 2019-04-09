package com.example.a2019.ecomerceapp.Admin.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import com.example.a2019.ecomerceapp.Admin.Models.ItemModel;
import com.example.a2019.ecomerceapp.Admin.RoomDataBaseUtilite.MyDatabase;
import com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase.ItemBranches;
import com.example.a2019.ecomerceapp.FireBaseUtilite.Storge.CategoryImageBranches;
import com.example.a2019.ecomerceapp.FireBaseUtilite.Storge.ItemImageBranches;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class Add_ComodityVm extends AndroidViewModel {
      MutableLiveData <Boolean> HideprogressBar;
      MutableLiveData <String>  showmessage;
      MutableLiveData <Boolean> Done;
    public Add_ComodityVm(@NonNull Application application) {
        super(application);
    }
    // Getter

    public MutableLiveData<Boolean> getHideprogressBar() {
        return HideprogressBar;
    }

    public MutableLiveData<String> getShowmessage() {
        return showmessage;
    }

    public MutableLiveData<Boolean> getDone() {
        return Done;
    }

    //CoreFun
    public void InsertNewComModity(ItemModel itemModel) {
             HideprogressBar.postValue(false);
             if(internetIsConnected())
             {
                 InsertIntoFIreBaseStorg(itemModel);
             }
             else
             {
                 HideprogressBar.postValue(true);
                 showmessage.postValue(" Cheek Your Internet Connection");
             }

    }
    public void   InsertIntoFIreBaseStorg(final ItemModel itemModel) {
        ItemImageBranches.AddItemImage(itemModel, new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                ItemImageBranches.GetUri(itemModel, new CategoryImageBranches.GetUriListner() {
                    @Override
                    public void MyUri(String Uri) {
                        if(Uri.length()>5)
                        {
                         ItemModel itemModel1 = new ItemModel(itemModel.getName(),itemModel.getDescription(),Uri,itemModel.getId(),
                                 itemModel.getPrice(),itemModel.getCategoryName());
                            InsertIntoFIreBaseDB(itemModel);
                        }
                        else
                        {
                            showmessage.postValue("Failed to Get Uri From Storage tO Add to FBDB");
                        }
                    }
                });
            }
        }, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                HideprogressBar.postValue(true);
               showmessage.postValue(e.getMessage()+"Failure On Add Image to Storage");
            }
        });
    }
    public void  InsertIntoFIreBaseDB(final ItemModel itemModel){
        ItemBranches.AddItem(itemModel, new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                InsertIntoRoomDB(itemModel);
            }
        }, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                HideprogressBar.postValue(true);
                showmessage.postValue(e.getMessage()+"Failure On Add Image to FBDB ");
            }
        });

    }
    public void InsertIntoRoomDB(ItemModel itemModel){
        InsertThreedRoomDb insertThreedRoomDb = new InsertThreedRoomDb(itemModel);
        insertThreedRoomDb.start();
        HideprogressBar.postValue(true);
        Done.postValue(true);
    }
    public class InsertThreedRoomDb extends Thread {
        ItemModel itemModel;
       public  InsertThreedRoomDb(ItemModel itemModel){
           this.itemModel=itemModel;
       }

        @Override
        public void run() {
            super.run();
            MyDatabase.getInstance().itemDao().AddItem(itemModel);
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
}
