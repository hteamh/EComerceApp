package com.example.a2019.ecomerceapp.Admin.RoomDataBaseUtilite;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.a2019.ecomerceapp.Admin.Models.CategoryModel;

import java.util.List;

@Dao
public interface CategoryDao {
    @Insert
 public void AddCategory(CategoryModel categoryModel);
    @Delete
 public  void  DeleteCategory(CategoryModel categoryModel);
    @Query("Select * from categorymodel")
 public List<CategoryModel> GetAllCategory();


}
