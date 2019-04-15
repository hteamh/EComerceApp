package com.example.a2019.ecomerceapp.Admin.RoomDataBaseUtilite;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import com.example.a2019.ecomerceapp.Admin.Models.CategoryModel;

import java.util.List;

import static android.database.sqlite.SQLiteDatabase.CONFLICT_REPLACE;

@Dao
public interface CategoryDao {
    @Insert(onConflict =CONFLICT_REPLACE)
  void AddCategory(CategoryModel categoryModel);
    @Delete
   void  DeleteCategory(CategoryModel categoryModel);
    @Query("Select * from categorymodel")
  List<CategoryModel> GetAllCategory();
    @Query("UPDATE CategoryModel set name =:name , ImageUri=:Uri , Description =:des Where id =:id")
     void Update(String name , String Uri,String id,String des);


}
