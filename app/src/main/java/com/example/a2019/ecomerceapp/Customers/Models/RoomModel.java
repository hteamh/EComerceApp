package com.example.a2019.ecomerceapp.Customers.Models;

import com.example.a2019.ecomerceapp.Admin.Models.UserModel;

public class RoomModel {

   private String name;
   private String desc;

    public RoomModel(String name, String desc) {
        this.name = name;
        this.desc = desc;
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


}
