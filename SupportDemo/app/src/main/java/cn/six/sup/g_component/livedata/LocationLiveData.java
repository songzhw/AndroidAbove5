package cn.six.sup.g_component.livedata;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class LocationLiveData extends LiveData<Location> {
    private LocationManager locationManager;

    private SimpleLocationListener listener = new SimpleLocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            setValue(location);
        }
    };

    public LocationLiveData(Context context) {
        locationManager = (LocationManager) context.getSystemService(
                Context.LOCATION_SERVICE);
    }

    @SuppressLint("MissingPermission")
    @Override
    protected void onActive() {
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);
    }

    @Override
    protected void onInactive() {
        locationManager.removeUpdates(listener);
    }
}



class SimpleLocationListener implements LocationListener {
    @Override
    public void onLocationChanged(Location location) {}
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}
    @Override
    public void onProviderEnabled(String provider) {}
    @Override
    public void onProviderDisabled(String provider) {}
}