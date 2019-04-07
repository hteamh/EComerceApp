package com.example.a2019.ecomerceapp.Admin.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.a2019.ecomerceapp.Admin.Models.CategoryModel;
import com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase.Categorybranches;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragmentVm extends AndroidViewModel {
    MutableLiveData<List<CategoryModel>> MyCategoryItem;
    public  List<CategoryModel> categoryModels ;
    public CategoryFragmentVm(@NonNull Application application) {
        super(application);
        categoryModels = new ArrayList<>();
        MyCategoryItem = new MutableLiveData<>();
        categoryModels = Categorybranches.GetAllCategoryInDB();
        if(categoryModels!=null)
        {
            MyCategoryItem.postValue(categoryModels);
        }
    }

    public MutableLiveData<List<CategoryModel>> getMyCategoryItem() {
        return MyCategoryItem;
    }

}
