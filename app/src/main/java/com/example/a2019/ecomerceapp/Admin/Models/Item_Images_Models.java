package com.example.a2019.ecomerceapp.Admin.Models;

public class Item_Images_Models {
    String item_id;
    String id;
    String Url;

    public Item_Images_Models() {
    }

    public Item_Images_Models(String itemId, String id, String url) {
        this.item_id = itemId;
        this.id = id;
        Url = url;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}