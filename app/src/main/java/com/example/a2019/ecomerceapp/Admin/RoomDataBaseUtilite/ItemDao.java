package com.example.a2019.ecomerceapp.Admin.RoomDataBaseUtilite;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.a2019.ecomerceapp.Admin.Models.ItemModel;

import java.util.List;

@Dao
public interface ItemDao{
    @Insert
    public void AddItem(ItemModel itemModel);
    @Delete
    public  void DeleteItem(ItemModel itemModel);
    @Update
    public void  UpdataItem(ItemModel itemModel);
    @Query("Select * from ItemModel Where id =:id")
    public ItemModel GetItemById(String id);
    @Query("Select * from ItemModel")
    public List<ItemModel> GetAllITem();
    @Query("Delete  from  ItemModel where CategoryName =:categoryName ")
    public  void DeleteAllItemByCategoryName(String categoryName);
}
