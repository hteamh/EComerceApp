package com.example.a2019.ecomerceapp.Admin.Models;

import android.arch.persistence.room.Ignore;

public class OrderModel {
    private String id;
    private String UserName;
    private String UserPhone;
    private String UserAdrees;
    private String TotalPrice;
    private String Date;

    public OrderModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserPhone() {
        return UserPhone;
    }

    public void setUserPhone(String userPhone) {
        UserPhone = userPhone;
    }

    public String getUserAdrees() {
        return UserAdrees;
    }

    public void setUserAdrees(String userAdrees) {
        UserAdrees = userAdrees;
    }

    public String getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        TotalPrice = totalPrice;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
    @Ignore

    public OrderModel(String id, String userName, String userPhone,
                      String userAdrees, String totalPrice, String date) {
        this.id = id;
        UserName = userName;
        UserPhone = userPhone;
        UserAdrees = userAdrees;
        TotalPrice = totalPrice;
        Date = date;
    }
}
