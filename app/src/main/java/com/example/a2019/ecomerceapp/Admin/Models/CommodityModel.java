package com.example.a2019.ecomerceapp.Admin.Models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "CommodityModel")
public class CommodityModel {
    @ColumnInfo
    String name;
    @ColumnInfo
    String  ImageUri;
    @NonNull
    @PrimaryKey
    String  Id;
    @ColumnInfo
    String Description;

    public CommodityModel() {
    }

    public CommodityModel(String name, String imageUri, @NonNull String id, String description) {
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
