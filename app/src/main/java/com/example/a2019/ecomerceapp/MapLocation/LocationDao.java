package com.example.a2019.ecomerceapp.MapLocation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import java.util.List;

public class LocationDao{
    LocationManager MylocationManger;
    Location MyLocation;
    LocationListener MyLocationListner;
    Context context;
    boolean CanGetLocation ;
       public LocationDao(Context context,LocationListener locationListener)
       {
           this.context = context;
           this.MylocationManger = (LocationManager) this.context.getSystemService(Context.LOCATION_SERVICE);
           MyLocation = null;
           MyLocationListner = locationListener;
           CanGetLocation=false;
       }
       @SuppressLint("MissingPermission")
       public Location GetCurrentLocation()
       {
           String Provider = null;

           if(this.MylocationManger.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
           {
               Provider = LocationManager.NETWORK_PROVIDER;
           }
           if(this.MylocationManger.isProviderEnabled(LocationManager.GPS_PROVIDER))
           {
               Provider = LocationManager.GPS_PROVIDER;
           }
           if(Provider ==null)
           {
               this.CanGetLocation = false;
               return null;
           }
           MyLocation = MylocationManger.getLastKnownLocation(Provider);
           if(MyLocation==null)
           {
               MyLocation = GetBestLastKnownProvider();
           }
           if(this.MyLocationListner !=null)
           {
               MylocationManger.requestLocationUpdates(Provider,500,10,MyLocationListner);
           }
           return MyLocation;
       }
    @SuppressLint("MissingPermission")
    private Location GetBestLastKnownProvider() {
           Location Best_Location = null;
        List<String> Providers =  MylocationManger.getProviders(true);
        for(String provider : Providers)
        {
           Location Loc = MylocationManger.getLastKnownLocation(provider);
            if(null == Best_Location && Loc!=null)
            {
                Best_Location=Loc;
            }
            else if(Loc!=null && Best_Location.getAccuracy()<Loc.getAccuracy())
            {
                Best_Location=Loc;
            }
        }
        return Best_Location;
    }
}
