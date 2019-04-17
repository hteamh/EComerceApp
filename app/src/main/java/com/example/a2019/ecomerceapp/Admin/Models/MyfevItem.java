package com.example.a2019.ecomerceapp.Admin.Models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


    @Entity
    public class MyfevItem
    {
        @ColumnInfo
        private String name;
        @ColumnInfo
        private String Description;
        @ColumnInfo
        private String ImageUri;
        @ColumnInfo
        @NonNull
        @PrimaryKey
        private String Id;
        @ColumnInfo
        private String Price;
        @ColumnInfo
        private String CategoryName;
        @ColumnInfo
        private String Count;
        @ColumnInfo
        private String BuyingPrice;
        @ColumnInfo
        private String Item_profit;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return Description;
        }

        public void setDescription(String description) {
            Description = description;
        }

        public String getImageUri() {
            return ImageUri;
        }

        public void setImageUri(String imageUri) {
            ImageUri = imageUri;
        }

        @NonNull
        public String getId() {
            return Id;
        }

        public void setId(@NonNull String id) {
            Id = id;
        }

        public String getPrice() {
            return Price;
        }

        public void setPrice(String price) {
            Price = price;
        }

        public String getCategoryName() {
            return CategoryName;
        }

        public void setCategoryName(String categoryName) {
            CategoryName = categoryName;
        }

        public String getCount() {
            return Count;
        }

        public void setCount(String count) {
            Count = count;
        }

        public String getBuyingPrice() {
            return BuyingPrice;
        }

        public void setBuyingPrice(String buyingPrice) {
            BuyingPrice = buyingPrice;
        }

        public String getItem_profit() {
            return Item_profit;
        }

        public void setItem_profit(String item_profit) {
            Item_profit = item_profit;
        }

        public MyfevItem() {
        }
        @Ignore

        public MyfevItem(String name, String description, String imageUri, @NonNull String id, String price, String categoryName, String count, String buyingPrice) {
            this.name = name;
            Description = description;
            ImageUri = imageUri;
            Id = id;
            Price = price;
            CategoryName = categoryName;
            Count = count;
            BuyingPrice = buyingPrice;
            this.Item_profit=Integer.toString(Integer.parseInt(Price)-Integer.parseInt(BuyingPrice));
        }
}
