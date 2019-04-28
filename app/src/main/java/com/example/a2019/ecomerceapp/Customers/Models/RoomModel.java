package com.example.a2019.ecomerceapp.Customers.Models;

import android.arch.persistence.room.Ignore;
import android.support.annotation.NonNull;

import com.example.a2019.ecomerceapp.Admin.Models.UserModel;

public class RoomModel extends UserModel {
   String RoomId;
   String Roomname;
   String RoomDes;

    public String getRoomId() {
        return RoomId;
    }

    public void setRoomId(String roomId) {
        RoomId = roomId;
    }

    public String getRoomname() {
        return Roomname;
    }

    public void setRoomname(String roomname) {
        Roomname = roomname;
    }

    public String getRoomDes() {
        return RoomDes;
    }

    public void setRoomDes(String roomDes) {
        RoomDes = roomDes;
    }


@Ignore
    public RoomModel(UserModel userModel) {
        super(userModel.getUid(), userModel.getName(), userModel.getPhone(), userModel.getAdrees(),userModel.getEmail());
        this.RoomDes=userModel.getAdrees();
        this.Roomname=userModel.getName()+userModel.getPhone();
        this.RoomId =userModel.getUid();
    }

    public RoomModel() {
    }

    public RoomModel(UserModel userModel, String roomId) {
        super(userModel.getUid(), userModel.getName(), userModel.getPhone(), userModel.getAdrees());
        RoomId = roomId;
        this.RoomDes=userModel.getAdrees();
        this.Roomname=userModel.getName()+userModel.getPhone();
    }
}
