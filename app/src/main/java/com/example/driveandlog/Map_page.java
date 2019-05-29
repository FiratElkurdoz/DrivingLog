package com.example.driveandlog;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


import static android.widget.Toast.LENGTH_LONG;

public class Map_page extends FragmentActivity implements OnMapReadyCallback,GeoTask.Geo {

    private GoogleMap mMap;

    private double startLongtitude = 55.506930;
    private double startLatitude =  10.471870;


    private double endLongtitude = 55.408884;
    private double endLatitude = 10.395557;

    public boolean isTripStarted = false;

    private String mDefaultLocation = "-33.8523341, 151.2106085";
    private static final int DEFAULT_ZOOM = 15;
    private Location location;




    LatLng origin;
    LatLng dest;
    public LatLng startMarkerPoints[];
    public LatLng endMarkerPoints[];

    TextView ShowDistanceDuration;
    Polyline line;


    /**
     * Request code for location permission request.
     *
     * @see #onRequestPermissionsResult(int, String[], int[])
     */
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    /**
     * Flag indicating whether a requested permission has been denied after returning in
     * {@link #onRequestPermissionsResult(int, String[], int[])}.
     */
    private boolean mPermissionDenied = false;
    private FusedLocationProviderClient fusedLocationClient;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_page);
        Toolbar toolbar = findViewById(R.id.toolbar);







        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        Button mapPageButton = (Button) findViewById(R.id.button2);

        mapPageButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                startActivity(new Intent(Map_page.this, List.class));
            }
        });

        Button logsButton = (Button) findViewById(R.id.button3);

        logsButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(Map_page.this, Profile.class));
            }
        });

        Button profileButton = (Button) findViewById(R.id.button4);

        profileButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if (Map_page.this != Map_page.this) {
                    startActivity(new Intent(Map_page.this, Map_page.class));
                }}
        });

        final Button startButton = (Button) findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                //build_retrofit_and_get_response("driving");
                if(!isTripStarted) {
                    Log.d("startKnap","START!");
                    startTrack();
                    isTripStarted = true;
                }
                //Log.d("location", mLastKnownLocation.toString());
            }
        });

        Button endButton = (Button) findViewById(R.id.endButton);
        endButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if(isTripStarted = true) {
                    Log.d("slutKnap", "SLUT!");
                    endTrack();
                    mDefaultLocation = mDefaultLocation.toString();
                    String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=40.6655101,-73.89188969999998&destinations=40.6905615,-73.9976592&mode=driving&key=AIzaSyB6-Lyogq5ysn7MMSgX7PBUKdX2wWeA9xI";
                    new GeoTask(Map_page.this).execute(url);

                    isTripStarted = false;
                }
            }
        });
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);


    }


    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;
        enableMyLocation();



    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void startTrack() {
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>(){
        @Override
                public void onComplete(@NonNull Task<Location> task){
            if(task.isSuccessful()){
                Location location = task.getResult();
                startLatitude = location.getLatitude();
                startLongtitude = location.getLongitude();
                final LatLng startPosition = new LatLng(startLatitude,startLongtitude);
                mMap.addMarker(new MarkerOptions().position(startPosition).title("start"));
                Log.d("start", String.valueOf(startLatitude));
            }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void endTrack() {
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>(){
            @Override
            public void onComplete(@NonNull Task<Location> task){
                if(task.isSuccessful()){
                    Location location = task.getResult();
                    endLatitude = location.getLatitude();
                    endLongtitude = location.getLongitude();
                    final LatLng endPosition = new LatLng(endLatitude,endLongtitude);
                    mMap.addMarker(new MarkerOptions().position(endPosition).title("end"));
                    Log.d("end", String.valueOf(endLatitude));

                }
            }
        });
    }

    /**
     * Enables the My Location layer if the fine location permission has been granted.
     */
    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission to access the location is missing.
            PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
                    Manifest.permission.ACCESS_FINE_LOCATION, true);
        } else if (mMap != null) {
            // Access to the location has been granted to the app.
            mMap.setMyLocationEnabled(true);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            return;
        }

        if (PermissionUtils.isPermissionGranted(permissions, grantResults,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Enable the my location layer if the permission has been granted.
            enableMyLocation();
        } else {
            // Display the missing permission error dialog when the fragments resume.
            mPermissionDenied = true;
        }
    }


    @Override
    protected void onStart(){
        super.onStart();
        Toast.makeText(this, "Start", LENGTH_LONG).show();

    }

    @Override
    protected  void onResume(){
        super.onResume();
        Toast.makeText(this, "Resumed", LENGTH_LONG).show();

    }

    @Override
    protected void onPause(){
        super.onPause();
        Toast.makeText(this, "Paused", LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "Stopped", LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }


    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        if (mPermissionDenied) {
            // Permission was not granted, display error dialog.
            showMissingPermissionError();
            mPermissionDenied = false;
        }
    }

    /**
     * Displays a dialog with error message explaining that the location permission is missing.
     */
    private void showMissingPermissionError() {
        PermissionUtils.PermissionDeniedDialog
                .newInstance(true).show(getSupportFragmentManager(), "dialog");
    }

    @Override
    public void setDouble(String min) {

    }
}
