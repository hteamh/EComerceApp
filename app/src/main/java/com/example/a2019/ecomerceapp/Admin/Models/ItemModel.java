package com.example.a2019.ecomerceapp.Admin.Models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.net.Uri;
import android.support.annotation.NonNull;

@Entity
public class ItemModel  {
    @ColumnInfo
    String name;
    @ColumnInfo
    String Description;
    @ColumnInfo
    String ImageUri;
    @ColumnInfo
    @NonNull
    @PrimaryKey
    String Id;
    @ColumnInfo
    String Price;
    @ColumnInfo
    String CategoryName;

    public ItemModel() {
    }
@Ignore
    public ItemModel(String name, String description, String imageUri, @NonNull String id, String price, String categoryName) {
        this.name = name;
        Description = description;
        ImageUri = imageUri;
        Id = id;
        Price = price;
        CategoryName = categoryName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
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

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }
}
