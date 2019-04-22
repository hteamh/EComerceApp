package com.example.a2019.ecomerceapp.Customers.Models;

public class RoomModel {

   private String name;
   private String desc;
   private String CreatedAt;
   private String userId;

    public RoomModel(String name, String desc, String createdAt, String userId) {
        this.name = name;
        this.desc = desc;
        CreatedAt = createdAt;
        this.userId = userId;
    }

    public RoomModel() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        CreatedAt = createdAt;
    }
}
