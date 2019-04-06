package com.example.a2019.ecomerceapp.Admin.Models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.net.Uri;

@Entity
public class CategoryModel {
     String name;
     Uri  ImageUri;
     String  Id;
     String Description;
    @Ignore
    public CategoryModel(String name, Uri Uri) {
        this.name = name;
        ImageUri = Uri;
    }

    @Ignore
    public CategoryModel(String name, Uri imageUri, String id, String description) {
        this.name = name;
        ImageUri = imageUri;
        Id = id;
        Description = description;
    }
    @Ignore
    public CategoryModel(String name, Uri uri, String id) {
        this.name = name;
        ImageUri = uri;
        Id = id;
    }

    public CategoryModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Uri getUri() {
        return ImageUri;
    }

    public void setUri(Uri uri) {
        ImageUri = uri;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
