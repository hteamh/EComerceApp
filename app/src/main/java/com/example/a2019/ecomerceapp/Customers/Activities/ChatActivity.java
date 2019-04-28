package com.example.a2019.ecomerceapp.Customers.Activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.a2019.ecomerceapp.Base.BaseActivity;
import com.example.a2019.ecomerceapp.MapLocation.LocationDao;
import com.example.a2019.ecomerceapp.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;

public class ChatActivity extends BaseActivity {
     public  static int MY_PERMISSIONS_REQUEST_Loc = 900;
       public static MapView Out_Map;
       public static MapView InMa;
      public static GoogleMap MyGooGleMap;
      RecyclerView Chat_RecyclerView;
      EditText TextInTheMessage;
      ImageView Send_Button, Location;
    LocationDao MyLocation;
    Location CurrentLocatin;
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Out_Map.onCreate(savedInstanceState);
        InMa.onCreate(savedInstanceState);
        inti();
        Listener();
    }
    @Override protected void onDestroy() {
        super.onDestroy();
        Out_Map.onDestroy();
        InMa.onDestroy();
    }
    @Override protected void onResume() {
        super.onResume();
        Out_Map.onResume();
        InMa.onResume();
    }
    @Override protected void onStart() {
        super.onStart();
        Out_Map.onStart();
        InMa.onStart();
    }
    @Override protected void onStop() {
        super.onStop();
        Out_Map.onStop();
        InMa.onStop();
    }
    @Override protected void onPause() {
        super.onPause();
        Out_Map.onPause();
        InMa.onPause();
    }
    @Override public void onLowMemory() {
        super.onLowMemory();
        Out_Map.onLowMemory();
        InMa.onLowMemory();
    }
    public void inti() {
        Chat_RecyclerView = findViewById(R.id.ChatReCyclerVIew);
        TextInTheMessage =findViewById(R.id.message_text);
        Send_Button =findViewById(R.id.message_send);
        Location = findViewById(R.id.send_Loc);
        MyLocation =new LocationDao(this,MyLocationListener);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode ==MY_PERMISSIONS_REQUEST_Loc)
        {
      CurrentLocatin= MyLocation.GetCurrentLocation();
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
               }
         else
             {
             Request_Location_Permission();
             }
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

}
