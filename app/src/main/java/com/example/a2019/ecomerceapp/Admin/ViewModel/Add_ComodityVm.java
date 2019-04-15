package com.example.a2019.ecomerceapp.Admin.ViewModel;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import com.example.a2019.ecomerceapp.Admin.Models.ItemModel;
import com.example.a2019.ecomerceapp.Admin.RoomDataBaseUtilite.MyDatabase;
import com.example.a2019.ecomerceapp.Base.BaseViewModel;
import com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase.ItemBranches;
import com.example.a2019.ecomerceapp.FireBaseUtilite.Storge.CategoryImageBranches;
import com.example.a2019.ecomerceapp.FireBaseUtilite.Storge.ItemImageBranches;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class Add_ComodityVm extends BaseViewModel {
     private MutableLiveData <Boolean> Done;
    public Add_ComodityVm(@NonNull Application application) {
        super(application);
        Done = new MutableLiveData<>();
    }
    // Getter


    public MutableLiveData<Boolean> getDone() {
        return Done;
    }

    //CoreFun
    public void InsertNewComModity(ItemModel itemModel) {
            SetHideProgrees(false);
             if(internetIsConnected())
             {
                 InsertIntoFIreBaseStorg(itemModel);
             }
             else
             {
                 SetHideProgrees(true);
                 SetMessage(" Can Not Insert New Commodity  Cheek Your Internet Connection");
             }

    }
    private void   InsertIntoFIreBaseStorg(final ItemModel itemModel) {
        ItemImageBranches.AddItemImage(itemModel, new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                ItemImageBranches.GetUri(itemModel, new CategoryImageBranches.GetUriListner() {
                    @Override
                    public void MyUri(String Uri) {
                        if(Uri.length()>5)
                        {

                         ItemModel itemModel1 = new ItemModel(itemModel.getName()
                                 ,itemModel.getDescription(),itemModel.getImageUri(),itemModel.getId(),itemModel.getPrice(),itemModel.getCategoryName(),itemModel.getCount(),itemModel.getBuyingPrice());
                            InsertIntoFIreBaseDB(itemModel1);
                        }
                        else
                        {
                            SetHideProgrees(true);
                            SetMessage("Failed to Get Uri From Storage tO Add to FBDB");
                            Done.postValue(true);
                        }
                    }
                });
            }
        }, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                SetHideProgrees(true);
               SetMessage(e.getMessage()+"Failure On Add Image to Storage");
            }
        });
    }
    private void  InsertIntoFIreBaseDB(final ItemModel itemModel){
        ItemBranches.AddItem(itemModel, new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                SetHideProgrees(true);
                Done.postValue(true);
            }
        }, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
               SetHideProgrees(true);
                SetMessage(e.getMessage()+"Failure On Add Image to FBDB ");
            }
        });

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
