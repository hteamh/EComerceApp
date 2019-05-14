package com.example.a2019.ecomerceapp.Admin.ViewModel;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.a2019.ecomerceapp.Admin.Models.Item_Images_Models;
import com.example.a2019.ecomerceapp.Base.BaseViewModel;
import com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase.Item_Image;

import java.util.List;

public class Add_Item_Image_VM extends BaseViewModel {
    MutableLiveData <List<Item_Images_Models>> myList;
    public Add_Item_Image_VM(@NonNull Application application) {
        super(application);
        myList = new MutableLiveData<>();
    }

    public MutableLiveData<List<Item_Images_Models>> getMyList() {
        return myList;
    }

    public void GetAllImageByItemId(String itemModelId) {
        Item_Image.GetAllImageByItemId(itemModelId, new Item_Image.GetAllImageToTheItem() {
            @Override
            public void AllImage(List<Item_Images_Models> MyList) {
                myList.postValue(MyList);
            }
        });
    }
}
