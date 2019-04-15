package com.example.a2019.ecomerceapp.Admin.RoomDataBaseUtilite;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.example.a2019.ecomerceapp.Admin.Models.ItemModel;

import java.util.List;

import static android.database.sqlite.SQLiteDatabase.CONFLICT_REPLACE;

@Dao
public interface ItemDao{
    @Insert(onConflict =CONFLICT_REPLACE)
     void AddItem(ItemModel itemModel);
    @Delete
      void DeleteItem(ItemModel itemModel);
    @Update
     void  UpdataItem(ItemModel itemModel);
    @Query("Select * from ItemModel Where id =:id")
     ItemModel GetItemById(String id);
    @Query("Select * from ItemModel where CategoryName =:name ")
     List<ItemModel> GetAllITem( String name);
    @Query("Delete  from  ItemModel where CategoryName =:categoryName ")
      void DeleteAllItemByCategoryName(String categoryName);

}
