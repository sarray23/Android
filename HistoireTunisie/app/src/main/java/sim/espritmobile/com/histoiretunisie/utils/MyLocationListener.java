package sim.espritmobile.com.histoiretunisie.utils;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.location.LocationListener;

/**
 * Created by sarra on 12/9/2016.
 */

public class MyLocationListener implements android.location.LocationListener {


    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            Log.v("Longitude", "" + location.getLongitude());
            Log.v("Latitude", "" + location.getLatitude());

        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


}