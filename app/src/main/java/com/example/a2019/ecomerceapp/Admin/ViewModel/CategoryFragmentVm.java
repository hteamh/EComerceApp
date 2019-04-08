package com.example.a2019.ecomerceapp.Admin.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.a2019.ecomerceapp.Admin.Models.CategoryModel;
import com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase.Categorybranches;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragmentVm extends AndroidViewModel {
    MutableLiveData<List<CategoryModel>> MyCategoryItem;
    public  List<CategoryModel> categoryModels ;
    public CategoryFragmentVm(@NonNull Application application) {
        super(application);
        MyCategoryItem = new MutableLiveData<>();
        categoryModels = new ArrayList<>();
    }

public void GETDATA(final MyCall myCall) {
    Categorybranches.GetAllCategoryInDB(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
            {
                categoryModels .add(dataSnapshot1.getValue(CategoryModel.class));
            }
            myCall.Mycall(categoryModels);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });
}

public  void SetData() {
   GETDATA(new MyCall() {
       @Override
       public void Mycall(List<CategoryModel> categoryModels) {
           MyCategoryItem.postValue(categoryModels);
       }
   });
}
    public MutableLiveData<List<CategoryModel>> getMyCategoryItem() {
        return MyCategoryItem;
    }



    public interface MyCall {
         void Mycall(List<CategoryModel> categoryModels);
    }

}
