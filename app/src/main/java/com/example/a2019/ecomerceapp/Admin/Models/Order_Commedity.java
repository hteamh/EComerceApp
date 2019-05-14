package com.example.a2019.ecomerceapp.Admin.Models;

public class Order_Commedity {
    private String commdityid;
    private String orderid;
    private String counter;
    private String item_name;
    private  String item_Image;
    private  String item_price;

    public String getItem_price() {
        return item_price;
    }

    public void setItem_price(String item_price) {
        this.item_price = item_price;
    }

    public String getItem_Image() {
        return item_Image;
    }

    public void setItem_Image(String item_Image) {
        this.item_Image = item_Image;
    }

    public String getCounter() {
        return counter;
    }

    public void setCounter(String counter) {
        this.counter = counter;
    }

    public String getCommdityid() {
        return commdityid;
    }

    public void setCommdityid(String commdityid) {
        this.commdityid = commdityid;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public Order_Commedity() {
    }

    public Order_Commedity(String commdityid, String orderid) {
        this.commdityid = commdityid;
        this.orderid = orderid;
        this.item_name ="";
        this.item_Image ="";
        item_price = "";
    }

    public Order_Commedity(String commdityid, String orderid, String counter) {
        this.commdityid = commdityid;
        this.orderid = orderid;
        this.counter = counter;
        this.item_name ="";
        this.item_Image ="";
        item_price = "";
    }
    public Order_Commedity(String commdityid, String orderid,
                           String counter,String item_Image , String item_name,String item_price) {
        this.commdityid = commdityid;
        this.orderid = orderid;
        this.counter = counter;
        this.item_name =item_name;
        this.item_Image =item_Image;
        this.item_price = item_price;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }
}
