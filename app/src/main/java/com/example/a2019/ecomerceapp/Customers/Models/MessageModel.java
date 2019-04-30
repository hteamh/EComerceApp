package com.example.a2019.ecomerceapp.Customers.Models;

import com.example.a2019.ecomerceapp.Admin.Models.UserModel;

public class MessageModel {
    String roomid;
    String SenderId;
    String  text;
    String Latitude;
    String Longitude;
    String ImageUri;
    String Sender_name;
    String Data;

    public String getData() {
        return Data;
    }
    public MessageModel()
    {}

    public void setData(String data) {
        Data = data;
    }

    public String getRoomid() {
        return roomid;
    }

    public void setRoomid(String roomid) {
        this.roomid = roomid;
    }

    public String getSenderId() {
        return SenderId;
    }

    public void setSenderId(String senderId) {
        SenderId = senderId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getImageUri() {
        return ImageUri;
    }

    public void setImageUri(String imageUri) {
        ImageUri = imageUri;
    }

    public String getSender_name() {
        return Sender_name;
    }

    public void setSender_name(String sender_name) {
        Sender_name = sender_name;
    }

    public MessageModel(String roomid, String senderId, String text, String sender_name , String Data) {
        this.roomid = roomid;
        SenderId = senderId;
        this.text = text;
        Sender_name = sender_name;
        this.Data=Data;
        this.Latitude = null;
        this.Longitude=null;
        this.ImageUri=null;
    }

    public MessageModel(String roomid, String senderId, String latitude, String longitude, String sender_name,String Data) {
        this.roomid = roomid;
        SenderId = senderId;
        Latitude = latitude;
        Longitude = longitude;
        Sender_name = sender_name;
        this.text=null;
        this.ImageUri=null;
        this.Data=Data;
    }

    public MessageModel(String imageUri, String roomid, UserModel userModel,String Data) {
        ImageUri = imageUri;
        this.Sender_name = userModel.getName();
        this.SenderId = userModel.getUid();
        this.Longitude=null;
        this.Latitude=null;
        this.Data=Data;
        this.text=null;
        this.roomid=roomid;
    }
}
