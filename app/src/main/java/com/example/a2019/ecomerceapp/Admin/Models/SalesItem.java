package com.example.a2019.ecomerceapp.Admin.Models;

import android.support.annotation.NonNull;

public class SalesItem extends ItemModel {
    String salesid;
    public SalesItem (ItemModel itemModel , String salesid)
    {
        this.salesid=salesid;
        this.setCount(itemModel.getCount());
        this.setBuyingPrice(itemModel.getBuyingPrice());
        this.setCategoryName(itemModel.getCategoryName());
        this.setDescription(itemModel.getDescription());
        this.setId(itemModel.getId());
        this.setImageUri(itemModel.getImageUri());
        this.setPrice(itemModel.getPrice());
        this.setItem_profit("null");
    }
}
