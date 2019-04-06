package com.example.a2019.ecomerceapp.Admin.RoomDataBaseUtilite;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import com.example.a2019.ecomerceapp.Admin.Models.CategoryModel;
import com.example.a2019.ecomerceapp.Admin.Models.ItemModel;
@Database(entities = {ItemModel.class, CategoryModel.class},version = 1,exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {
      private  static MyDatabase myDatabase;
      public  static void init(Context context)
      {
            if(myDatabase == null)
            {
             myDatabase = Room.databaseBuilder(context,MyDatabase.class,"MyDatabase")
                     .allowMainThreadQueries()
                     .build();
            }
      }
      public static MyDatabase getInstance()
      {
            return myDatabase;
      }
}
