package com.example.a2019.ecomerceapp.Admin.ViewModel;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import com.example.a2019.ecomerceapp.Admin.Activiteis.Commodities;
import com.example.a2019.ecomerceapp.Admin.Models.ItemModel;
import com.example.a2019.ecomerceapp.Admin.RoomDataBaseUtilite.MyDatabase;
import com.example.a2019.ecomerceapp.Base.BaseViewModel;
import com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase.ItemBranches;
import com.example.a2019.ecomerceapp.FireBaseUtilite.Storge.CategoryImageBranches;
import com.example.a2019.ecomerceapp.FireBaseUtilite.Storge.ItemImageBranches;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class EditComdityVm extends BaseViewModel {

  private   MutableLiveData <Boolean> Done;
    public EditComdityVm(@NonNull Application application) {
        super(application);

        Done = new MutableLiveData<>();
    }



    public MutableLiveData<Boolean> getDone() {
        return Done;
    }



    // core fun

    public void Update(ItemModel itemModel){

        if (internetIsConnected()){
           SetHideProgrees(false);
            if (itemModel.getImageUri()==Commodities.comodityWeWantEdit.getImageUri()){

                UpdateIntoFireBaseDB(itemModel);

            }else {
                UpdateIntoFireBaseStorge(itemModel);

            }

        }else {
            SetHideProgrees(true);
           SetMessage("check your internet connection");

        }


    }

   private void UpdateIntoFireBaseStorge(final ItemModel itemModel){
       ItemImageBranches.Edit(itemModel, new OnSuccessListener() {
           @Override
           public void onSuccess(Object o) {
            ItemImageBranches.GetUri(itemModel, new CategoryImageBranches.GetUriListner() {
                @Override
                public void MyUri(String Uri) {
                    ItemModel model=new ItemModel(itemModel.getName(),itemModel.getDescription(),itemModel.getImageUri(),
                            itemModel.getId(),itemModel.getPrice(),itemModel.getCategoryName(),
                            itemModel.getCount(),itemModel.getBuyingPrice());
                    UpdateIntoFireBaseDB(model);
                }
            });
           }
       }, new OnFailureListener() {
           @Override
           public void onFailure(@NonNull Exception e) {
            SetHideProgrees(true);
               SetMessage(e.getMessage()+"fail to add new image uri to firebase storge");
           }
       });



    }

    private void UpdateIntoFireBaseDB(final ItemModel itemModel){

        ItemBranches.EditItem(itemModel, new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                SetHideProgrees(true);
                Done.postValue(true);


            }
        }, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
               SetHideProgrees(true);
                SetMessage(e.getMessage()+"fail to add new image uri to firebase db");

            }
        });

    }



}
