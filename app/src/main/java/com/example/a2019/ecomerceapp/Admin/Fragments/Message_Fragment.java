package com.example.a2019.ecomerceapp.Admin.Fragments;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a2019.ecomerceapp.Admin.Adapters.RoomsAdapter;
import com.example.a2019.ecomerceapp.Admin.Models.UserModel;
import com.example.a2019.ecomerceapp.Admin.RoomDataBaseUtilite.MyDatabase;
import com.example.a2019.ecomerceapp.Admin.ViewModel.MessageFragmentVm;
import com.example.a2019.ecomerceapp.Base.BaseFragment;
import com.example.a2019.ecomerceapp.Customers.Activities.ChatActivity;
import com.example.a2019.ecomerceapp.Customers.Activities.Home;
import com.example.a2019.ecomerceapp.Customers.Models.RoomModel;
import com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase.RoomBranch;
import com.example.a2019.ecomerceapp.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class Message_Fragment extends BaseFragment {
    View view ;
    static RoomsAdapter roomsAdapter;
   RecyclerView recyclerView;
    MessageFragmentVm messageFragmentVm;
   RecyclerView.LayoutManager layoutManager;
    public Message_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_message, container, false);
        Init();
        roomsAdapter.setOnItemClick(new RoomsAdapter.OnItemClick() {
            @Override
            public void OnItemClick(final RoomModel roomModel) {
                ThreadGetUser threadGetUser = new ThreadGetUser(new InUserGet() {
                    @Override
                    public void GetUser(UserModel userModel) {
                        Home.roomModel=roomModel;
                        Home.userModel = userModel;
                        startActivity(new Intent(getContext(), ChatActivity.class));
                    }
                });
                threadGetUser.start();
            }
        });
        messageFragmentVm.GetAllRooms(childEventListener);
        return view;
    }



    public void Init()
    {
        messageFragmentVm = ViewModelProviders.of(this).get(MessageFragmentVm.class);
        roomsAdapter = new RoomsAdapter(null);
        recyclerView = view.findViewById(R.id.RoomRecyclerView);
        layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setAdapter(roomsAdapter);
        recyclerView.setLayoutManager(layoutManager);
    }

   static ChildEventListener childEventListener = new ChildEventListener() {
       @Override
       public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
           roomsAdapter.AddRoom(dataSnapshot.getValue(RoomModel.class));
       }

       @Override
       public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

       }

       @Override
       public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

       }

       @Override
       public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

       }

       @Override
       public void onCancelled(@NonNull DatabaseError databaseError) {

       }
   };

    @Override
    public void onDestroy() {
        super.onDestroy();
        RoomBranch.getRoomRef().removeEventListener(childEventListener);
    }
    public class ThreadGetUser extends Thread{
        InUserGet inUserGet;

        private   ThreadGetUser(InUserGet inUserGet) {
            this.inUserGet = inUserGet;
        }

        @Override
        public void run() {
            super.run();
            final List<UserModel> AllUser;
            AllUser = MyDatabase.getInstance().userDao().GetAllUser();
            if (AllUser.size()>0)
            {
                inUserGet.GetUser(AllUser.get(0));
            }
            else
            {
                inUserGet.GetUser(null);
            }


        }
    }
    private interface InUserGet
    {
        void GetUser(UserModel userModel);
    }


}
