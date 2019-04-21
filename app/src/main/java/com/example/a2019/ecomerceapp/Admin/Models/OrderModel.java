package com.example.a2019.ecomerceapp.Admin.Models;

public class OrderModel extends UserModel {
    private String id;
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

    public OrderModel (UserModel userModel , String id , String totalPrice,String Data)
    {
        this.setDate(Data);
        this.setId(id);
        this.setTotalPrice(totalPrice);
        this.setAdrees(userModel.getAdrees());
        this.setName(userModel.getName());
        this.setPhone(userModel.getPhone());
        this.setUid(userModel.getUid());
    }
}
