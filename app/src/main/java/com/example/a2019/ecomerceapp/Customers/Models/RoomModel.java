package com.example.a2019.ecomerceapp.Customers.Models;

public class RoomModel {

   private String name;
   private String desc;
   private String CreatedAt;

    public RoomModel(String name, String desc, String createdAt) {
        this.name = name;
        this.desc = desc;
        CreatedAt = createdAt;
    }

    public RoomModel() {
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
