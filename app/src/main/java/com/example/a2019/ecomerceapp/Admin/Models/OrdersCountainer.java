package com.example.a2019.ecomerceapp.Admin.Models;

import java.util.List;

public class OrdersCountainer extends OrderModel {
    List<ItemModel> itemModels;

    public List<ItemModel> getItemModels() {
        return itemModels;
    }

    public void setItemModels(List<ItemModel> itemModels) {
        this.itemModels = itemModels;
    }
    public OrdersCountainer()
    {
    }
    public OrdersCountainer(OrderModel orderModel,List<ItemModel> itemModelsA)
    {
        this.setUid(orderModel.getUid());
        this.setPhone(orderModel.getPhone());
        this.setName(orderModel.getName());
        this.setAdrees(orderModel.getAdrees());
        this.setTotalPrice(orderModel.getTotalPrice());
        this.setId(orderModel.getId());
        this.setItemModels(itemModelsA);
        this.setDate(orderModel.getDate());
    }
}
