package cn.six.sup.g_component.sample.location;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

// compile 'com.google.android.gms:play-services-plus:11.2.0'
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
// compile 'com.google.android.gms:play-services-location:11.2.0'
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

@SuppressWarnings("MissingPermission")
public class LocationDemo1 extends Activity implements GoogleApiClient.ConnectionCallbacks,
    GoogleApiClient.OnConnectionFailedListener, LocationListener {
    private FusedLocationProviderApi locationClient;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        googleApiClient = new GoogleApiClient.Builder(this)
            .addApi(LocationServices.API)
            .addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this)
            .build();

        locationClient = LocationServices.FusedLocationApi;

        locationRequest = LocationRequest.create()
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            .setInterval(5000)
            .setFastestInterval(1000)
            .setSmallestDisplacement(2);
    }


    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("szw onResume()");
        if (googleApiClient.isConnected()) {
            locationClient.requestLocationUpdates(googleApiClient, locationRequest, this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("szw onPause()");
        if (googleApiClient.isConnected()) {
            locationClient.removeLocationUpdates(googleApiClient, this);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("szw onStart()");
        googleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("szw onStop()");
        googleApiClient.disconnect();
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        System.out.println("szw google play connected");
    }

    @Override
    public void onConnectionSuspended(int i) {
        System.out.println("szw google play connection suspended");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        System.out.println("szw google play connect failed");
    }

    @Override
    public void onLocationChanged(Location location) {
        System.out.println("szw location : ( " + location.getLatitude() + " , " + location.getLongitude() + " )");
    }
}
