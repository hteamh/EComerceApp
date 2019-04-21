package com.example.a2019.ecomerceapp.Admin.Models;

import java.util.List;

public class SalesModel extends UserModel {
    String id;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SalesModel(String id) {
        this.id = id;
    }

    public SalesModel(UserModel userModel, String id) {
       this.setUid(userModel.getUid());
       this.setId(id);
       this.setAdrees(userModel.getAdrees());
       this.setName(userModel.getName());
       this.setPhone(userModel.getPhone());
    }

    public SalesModel()
    {

    }

}
