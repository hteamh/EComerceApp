package com.example.a2019.ecomerceapp.Customers.Activities;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.a2019.ecomerceapp.Base.BaseActivity;
import com.example.a2019.ecomerceapp.Customers.Models.RoomModel;
import com.example.a2019.ecomerceapp.Customers.ViewModel.RoomChatVM;
import com.example.a2019.ecomerceapp.R;

public class AddRoom extends BaseActivity implements View.OnClickListener {

    protected EditText uRoomName;
    protected EditText uRoomDesc;
    protected Button creat;
    RoomChatVM roomChatVM;
    public static RoomModel roomModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_add_room);
        initView();
        roomChatVM = ViewModelProviders.of(this).get(RoomChatVM.class);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.creat) {
            String name=uRoomName.getText().toString().trim();
            String desc=uRoomDesc.getText().toString().trim();
             roomModel= new RoomModel(name,desc);
            roomChatVM.InsertNewRoom(roomModel,RegisterByNameAndPhone.MyUserModel.getUid());

        }
    }

    private void initView() {
        uRoomName = (EditText) findViewById(R.id.uRoomName);
        uRoomDesc = (EditText) findViewById(R.id.uRoomDesc);
        creat = (Button) findViewById(R.id.creat);
        creat.setOnClickListener(AddRoom.this);
    }
}
