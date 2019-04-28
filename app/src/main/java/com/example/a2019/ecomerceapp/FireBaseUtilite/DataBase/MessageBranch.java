package com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase;

import com.example.a2019.ecomerceapp.Customers.Models.MessageModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class MessageBranch {
    private   static String MessageBrunche = "Message";
    private static DatabaseReference GetMessageBrunche()
    {
        return FirebaseDatabase.getInstance().getReference(MessageBrunche);
    }
    public static void AddMessage(MessageModel messageModel)
    {
      GetMessageBrunche().push().setValue(messageModel);
    }
    public static Query GetAllMessageByRoomId(String RoomID)
    {
      Query query = GetMessageBrunche().orderByChild("roomid").equalTo(RoomID);
      return query;
    }
}
