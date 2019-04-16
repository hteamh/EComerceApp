package com.example.a2019.ecomerceapp.Admin.Models;

import android.arch.persistence.room.Ignore;

public class OrderItem {
    String OrderItemId;
    String ItemId;
    String UserId;
    String Day;
    String Month;
    String Year;
@Ignore
    public OrderItem(String orderItemId, String itemId, String userId, String day, String month, String year) {
        OrderItemId = orderItemId;
        ItemId = itemId;
        UserId = userId;
        Day = day;
        Month = month;
        Year = year;
    }

    public OrderItem() {
    }

    public String getOrderItemId() {
        return OrderItemId;
    }

    public void setOrderItemId(String orderItemId) {
        OrderItemId = orderItemId;
    }

    public String getItemId() {
        return ItemId;
    }

    public void setItemId(String itemId) {
        ItemId = itemId;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getDay() {
        return Day;
    }

    public void setDay(String day) {
        Day = day;
    }

    public String getMonth() {
        return Month;
    }

    public void setMonth(String month) {
        Month = month;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

}
