package com.example.a2019.ecomerceapp.MapLocation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.a2019.ecomerceapp.Customers.Activities.ChatActivity;
import com.example.a2019.ecomerceapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class ShowMapActivity extends AppCompatActivity implements OnMapReadyCallback {
     MapView ShowBiggerMAp;
    static   Marker marker;
    GoogleMap myGoogleMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_map);
        ShowBiggerMAp = findViewById(R.id.ShowBiggerMap);
        ShowBiggerMAp.onCreate(savedInstanceState);
        ShowBiggerMAp.getMapAsync(this);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ShowBiggerMAp.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        ShowBiggerMAp.onLowMemory();
    }

    @Override
    protected void onStart() {
        super.onStart();
        ShowBiggerMAp.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        ShowBiggerMAp.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ShowBiggerMAp.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        ShowBiggerMAp.onPause();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Double laT = Double.parseDouble(ChatActivity.MessageThatIWantTOGetTheLocationFromIT.getLatitude());
        Double longtiude = Double.parseDouble(ChatActivity.MessageThatIWantTOGetTheLocationFromIT.getLongitude());
        LatLng latLng = new LatLng(laT,longtiude);
        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("Here");
        if(marker == null)
        {
            marker= googleMap.addMarker(markerOptions);
        }
        else
        {
         marker = null;
         marker =googleMap.addMarker(markerOptions);
        }
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));

    }
}
