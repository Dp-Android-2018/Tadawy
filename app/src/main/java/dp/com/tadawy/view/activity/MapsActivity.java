package dp.com.tadawy.view.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import dp.com.tadawy.R;
import dp.com.tadawy.utils.ConfigurationFile;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    CameraUpdate cameraUpdate = null;
    Button b;
    LocationManager locationManager ;//= (LocationManager) getSystemService(LOCATION_SERVICE);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        b=findViewById(R.id.get);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLocation();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED) {
           mMap.setMyLocationEnabled(true);
        }else {
            ActivityCompat.requestPermissions(this, new String[] {
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION },1);
        }
    }

    public void getLocation() {
        System.out.println("button clicked");
        boolean statusOfGPS = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        Location loc = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        System.out.println("current location is  : "+loc);
        System.out.println("Long :"+loc.getLongitude()+"Lat :"+loc.getLatitude());
//        if (loc != null) {
//            LatLng latLang = new LatLng(loc.getLatitude(), loc.getLongitude());
//            cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLang,17);
//            mMap.animateCamera(cameraUpdate);
//        }
        Geocoder geocoder;
        List<Address> addresses = null;
        geocoder = new Geocoder(this, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(loc.getLatitude(), loc.getLongitude(), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
        } catch (IOException e) {
            e.printStackTrace();
        }

        String address = (addresses.get(0).getAddressLine(0));

        System.out.println("location test : "+address);
        Intent i=getIntent();
        i.putExtra(ConfigurationFile.IntentConstants.LATITUDE_REQUEST,loc.getLatitude());
        i.putExtra(ConfigurationFile.IntentConstants.ADDRESS_REQUEST,address);
        i.putExtra(ConfigurationFile.IntentConstants.LONGITUDE_REQUEST,loc.getLongitude());
        setResult(ConfigurationFile.IntentConstants.REQUEST_CODE_MAP,i);
        finish();
    }
}
