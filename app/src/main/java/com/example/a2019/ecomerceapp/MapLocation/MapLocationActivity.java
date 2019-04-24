package com.example.a2019.ecomerceapp.MapLocation;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.a2019.ecomerceapp.Base.BaseActivity;
import com.example.a2019.ecomerceapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapLocationActivity extends BaseActivity {
    public static final int MY_PERMISSIONS_REQUEST_Location = 500;
    TextView SendYourLocation;
    MapView mapView;
    LocationDao MyLocation;
    Location CurruntLocation;
    GoogleMap MyGoogleMap;
    Marker marker;
    double Latitude;
    double logitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_location);
        SendYourLocation = findViewById(R.id.Send_Your_Location);
        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        MyLocation = new LocationDao(this,MyLocationListener);
        if(IsPermissionAllowed())  OpenMap();
        else RequestPermission();
        mapView.getMapAsync(MyCallBack);
    }
    private void RequestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(MapLocationActivity.this,
                Manifest.permission.READ_CONTACTS)) {
            // Show an explanation to the user
            showConfirmationMessage(R.string.Message, R.string.I_Need_This_Permisstion, R.string.Yes, new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                    ActivityCompat.requestPermissions(MapLocationActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            MY_PERMISSIONS_REQUEST_Location);
                }
            });

        } else {
            // request the permission
            ActivityCompat.requestPermissions(MapLocationActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_Location);
        }
    }
    private void OpenMap() {
      CurruntLocation = MyLocation.GetCurrentLocation();
      Latitude = CurruntLocation.getLatitude();
      logitude=CurruntLocation.getLongitude();
    }
    public boolean IsPermissionAllowed() {
        if (ContextCompat.checkSelfPermission(MapLocationActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        else return true;
    }
    @Override protected void onStart() {
        super.onStart();mapView.onStart();
    }
    @Override protected void onStop() {
        super.onStop();
        mapView.onStop();
    }
    @Override protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
    @Override protected void onPause() {
        super.onPause();
        mapView.onPause();
    }
    @Override protected void onResume() {
        super.onResume();
        mapView.onResume();
    }
    @Override public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
    LocationListener MyLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            CurruntLocation = location;
            Latitude = CurruntLocation.getLatitude();
            logitude = CurruntLocation.getLongitude();
            ChangeMarker();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {
            Toast.makeText(activity, provider+"is Enabled", Toast.LENGTH_SHORT).show();
            MyLocation.GetCurrentLocation();
        }

        @Override
        public void onProviderDisabled(String provider) {
            Toast.makeText(activity, provider+"is Disabled", Toast.LENGTH_SHORT).show();

        }
    };
    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_Location: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    MyLocation.GetCurrentLocation();
                } else {
                    // permission denied, boo! Disable the
                    Toast.makeText(activity, "Permission denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
        }
    }
    OnMapReadyCallback MyCallBack = new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap googleMap) {
            MyGoogleMap = googleMap;
            ChangeMarker();
        }
    };
    public void ChangeMarker()
    {     LatLng latLng = new LatLng(Latitude,logitude);
        if(CurruntLocation!=null && marker ==null)
        {
            MarkerOptions  markerOptions = new MarkerOptions().position(latLng).title("You Are Here");
            marker = this.MyGoogleMap.addMarker(markerOptions);
        }
        else{ marker.setPosition(latLng); }
          MyGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));    }
}
