package com.example.a2019.ecomerceapp.Admin.Models;

public class CategoryModel {
     String name;
     String  ImageUri;
     String  Id;
     String Description;

    public CategoryModel(String name, String Uri) {
        this.name = name;
        ImageUri = Uri;
    }


    public CategoryModel(String name, String imageUri, String id, String description) {
        this.name = name;
        ImageUri = imageUri;
        Id = id;
        Description = description;
    }

    public CategoryModel(String name, String uri, String id) {
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

    public String getUri() {
        return ImageUri;
    }

    public void setUri(String uri) {
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
