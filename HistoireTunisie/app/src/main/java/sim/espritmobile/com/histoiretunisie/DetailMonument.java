package sim.espritmobile.com.histoiretunisie;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.firebase.client.Firebase;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import sim.espritmobile.com.histoiretunisie.PicassoImg.ListMuseesPicasso;
import sim.espritmobile.com.histoiretunisie.adaptater.DetailAdapter;
import sim.espritmobile.com.histoiretunisie.models.Details;
import sim.espritmobile.com.histoiretunisie.musees.DetailMusee.FirebaseDetailMusee;



public class DetailMonument extends AppCompatActivity implements OnMapReadyCallback {
    Firebase mRef;
    Context context;
    FirebaseDetailMusee firebaseClient;
    RecyclerView rv;
    GoogleMap mMap;
    DetailAdapter adapter;
    String DB_URL;
    ArrayList<Details> array=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_musees);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        Bundle bundle=getIntent().getExtras();
        String titre=bundle.getString("titre");
        String contenu=bundle.getString("contenu");
        String img=bundle.getString("image");
        String id=bundle.getString("id");

        ImageView myImg=(ImageView)findViewById(R.id.imgdetaille);
        ListMuseesPicasso.downloadImg(context,img, myImg);
        double longitude=bundle.getDouble("longitude");
        double lattitude=bundle.getDouble("lattitude");
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapdetail);
        mapFragment.getMapAsync(this);

        DB_URL="https://histoiretunisie-fa10e.firebaseio.com/monument/"+id+"/detailsList/";
        rv=(RecyclerView)findViewById(R.id.listdetail) ;

        rv.setLayoutManager(new LinearLayoutManager(this));
        firebaseClient=new FirebaseDetailMusee(this,DB_URL,rv);
        firebaseClient.retriveData();



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
    @Override
    public void onMapReady(GoogleMap googleMap) {



        Bundle bundle=getIntent().getExtras();
        String titre=bundle.getString("titre");
        double longitude=bundle.getDouble("longitude");
        double lattitude=bundle.getDouble("lattitude");
        System.out.println("Ma longitude est"+longitude+" Ma lattitude est:"+lattitude);

        mMap = googleMap;
        LatLng latLng=new LatLng(lattitude,longitude);
        MarkerOptions mMarker=new MarkerOptions();
        mMarker.position(latLng);
        mMap.addMarker(mMarker
                .title(titre)

        );
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12));
    }
}

