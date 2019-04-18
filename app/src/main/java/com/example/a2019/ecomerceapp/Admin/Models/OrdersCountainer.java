package com.example.a2019.ecomerceapp.Admin.Models;

import java.util.List;

public class OrdersCountainer {
    private String Orderid;
    private String UserName;
    private String UserPhone;
    private String UserAdrees;
    private String TotalPrice;
    private String Date;
    List<ItemModel> itemModels;


    public String getOrderid() {
        return Orderid;
    }

    public void setOrderid(String orderid) {
        Orderid = orderid;
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

    public List<ItemModel> getItemModels() {
        return itemModels;
    }

    public void setItemModels(List<ItemModel> itemModels) {
        this.itemModels = itemModels;
    }

    public OrdersCountainer(String orderid, String userName, String userPhone, String userAdrees, String totalPrice, String date, List<ItemModel> itemModels) {
        Orderid = orderid;
        UserName = userName;
        UserPhone = userPhone;
        UserAdrees = userAdrees;
        TotalPrice = totalPrice;
        Date = date;
        this.itemModels = itemModels;
    }
}
