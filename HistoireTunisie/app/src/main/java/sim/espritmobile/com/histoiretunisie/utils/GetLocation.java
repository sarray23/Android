package sim.espritmobile.com.histoiretunisie.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import sim.espritmobile.com.histoiretunisie.parcours.Itineraire;


public class GetLocation {
    LocationManager myManager;
    LocationListener loc;
    Location location;
    Context context;
    Activity act;

    public GetLocation(Context context, Activity act) {
        this.context = context;
        this.act = act;
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        ActivityCompat.requestPermissions(act,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                1);
        ActivityCompat.requestPermissions(act,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                1);

        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }

            }
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding

                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            loc = new MyLocationListener();
            myManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            myManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, loc);
            myManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, loc);
            if (myManager != null) {
                location = myManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                location = myManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                if (location != null) {

                } else {
                    Toast.makeText(context, "manager null", Toast.LENGTH_LONG).show();
                }

            }

        }

        // other 'case' lines to check for other
        // permissions this app might request
    }


    public Location getMylocation() {


        //    ActivityCompat#requestPermissions
        // here to request the missing permissions, and then overriding
        loc = new MyLocationListener();
        myManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            System.out.println("no per");
        }
        myManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, loc);
        myManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, loc);
        if (myManager != null) {
            location = myManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            location = myManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (location != null) {
                return location;
            } else {
                Toast.makeText(context, "manager null", Toast.LENGTH_LONG).show();
            }

        }

        return location;


    }
}
