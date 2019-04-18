package com.example.a2019.ecomerceapp.Admin.Models;

public class Order_Commedity {
    private String commdityid;
    private String orderid;
    private String counter;

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
    }

    public Order_Commedity(String commdityid, String orderid, String counter) {
        this.commdityid = commdityid;
        this.orderid = orderid;
        this.counter = counter;
    }
}
