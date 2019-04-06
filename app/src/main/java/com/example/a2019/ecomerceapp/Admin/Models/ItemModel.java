package com.example.a2019.ecomerceapp.Admin.Models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.net.Uri;

@Entity
public class ItemModel {
    String name;
    String Description;
    Uri ImageUri;
    String Id;
    String Price;
    String CategoryName;

    public ItemModel() {
    }
@Ignore
    public ItemModel(String name, String description, Uri imageUri, String id, String price, String categoryName) {
        this.name = name;
        Description = description;
        ImageUri = imageUri;
        Id = id;
        Price = price;
        CategoryName = categoryName;
    }
    @Ignore
    public ItemModel(String name, String description, Uri imageUri, String price, String categoryName) {
        this.name = name;
        Description = description;
        ImageUri = imageUri;
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

    public Uri getImageUri() {
        return ImageUri;
    }

    public void setImageUri(Uri imageUri) {
        ImageUri = imageUri;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
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
