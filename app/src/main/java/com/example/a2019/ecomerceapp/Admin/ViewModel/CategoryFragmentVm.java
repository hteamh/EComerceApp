package com.example.a2019.ecomerceapp.Admin.ViewModel;

import android.app.Application;
import android.app.ListActivity;
import android.arch.lifecycle.MutableLiveData;
import android.os.Handler;
import android.support.annotation.NonNull;
import com.example.a2019.ecomerceapp.Admin.Models.CategoryModel;
import com.example.a2019.ecomerceapp.Admin.Models.ItemModel;
import com.example.a2019.ecomerceapp.Admin.RoomDataBaseUtilite.MyDatabase;
import com.example.a2019.ecomerceapp.Base.BaseViewModel;
import com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase.Categorybranches;
import com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase.ItemBranches;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class CategoryFragmentVm extends BaseViewModel {
  private   MutableLiveData<List<CategoryModel>> MyCategoryItem;
    private List<CategoryModel> categoryModels;
    public CategoryFragmentVm(@NonNull Application application) {
        super(application);
        MyCategoryItem = new MutableLiveData<>();
        categoryModels = new ArrayList<>();
    }


    // GetData From FireBase
    private void GETDATA(final MyCall myCall) {
        Categorybranches.GetAllCategoryInDB(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                categoryModels.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                        categoryModels.add(dataSnapshot1.getValue(CategoryModel.class));

                }
                myCall.Mycall(categoryModels);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
               SetMessage("onCancelled Enter");
            }
        });
    }

    public void SetData() {
        if (internetIsConnected()) {
            GETDATA(new MyCall() {
                @Override
                public void Mycall(List<CategoryModel> categoryModels) {
                    if(categoryModels!=null)
                    {
                        MyCategoryItem.postValue(categoryModels);
                        DeleaingTHreead deleaingTHreead = new DeleaingTHreead(categoryModels);
                        deleaingTHreead.start();
                    }
                    else
                    {
                        SetMessage("No Category to Get it ");

                    }

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

    public MutableLiveData<List<CategoryModel>> getMyCategoryItem() {
        return MyCategoryItem;
    }

    public interface MyCall {
        void Mycall(List<CategoryModel> categoryModels);
    }

    public class MyGetTHreead extends Thread {
        MyCall myCall;

        private MyGetTHreead(MyCall myCall) {
            this.myCall = myCall;
        }

        @Override
        public void run() {
            super.run();
            List<CategoryModel> categoryModels = MyDatabase.getInstance().categoryDao().GetAllCategory();
            myCall.Mycall(categoryModels);


        }
    }
    public void Delete(final CategoryModel categoryModel)
    {
        Categorybranches.DeleteCategoryByid(categoryModel.getId());
        Query query= ItemBranches.GetAllItemByCategoryName(categoryModel.getName());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot mydata:dataSnapshot.getChildren())
                {
                    ItemModel MyItem = mydata.getValue(ItemModel.class);
                    ItemBranches.DeleteItemByItemId(MyItem.getId());
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                 SetMessage(databaseError.getMessage());
            }
        });

    }


    private class DeleaingTHreead extends Thread
    {
        List<CategoryModel> Mycategory;

        public DeleaingTHreead(List<CategoryModel> mycategory) {
            Mycategory = mycategory;
        }

        @Override
        public void run() {
            super.run();
            List <CategoryModel> OldList = MyDatabase.getInstance().categoryDao().GetAllCategory();
            for(int k=0;k<OldList.size();k++)
            {
                MyDatabase.getInstance().categoryDao().DeleteCategory(OldList.get(k));
            }


            for(int i=0;i<Mycategory.size();i++)
            {
                CategoryModel  categoryModel= Mycategory.get(i);
                MyDatabase.getInstance().categoryDao().AddCategory(categoryModel);
            }



        }
    }
}
