package sim.espritmobile.com.histoiretunisie.parcours;


import android.content.DialogInterface;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.List;

import sim.espritmobile.com.histoiretunisie.R;
import sim.espritmobile.com.histoiretunisie.adaptater.ListMuseesAdapter;
import sim.espritmobile.com.histoiretunisie.models.Musees;
import sim.espritmobile.com.histoiretunisie.utils.GetLocation;


public class ParcoursFragment extends Fragment implements OnMapReadyCallback {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Double longitude,latitude;
    public DatabaseReference mDatabase;
    ListMuseesAdapter adapter;
    private List<Musees> arrayMusees = new ArrayList<>();
    private List<Musees> histoireNearMe ;
    GoogleMap googlemap ;

    public ParcoursFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentEventNearMe.
     */
    // TODO: Rename and change types and number of parameters
    public static ParcoursFragment newInstance(String param1, String param2) {
        ParcoursFragment fragment = new ParcoursFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_parcours, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.fragment_parcours);
        mapFragment.getMapAsync(this);
        return view;

    }



    public void GetHistoireNearMe(){
        GetLocation getloc = new GetLocation(getActivity(),getActivity());

        longitude = getloc.getMylocation().getLongitude();
        latitude=getloc.getMylocation().getLatitude();

        // longitude = 9.887951798737049;
        //  latitude=37.24520511815063;
        for( Musees musees :arrayMusees){
            if (musees.getLongitude()-longitude>0 && musees.getLongitude()-longitude< Math.abs(0.048777) &&
                    musees.getLattitude()-latitude>0 &&  musees.getLattitude()-latitude<  Math.abs(0.035034)  ){
                histoireNearMe.add(musees);

            }
        }

    }

    @Override
    public void onMapReady(GoogleMap map) {

        googlemap=map;
        mDatabase.child("events").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                try {
                    System.out.println("key" + dataSnapshot.getKey());
                    final Musees model = dataSnapshot.getValue(Musees.class);
                    System.out.println("llllllllll" + model);
                    arrayMusees.add(model);
                    //
                    //
                    MarkerOptions markerOptions = new MarkerOptions();
                    GetLocation getloc = new GetLocation(getActivity(),getActivity());
                    LatLng place = new LatLng(model.getLattitude(), model.getLongitude());
                    System.out.println("lonnii"+model.getLongitude()+"lat"+model.getLattitude());
                    if ( Math.abs(model.getLongitude()-longitude)>0 && model.getLongitude()-longitude< Math.abs(0.048777) &&
                            Math.abs( model.getLattitude()-latitude)>0 &&  model.getLattitude()-latitude<  Math.abs(0.045034)  ) {

                        if (place != null) {
                            markerOptions.position(place).title("Marker in " + place);
                            googlemap.addMarker(markerOptions);
                            googlemap.animateCamera(CameraUpdateFactory.newLatLngZoom(place, 12));
                            googlemap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                                @Override
                                public boolean onMarkerClick(Marker marker) {
                                    final String[] viewDetail = {"Consulter", "Participer"};
                                    AlertDialog.Builder dialogmodifsupp = new AlertDialog.Builder(getActivity());
                                    dialogmodifsupp.setPositiveButton("View", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }});
                                    dialogmodifsupp.setNegativeButton("Participate", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }});
                                    dialogmodifsupp.setTitle(model.getNom());
                                    dialogmodifsupp.show();
                                    return false;
                                }
                            });
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

        MarkerOptions markerOptions = new MarkerOptions();
        GetLocation getloc = new GetLocation(getActivity(),getActivity());
        markerOptions.position(new LatLng(getloc.getMylocation().getLatitude(),getloc.getMylocation().getLongitude())).
                title("Marker in " +"MyPlace");
        map.addMarker(markerOptions);
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(getloc.getMylocation().getLatitude(),getloc.getMylocation().getLongitude()), 12));


        GetHistoireNearMe();
        Geocoder coder = new Geocoder(getActivity());
        List<android.location.Address> addresses;
        for (Musees i : arrayMusees) {
            LatLng place = new LatLng(i.getLattitude(), i.getLongitude());
            System.out.println("lon"+i.getLongitude()+"lat"+i.getLattitude());

            if (place != null){
                markerOptions.position(place).title("Marker in " + place);
                map.addMarker(markerOptions);
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(place, 12));
            }}



    }
}
