package sim.espritmobile.com.histoiretunisie.parcours;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.location.Geocoder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import android.content.pm.PackageManager;
import sim.espritmobile.com.histoiretunisie.R;
import sim.espritmobile.com.histoiretunisie.models.Monuments;
import sim.espritmobile.com.histoiretunisie.models.Musees;
import sim.espritmobile.com.histoiretunisie.models.Personnage;
import sim.espritmobile.com.histoiretunisie.models.Site;
import sim.espritmobile.com.histoiretunisie.parcours.Modules.DirectionFinder;
import sim.espritmobile.com.histoiretunisie.parcours.Modules.DirectionFinderListener;
import sim.espritmobile.com.histoiretunisie.parcours.Modules.Route;
import sim.espritmobile.com.histoiretunisie.utils.GetLocation;


public class Itineraire extends AppCompatActivity implements OnMapReadyCallback, DirectionFinderListener {

    private GoogleMap mMap;
    public DatabaseReference mDatabase;
    private Button btnFindPath;
    private List<Musees>arrayMusees=new ArrayList<>();
    private Double longitude,latitude;
    private EditText etOrigin;
    private EditText etDestination;
    private List<Marker> originMarkers = new ArrayList<>();
    private List<Marker> destinationMarkers = new ArrayList<>();
    private List<Polyline> polylinePaths = new ArrayList<>();
    private ProgressDialog progressDialog;
    private List<Musees> histoireNearMe ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itineraire);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        btnFindPath = (Button) findViewById(R.id.btnFindPath);
        etOrigin = (EditText) findViewById(R.id.etOrigin);
        etDestination = (EditText) findViewById(R.id.etDestination);
        btnFindPath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequest();
            }
        });
        ActivityCompat.requestPermissions(Itineraire.this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                1);
        ActivityCompat.requestPermissions(Itineraire.this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                1);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void sendRequest() {
        String origin = etOrigin.getText().toString();
        String destination = etDestination.getText().toString();
        if (origin.isEmpty()) {
            Toast.makeText(this, "Please enter origin address!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (destination.isEmpty()) {
            Toast.makeText(this, "Please enter destination address!", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            new DirectionFinder(this, origin, destination).execute();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }







    public void GetHistoireNearMe(){
        GetLocation getloc = new GetLocation(this,Itineraire.this);

        longitude = getloc.getMylocation().getLongitude();


        latitude=getloc.getMylocation().getLatitude();
        System.out.println("Ma position courante est LONGITUDE= "+longitude+" \n LATTITUDE= "+latitude);

        // longitude = 9.887951798737049;
        //  latitude=37.24520511815063;
        for( Musees musees :arrayMusees){
            System.out.println("bonjour\n");
            System.out.println("**********");
            System.out.println("test dappel de fonction!");
            if (musees.getLongitude()-longitude>0 && musees.getLongitude()-longitude< Math.abs(0.048777) &&
                    musees.getLattitude()-latitude>0 &&  musees.getLattitude()-latitude<  Math.abs(0.045034)  ){

                histoireNearMe.add(musees);

            }
        }

    }

    @Override
    public void onMapReady(GoogleMap map) {

        mMap=map;
        mDatabase.child("musee").addChildEventListener(new ChildEventListener() {

            @Override

            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                try {


                    /**************************** Test For MUSUEM **************************/
                    final Musees model = dataSnapshot.getValue(Musees.class);
                    MarkerOptions markerOptions = new MarkerOptions();
                    GetLocation getloc = new GetLocation(getApplicationContext(),Itineraire.this);
                    LatLng place = new LatLng(model.getLattitude(), model.getLongitude());
                    longitude = getloc.getMylocation().getLongitude();
                    latitude=getloc.getMylocation().getLatitude();
                    LatLng maposition=new LatLng(latitude,longitude);

                      if ( Math.abs(model.getLongitude()-longitude)>0 && Math.abs(model.getLongitude()-longitude)< Math.abs(0.048777) &&
                            Math.abs( model.getLattitude()-latitude)>0 &&  Math.abs(model.getLattitude()-latitude)<  Math.abs(0.045034)  ) {

                        if (place != null) {
                            markerOptions.position(place).title(model.getNom()).icon(BitmapDescriptorFactory
                                    .defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
                            mMap.addMarker(markerOptions);
                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(maposition, 12));

                        }
                    }


                } catch (Exception ex) {
                    Log.e("tagii", ex.getMessage());
                }
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });





       mDatabase.child("monument").addChildEventListener(new ChildEventListener() {
           @Override
           public void onChildAdded(DataSnapshot dataSnapshot, String s) {


               /**************************** Test For MONUMENT **************************/
               final Monuments modelMonument = dataSnapshot.getValue(Monuments.class);
               MarkerOptions markerOptions = new MarkerOptions();
               GetLocation getloc = new GetLocation(getApplicationContext(),Itineraire.this);
               LatLng place = new LatLng(modelMonument.getLattitude(), modelMonument.getLongitude());
               longitude = getloc.getMylocation().getLongitude();
               latitude=getloc.getMylocation().getLatitude();

               if ( Math.abs(modelMonument.getLongitude()-longitude)>0 && Math.abs(modelMonument.getLongitude()-longitude)< Math.abs(0.048777) &&
                       Math.abs( modelMonument.getLattitude()-latitude)>0 &&  Math.abs(modelMonument.getLattitude()-latitude)<  Math.abs(0.045034)  ) {

                   if (place != null) {
                       markerOptions.position(place).title(modelMonument.getNom()).icon(BitmapDescriptorFactory
                               .defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                       mMap.addMarker(markerOptions);
                       //mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(place, 12));

                   }
               }

           }

           @Override
           public void onChildChanged(DataSnapshot dataSnapshot, String s) {

           }

           @Override
           public void onChildRemoved(DataSnapshot dataSnapshot) {

           }

           @Override
           public void onChildMoved(DataSnapshot dataSnapshot, String s) {

           }

           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       });






        /****************************************Site Archéologique************************************************/
        mDatabase.child("site").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {


                /**************************** Test For MONUMENT **************************/
                final Site modelSite = dataSnapshot.getValue(Site.class);
                MarkerOptions markerOptions = new MarkerOptions();
                GetLocation getloc = new GetLocation(getApplicationContext(),Itineraire.this);
                LatLng place = new LatLng(modelSite.getLattitude(), modelSite.getLongitude());
                longitude = getloc.getMylocation().getLongitude();
                latitude=getloc.getMylocation().getLatitude();

                if ( Math.abs(modelSite.getLongitude()-longitude)>0 && Math.abs(modelSite.getLongitude()-longitude)< Math.abs(0.048777) &&
                        Math.abs( modelSite.getLattitude()-latitude)>0 &&  Math.abs(modelSite.getLattitude()-latitude)<  Math.abs(0.045034)  ) {

                    if (place != null) {
                        markerOptions.position(place).title(modelSite.getNom()).icon(BitmapDescriptorFactory
                                .defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
                        mMap.addMarker(markerOptions);
                        //mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(place, 12));

                    }
                }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });










        /****************************************Personnage Historique************************************************/
        mDatabase.child("personnage").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {


                /**************************** Test For MONUMENT **************************/
                final Personnage modelPersonnage = dataSnapshot.getValue(Personnage.class);
                MarkerOptions markerOptions = new MarkerOptions();
                GetLocation getloc = new GetLocation(getApplicationContext(),Itineraire.this);
                LatLng place = new LatLng(modelPersonnage.getLattitude(), modelPersonnage.getLongitude());
                longitude = getloc.getMylocation().getLongitude();
                latitude=getloc.getMylocation().getLatitude();

                if ( Math.abs(modelPersonnage.getLongitude()-longitude)>0 && Math.abs(modelPersonnage.getLongitude()-longitude)< Math.abs(0.048777) &&
                        Math.abs( modelPersonnage.getLattitude()-latitude)>0 &&  Math.abs(modelPersonnage.getLattitude()-latitude)<  Math.abs(0.045034)  ) {

                    if (place != null) {
                        markerOptions.position(place).title(modelPersonnage.getNom());
                        mMap.addMarker(markerOptions);
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(place, 12));

                    }
                }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });






        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);



    }



    @Override
    public void onDirectionFinderStart() {

        progressDialog = ProgressDialog.show(this, "Please wait.",
                "Finding direction..!", true);

        if (originMarkers != null) {
            for (Marker marker : originMarkers) {
                marker.remove();
            }
        }

        if (destinationMarkers != null) {
            for (Marker marker : destinationMarkers) {
                marker.remove();
            }
        }

        if (polylinePaths != null) {
            for (Polyline polyline:polylinePaths ) {
                polyline.remove();
            }
        }

    }

    @Override
    public void onDirectionFinderSuccess(List<Route> routes) {

        progressDialog.dismiss();
        polylinePaths = new ArrayList<>();
        originMarkers = new ArrayList<>();
        destinationMarkers = new ArrayList<>();

        for (Route route : routes) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(route.startLocation, 16));
            ((TextView) findViewById(R.id.tvDuration)).setText(route.duration.text);
            ((TextView) findViewById(R.id.tvDistance)).setText(route.distance.text);

            originMarkers.add(mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.start_blue))
                    .title(route.startAddress)
                    .position(route.startLocation)));
            destinationMarkers.add(mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.end_green))
                    .title(route.endAddress)
                    .position(route.endLocation)));

            PolylineOptions polylineOptions = new PolylineOptions().
                    geodesic(true).
                    color(Color.BLUE).
                    width(10);

            for (int i = 0; i < route.points.size(); i++)
                polylineOptions.add(route.points.get(i));

            polylinePaths.add(mMap.addPolyline(polylineOptions));
        }
    }

}

