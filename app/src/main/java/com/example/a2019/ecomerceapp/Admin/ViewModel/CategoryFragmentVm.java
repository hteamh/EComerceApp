package com.example.a2019.ecomerceapp.Admin.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.a2019.ecomerceapp.Admin.Models.CategoryModel;
import com.example.a2019.ecomerceapp.Admin.RoomDataBaseUtilite.MyDatabase;
import com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase.Categorybranches;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragmentVm extends AndroidViewModel {
    MutableLiveData<List<CategoryModel>> MyCategoryItem;
    MutableLiveData<String> Error;
    public List<CategoryModel> categoryModels;

    public CategoryFragmentVm(@NonNull Application application) {
        super(application);
        MyCategoryItem = new MutableLiveData<>();
        categoryModels = new ArrayList<>();
        Error = new MutableLiveData<>();
    }

    public MutableLiveData<String> getError() {
        return Error;
    }

    // GetData From FireBase
    public void GETDATA(final MyCall myCall) {
        Categorybranches.GetAllCategoryInDB(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    categoryModels.add(dataSnapshot1.getValue(CategoryModel.class));
                }
                myCall.Mycall(categoryModels);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Error.postValue("onCancelled Enter");
            }
        });
    }

    // Set Data to Mutiable live data
    public void SetData() {
        if (internetIsConnected()) {
            GETDATA(new MyCall() {
                @Override
                public void Mycall(List<CategoryModel> categoryModels) {
                    MyCategoryItem.postValue(categoryModels);
                }
            });
        } else {
            MyGetTHreead myGetTHreead = new MyGetTHreead(new MyCall() {
                @Override
                public void Mycall(List<CategoryModel> categoryModels) {
                    MyCategoryItem.postValue(categoryModels);
                }
            });
            myGetTHreead.start();
        }


    }

    // live data Getter
    public MutableLiveData<List<CategoryModel>> getMyCategoryItem() {
        return MyCategoryItem;
    }

    // interface for Delling with the Other Thread
    public interface MyCall {
        void Mycall(List<CategoryModel> categoryModels);
    }

    // thread for dealing whith
    public class MyGetTHreead extends Thread {
        MyCall myCall;

        public MyGetTHreead(MyCall myCall) {
            this.myCall = myCall;
        }

        @Override
        public void run() {
            super.run();
            List<CategoryModel> categoryModels = MyDatabase.getInstance().categoryDao().GetAllCategory();
            myCall.Mycall(categoryModels);
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
