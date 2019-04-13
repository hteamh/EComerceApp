package com.example.a2019.ecomerceapp.Admin.ViewModel;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
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

    // Set Data to Mutiable live data
    public void SetData() {
        if (internetIsConnected()) {
            GETDATA(new MyCall() {
                @Override
                public void Mycall(List<CategoryModel> categoryModels) {
                    if(categoryModels!=null)
                    {
                        MyCategoryItem.postValue(categoryModels);
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
        SetHideProgrees(false);
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
                SetHideProgrees(true);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                SetHideProgrees(true);
                 SetMessage(databaseError.getMessage());
            }
        });
        DeleteCategoryAndAllHisItemFromRoom deleteCategoryAndAllHisItemFromRoom
                = new DeleteCategoryAndAllHisItemFromRoom(categoryModel);
        deleteCategoryAndAllHisItemFromRoom.start();
    }
  public  class DeleteCategoryAndAllHisItemFromRoom extends Thread
  {
    CategoryModel categoryModel;

      private DeleteCategoryAndAllHisItemFromRoom(CategoryModel categoryModel) {
          this.categoryModel = categoryModel;
      }

      @Override
      public void run() {
          super.run();
          MyDatabase.getInstance().categoryDao().DeleteCategory(categoryModel);
          MyDatabase.getInstance().itemDao().DeleteAllItemByCategoryName(categoryModel.getName());

      }
  }

}
