package com.deltahacks4.tommyandshaq.getmacclimated;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.PowerManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    // Power Manager
    public PowerManager.WakeLock wl;
    public Location currentLocation;

    public int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION;
    public int MY_PERMISSIONS_REQUEST_WAKE_LOCK;

    public String locationProvider;
    public LocationListener locationListener;
    public LocationManager locationManager;


    private static final String TAG = MapsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        checkPermissions();
        startLocationUpdates();


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);






    }

    public void openMaker(View view) {
        Intent myIntent = new Intent(this,EventMaker.class);
        startActivity(myIntent);
    }


    protected void onStart() {
        super.onStart();
        if (wl != null) {
            wl.release();
        }
    }

    protected  void onStop(){
        super.onStop();

    }

    protected void onPause(){
        super.onPause();
        setWakeLock();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }


    public void setLocations(Event[] events) {
        List<LatLng> locations = new ArrayList<>(events.length);
        for (int i = 0; i < events.length - 1; i++) {
            locations.add(events[i].getLatLng());
            mMap.addMarker(new MarkerOptions().position(events[i].getLatLng()).title(events[i].getName()).snippet(events[i].getLocation()));
        }
    }

    public void setWakeLock() {
        PowerManager pm = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
        wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "MyWakeLock");
        wl.acquire();
    }

    public void startLocationUpdates() {
        // Acquire a reference to the system Location Manager
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

// Define a listener that responds to location updates
        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.
                locationProvider = LocationManager.GPS_PROVIDER;


                updateMap(locationManager.getLastKnownLocation(locationProvider));
            }


            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
                mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude())));
                mMap.moveCamera(CameraUpdateFactory.zoomTo(18));
            }

            public void onProviderDisabled(String provider) {

            }
        };

// Register the listener with the Location Manager to receive location updates
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 0, locationListener);
    }

    public void updateMap(Location location) {
        currentLocation = new Location(location);
        LatLng currentLatLng = new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude());
        mMap.clear();
        mMap.addCircle(new CircleOptions().center(currentLatLng).radius(1).visible(true).fillColor(0xff0000ff).strokeColor(0));


    }


    public boolean checkPermissions() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WAKE_LOCK},
                MY_PERMISSIONS_REQUEST_WAKE_LOCK);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            Log.i(TAG, "PERMISSIONS NOT SATISFIED");
            return false;
        }

    }
}
