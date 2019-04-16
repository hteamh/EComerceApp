package com.example.a2019.ecomerceapp.Admin.RoomDataBaseUtilite;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.a2019.ecomerceapp.Admin.Models.UserModel;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
     void AddUser(UserModel userModel);
    @Delete
    void  DeleteUser(UserModel userModel);
    @Query("Select * From UserModel")
    UserModel GetAllUser();

}
