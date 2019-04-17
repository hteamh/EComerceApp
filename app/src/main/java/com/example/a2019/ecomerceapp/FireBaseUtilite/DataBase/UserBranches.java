package com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase;

import com.example.a2019.ecomerceapp.Admin.Models.UserModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserBranches  {
    private static String UserBranche = "User";
    private static FirebaseDatabase GetInstance()
    {
     return    FirebaseDatabase.getInstance();
    }
    public static DatabaseReference GetUserBranches()
    {
        return  GetInstance().getReference(UserBranche);
    }
    public static void AddUser(UserModel userModel, OnSuccessListener onSuccessListener,
                               OnFailureListener onFailureListener)
    {
        GetUserBranches().child(userModel.getUid()).setValue(userModel)
        .addOnFailureListener(onFailureListener)
        .addOnSuccessListener(onSuccessListener);
    }
    public static void EditUser(UserModel userModel,OnFailureListener onFailureListener,
                                OnSuccessListener onSuccessListener)
    {
        GetUserBranches().child(userModel.getUid()).removeValue();
        GetUserBranches().child(userModel.getUid()).setValue(userModel)
        .addOnSuccessListener(onSuccessListener)
        .addOnFailureListener(onFailureListener);

    }
    public static void DeleteUser(UserModel userModel)
    {
        GetUserBranches().child(userModel.getUid()).removeValue();

    }
}
