package com.example.a2019.ecomerceapp.Admin.Models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "CategoryModel" )
public class CategoryModel {
    @ColumnInfo
    private String name;
    @ColumnInfo
   private   String  ImageUri;
    @NonNull
    @PrimaryKey
   private String  Id;
    @ColumnInfo
   private   String Description;

    public CategoryModel() {
    }
@Ignore
    public CategoryModel(String name, String imageUri, @NonNull String id, String description) {
        this.name = name;
        ImageUri = imageUri;
        Id = id;
        Description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUri() {
        return ImageUri;
    }

    public void setImageUri(String imageUri) {
        ImageUri = imageUri;
    }

    @NonNull
    public String getId() {
        return Id;
    }

    public void setId(@NonNull String id) {
        Id = id;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
