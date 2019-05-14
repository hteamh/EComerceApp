package com.example.a2019.ecomerceapp.Admin.Models;

import android.support.annotation.NonNull;

public class SalesItem extends Order_Commedity {
    String id;
    String salesid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSalesid() {
        return salesid;
    }

    public void setSalesid(String salesid) {
        this.salesid = salesid;
    }

    public SalesItem() {
    }


    public SalesItem(Order_Commedity order_commedity, String id, String salesid) {
        super(order_commedity.getCommdityid(), order_commedity.getOrderid(), order_commedity.getCounter()
        ,order_commedity.getItem_Image(),order_commedity.getItem_name(),order_commedity.getItem_price());
        this.id = id;
        this.salesid = salesid;
    }
}
