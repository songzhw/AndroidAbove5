package cn.six.sup.g_component.sample.location;

import android.Manifest;
import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import ca.six.util.IAfterDo;
import ca.six.util.Permission6;

public class LocationWatcher implements LifecycleObserver, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {
    private FusedLocationProviderApi locationClient;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private boolean isFirstTime = true;
    private boolean isUserDenyPermission = false;
    private Activity activity;

    public LocationWatcher(Activity activity) {
        this.activity = activity;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate(){
        googleApiClient = new GoogleApiClient.Builder(activity)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        googleApiClient.connect();

        locationClient = LocationServices.FusedLocationApi;

        locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(5000)
                .setFastestInterval(1000)
                .setSmallestDisplacement(2);

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy(){
        System.out.println("szw + onDestroy()");
        googleApiClient.disconnect();
    }

    @SuppressWarnings("MissingPermission")
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume(){
        System.out.println("szw + onResume()");

        if (isUserDenyPermission){
            return;
        }

        if (googleApiClient.isConnected()) {
            locationClient.requestLocationUpdates(googleApiClient, locationRequest, this);
        }

        /*If this is the first time ever we connected to Google API Client we wouldn't have a valid last location.  In this case, don't trigger a location update, and wait for the regular updates*/
        // 弹出"请求权限对话框"时, onPause被调用了. 点击对话框上的按钮 这时onResume()再次被调用, 这时基本上googleApiClient已经连接上了
        if (isFirstTime) {
            Location location = locationClient.getLastLocation(googleApiClient);
            if (location != null) {
                System.out.println("szw + location2 : ( " + location.getLatitude() + " , " + location.getLongitude() + " )");
                isFirstTime = false;
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause(){
        System.out.println("szw + onPause()");
        if (googleApiClient.isConnected()) {
            locationClient.removeLocationUpdates(googleApiClient, this);
        }
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        System.out.println("szw + google play connected");
    }

    @Override
    public void onConnectionSuspended(int i) {
        System.out.println("szw + google play connection suspended");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        System.out.println("szw + google play connect failed");
    }



    @Override
    public void onLocationChanged(Location location) {
        System.out.println("szw + location1 : ( " + location.getLatitude() + " , " + location.getLongitude() + " )");
    }


}

