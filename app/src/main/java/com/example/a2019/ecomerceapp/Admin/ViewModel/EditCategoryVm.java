package com.example.a2019.ecomerceapp.Admin.ViewModel;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.a2019.ecomerceapp.Admin.Fragments.Categories;
import com.example.a2019.ecomerceapp.Admin.Models.CategoryModel;
import com.example.a2019.ecomerceapp.Admin.RoomDataBaseUtilite.MyDatabase;
import com.example.a2019.ecomerceapp.Base.BaseViewModel;
import com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase.Categorybranches;
import com.example.a2019.ecomerceapp.FireBaseUtilite.Storge.CategoryImageBranches;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class EditCategoryVm extends BaseViewModel {
   private MutableLiveData <Boolean> Done;
    public EditCategoryVm(@NonNull Application application) {
        super(application);

        Done = new MutableLiveData<>();
    }



    public MutableLiveData<Boolean> getDone() {
        return Done;
    }



    public   void UpdateCategry(final CategoryModel categoryModel)
    {
        SetHideProgrees(false);
        if(internetIsConnected())
        {
            if(categoryModel.getImageUri() == Categories.categoryModeWeWantToUpdate.getImageUri())
            {
                final CategoryModel newCategoryModel = new CategoryModel(categoryModel.getName(),categoryModel.getImageUri(),
                        Categories.categoryModeWeWantToUpdate.getId(),categoryModel.getDescription());
                Categorybranches.EditCateory(newCategoryModel, new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
                       SetHideProgrees(true);
                        Done.postValue(true);
                    }
                }, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        SetMessage(e.getMessage());
                        SetHideProgrees(true);

                    }
                });
            }
            else
            {
                CategoryImageBranches.Edit(categoryModel, new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
                        CategoryImageBranches.GetUri(categoryModel, new CategoryImageBranches.GetUriListner() {
                            @Override
                            public void MyUri(String Uri) {
                                final CategoryModel newCategoryModel = new CategoryModel(categoryModel.getName(),Uri,
                                        Categories.categoryModeWeWantToUpdate.getId(),categoryModel.getDescription());
                                Categorybranches.EditCateory(newCategoryModel, new OnSuccessListener() {
                                    @Override
                                    public void onSuccess(Object o) {
                                        SetHideProgrees(true);
                                        Done.postValue(true);

                                    }
                                }, new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        SetHideProgrees(true);
                                        SetMessage(e.getMessage());

                                    }
                                });
                            }
                        });
                    }
                }, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        SetHideProgrees(true);
                        SetMessage(e.getMessage());
                    }
                });
            }

        }
        else
        {
           SetHideProgrees(true);
          SetMessage("No Internet Connection");
        }

    }


}
