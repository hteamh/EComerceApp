package com.example.a2019.ecomerceapp.Admin.ViewModel;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import com.example.a2019.ecomerceapp.Admin.Fragments.Categories;
import com.example.a2019.ecomerceapp.Admin.Models.CategoryModel;
import com.example.a2019.ecomerceapp.Admin.Models.ItemModel;
import com.example.a2019.ecomerceapp.Admin.RoomDataBaseUtilite.MyDatabase;
import com.example.a2019.ecomerceapp.Base.BaseViewModel;
import com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase.ItemBranches;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CommoditiesVm extends BaseViewModel {
  private   List<ItemModel> list;
  private   MutableLiveData<List<ItemModel>> listMutableLiveData;


    public MutableLiveData<List<ItemModel>> getListMutableLiveData() {
        return listMutableLiveData;
    }

    public CommoditiesVm(@NonNull Application application) {
        super(application);
        listMutableLiveData=new MutableLiveData<>();
    }

    private void getData(final call call){
        list=new ArrayList<>();
        if (internetIsConnected()){
       Query query =ItemBranches.GetAllItemByCategoryName(Categories.categoryModeWeWantToSHowHisItem.getName());
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    list.clear();
                    for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                        list.add(snapshot.getValue(ItemModel.class));


                    }
                    call.mycall(list);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    SetMessage(databaseError.getMessage());

                }
            });

        }else {
            // get data from room
            getDataFromRoomDB th=new getDataFromRoomDB(list);
            th.start();
        }
    }
    // core fun
    public void setData(){
        SetHideProgrees(false);

        getData(new call() {
            @Override
            public void mycall(List<ItemModel> list) {
                listMutableLiveData.postValue(list);
              SetHideProgrees(true);
                DeleaingTHreead deleaingTHreead = new DeleaingTHreead(list);
                deleaingTHreead.start();

            }
        });

    }

    public class getDataFromRoomDB extends Thread{
        List<ItemModel> list;

        private getDataFromRoomDB(List<ItemModel> list){
            this.list=list;
        }

        @Override
        public void run() {
            super.run();
          list=  MyDatabase.getInstance().itemDao().GetAllITem();
        }
    }

   public  void Delete(ItemModel itemModel)
   {
       ItemBranches.DeleteItemByItemId(itemModel.getId());

   }
    public interface call {
        void mycall(List<ItemModel> list);
    }
    private class DeleaingTHreead extends Thread
    {
        List<ItemModel> MyItem;

        public DeleaingTHreead(List<ItemModel> myitem) {
            MyItem = myitem;
        }

        @Override
        public void run() {
            super.run();
            List <ItemModel> OldList = MyDatabase.getInstance().itemDao().GetAllITem();
            for(int k=0;k<OldList.size();k++)
            {
                MyDatabase.getInstance().itemDao().DeleteItem(OldList.get(k));
            }

            if(MyItem.size()<20)
            {
                for(int i=0;i<MyItem.size();i++)
                {
                    MyDatabase.getInstance().itemDao().AddItem(MyItem.get(i));
                }
            }
            else
            {
                for(int J=0;J<20;J++)
                {
                    MyDatabase.getInstance().itemDao().AddItem(MyItem.get(J));
                }
            }


        }
    }


}
