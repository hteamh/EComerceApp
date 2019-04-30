package com.example.a2019.ecomerceapp.Customers.Activities;

import android.Manifest;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.a2019.ecomerceapp.Base.BaseActivity;
import com.example.a2019.ecomerceapp.Customers.Adapters.ChatAdapter;
import com.example.a2019.ecomerceapp.Customers.Models.MessageModel;
import com.example.a2019.ecomerceapp.Customers.ViewModel.MessageVm;
import com.example.a2019.ecomerceapp.FireBaseUtilite.DataBase.MessageBranch;
import com.example.a2019.ecomerceapp.MapLocation.LocationDao;
import com.example.a2019.ecomerceapp.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.Marker;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.a2019.ecomerceapp.Admin.Activiteis.AddCategory.PICK_IMAGE_REQUEST;

public class ChatActivity extends BaseActivity {
     public  static int MY_PERMISSIONS_REQUEST_Loc = 900;
      public static GoogleMap MyGooGleMap;
      public  static Marker marker;
      RecyclerView Chat_RecyclerView;
      EditText TextInTheMessage;
      ImageView Send_Button, Location,Choose_Image;
    LocationDao MyLocation;
    Location CurrentLocatin;
    MessageVm messageVm;
    ChatAdapter adapter;
    Query  query;
    RecyclerView.LayoutManager layoutManager;
    public void inti() {
        messageVm = ViewModelProviders.of(this).get(MessageVm.class);
        Chat_RecyclerView = findViewById(R.id.ChatReCyclerVIew);
        layoutManager = new LinearLayoutManager(this);
        ((LinearLayoutManager) layoutManager).setStackFromEnd(true);
        adapter=new ChatAdapter(null);
        Chat_RecyclerView.setLayoutManager(layoutManager);
        Chat_RecyclerView.setAdapter(adapter);
        TextInTheMessage =findViewById(R.id.message_text);
        Send_Button =findViewById(R.id.message_send);
        Location = findViewById(R.id.send_Loc);
        MyLocation =new LocationDao(this,MyLocationListener);
        Choose_Image = findViewById(R.id.Imge_ch);

    }
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        inti();
        Listener();
        InitDataToAdapter();
    }

    @Override public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
         @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode ==MY_PERMISSIONS_REQUEST_Loc)
        {
      CurrentLocatin= MyLocation.GetCurrentLocation();
      messageVm.SetLocationMessage(CurrentLocatin);
        }
    }
    public void Listener() {
        Location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
     {
       if( IsPermissionAllowed())
               {
        CurrentLocatin= MyLocation.GetCurrentLocation();
        if(CurrentLocatin!=null)
        {
            messageVm.SetLocationMessage(CurrentLocatin);
        }
        else
        {
        }
               }
         else
             {
             Request_Location_Permission();

             }
     }
        });
        Send_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextInTheMessage.getText().toString().trim().length()>1)
                {
                    String s= TextInTheMessage.getText().toString().trim();
                    messageVm.SetTextMessage(s) ;
                    TextInTheMessage.setText(null);
                }
                else
                {
                    Toast.makeText(activity, "No text To Send", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Choose_Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenImageGalary();
            }
        });

    }
    public void Request_Location_Permission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(ChatActivity.this,
                Manifest.permission.READ_CONTACTS)) {
            // Show an explanation to the user
            showConfirmationMessage(R.string.Message, R.string.I_Need_This_Permisstion,
                    R.string.Yes, new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                    ActivityCompat.requestPermissions(ChatActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            MY_PERMISSIONS_REQUEST_Loc);
                }
            });

        } else {
            // request the permission
            ActivityCompat.requestPermissions(ChatActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_Loc);
        }
    }
    public boolean IsPermissionAllowed() {
        if (ContextCompat.checkSelfPermission(ChatActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        else return true;
    }

    LocationListener MyLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(android.location.Location location) {
            CurrentLocatin=location;
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {
            MyLocation.GetCurrentLocation();

        }

        @Override
        public void onProviderDisabled(String provider) {
            Toast.makeText(activity, provider+"Is Disabled", Toast.LENGTH_SHORT).show();
        }
    };
    public void  OpenImageGalary() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            messageVm.SetImageMessage( data.getData());
            Toast.makeText(activity, "Waiting for uploading your image", Toast.LENGTH_SHORT).show();

        }
    }
   public void InitDataToAdapter()
   {
   query = MessageBranch.GetAllMessageByRoomId(Home.roomModel.getUid());
   adapter.ChangeData(null);
  query.addChildEventListener(childEventListener);

   }
   ChildEventListener childEventListener = new ChildEventListener() {
       @Override
       public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
           adapter.AddOneMessage(dataSnapshot.getValue(MessageModel.class));

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
    protected void onDestroy() {
        super.onDestroy();
        query.removeEventListener(childEventListener);
    }

}
