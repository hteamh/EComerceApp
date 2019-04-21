package com.example.a2019.ecomerceapp.Admin.Models;

import java.util.List;

public class SalesCountainer extends SalesModel {
    List<SalesItem> salesItems;

    public SalesCountainer(String id, List<SalesItem> salesItems) {
        super(id);
        this.salesItems = salesItems;
    }

    public SalesCountainer(SalesModel salesModel, List<SalesItem> salesItems) {
       this.setAdrees(salesModel.getAdrees());
       this.setId(salesModel.getId());
       this.setName(salesModel.getName());
       this.setPhone(salesModel.getPhone());
       this.setUid(salesModel.getUid());
    }


    public SalesCountainer()
    {

    }

    public List<SalesItem> getSalesItems() {
        return salesItems;
    }

    public void setSalesItems(List<SalesItem> salesItems) {
        this.salesItems = salesItems;
    }
}
