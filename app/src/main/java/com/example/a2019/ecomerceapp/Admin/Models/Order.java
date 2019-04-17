package com.example.a2019.ecomerceapp.Admin.Models;

import android.arch.persistence.room.Ignore;

import java.util.List;

public class Order {
   private String id;
    private String Userid;
    private String Data;
    private  String TotalPrice;
    private  List<ItemModel> orderitemList;
    private  UserModel userModel;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return Userid;
    }

    public void setUserid(String userid) {
        Userid = userid;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    public String getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(String value) {
        TotalPrice = value;
    }

    public List<ItemModel> getitemList() {
        return orderitemList;
    }

    public void setOrderitemList(List<ItemModel> orderitemList) {
        this.orderitemList = orderitemList;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public Order() {
    }

    public Order(String id, String userid, String data, String totalPrice, List<ItemModel> orderitemList, UserModel userModel) {
        this.id = id;
        Userid = userid;
        Data = data;
        TotalPrice = totalPrice;
        this.orderitemList = orderitemList;
        this.userModel = userModel;
    }
}
