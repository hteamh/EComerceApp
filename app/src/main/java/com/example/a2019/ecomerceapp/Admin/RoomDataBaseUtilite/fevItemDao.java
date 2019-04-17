package com.example.a2019.ecomerceapp.Admin.RoomDataBaseUtilite;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.example.a2019.ecomerceapp.Admin.Models.MyfevItem;

import java.util.List;

import static android.database.sqlite.SQLiteDatabase.CONFLICT_REPLACE;

@Dao
public interface fevItemDao {
    @Insert(onConflict = CONFLICT_REPLACE)
    void AddItem(MyfevItem itemModel);

    @Delete
    void DeleteItem(MyfevItem itemModel);

    @Update
    void UpdataItem(MyfevItem itemModel);

    @Query("Select * from myfevItem Where id =:id")
    MyfevItem GetItemById(String id);
    @Query("Select * from myfevitem")
         List<MyfevItem> GetAllMyfevItem();
}
