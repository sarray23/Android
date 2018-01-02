package sim.espritmobile.com.histoiretunisie;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import sim.espritmobile.com.histoiretunisie.Experience.ExperiencesUsersActivity;
import sim.espritmobile.com.histoiretunisie.models.Details;
import sim.espritmobile.com.histoiretunisie.models.Musees;
import sim.espritmobile.com.histoiretunisie.parcours.Itineraire;


public class Accueil extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
Button btnMap;
    private Firebase mRef;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);
        MediaPlayer mMediaPlayer=new MediaPlayer();
        mMediaPlayer=MediaPlayer.create(this,R.raw.sabri);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setLooping(true);
        mMediaPlayer.start();

             final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        final MediaPlayer finalMMediaPlayer = mMediaPlayer;
        fab.setTag("ok");
        fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   if( fab.getTag()=="ok"){
                   fab.setImageResource(R.drawable.ic_volume_up_white_24dp);
                    finalMMediaPlayer.pause();
                       fab.setTag("no");
                   }
                    else if (fab.getTag()=="no"){
                       fab.setImageResource(R.drawable.ic_volume_off_white_24dp);
                       finalMMediaPlayer.start();
                       fab.setTag("ok");
                   }

                }
            });

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);



        ImageView image = (ImageView)findViewById(R.id.backgroundimg);
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.clockwise);
        image.startAnimation(animation1);

        btnMap=(Button) findViewById(R.id.btnMap);

        Firebase.setAndroidContext(this);



        mRef=new Firebase("https://histoiretunisie-fa10e.firebaseio.com/");
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent ii= new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(ii);

            }
        });



    }



    public void zoom(View view){

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.accueil, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(getApplicationContext(),About.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            ArrayList<Details> maliste=new ArrayList<Details>();
            Details d=new Details();Details d2=new Details();
            d.setImg("http://res.cloudinary.com/dv4z2vwnno/image/upload/v1479414757/musees_rrfavi.jpg");
            d.setTitre("Ceci est un test pour les details");
            d2.setImg("http://res.cloudinary.com/dv4z2vwnno/image/upload/v1479414757/musees_rrfavi.jpg");
            d2.setTitre("Ceci est un test pour les details ceci est 2éme");
            maliste.add(d);
            maliste.add(d2);



            Musees m1=new Musees();
            m1.setNom("Aumphi théatre romain");
            m1.setDescription("Située au Kef");


            m1.setDetailsList(maliste);
            m1.setImg("http://res.cloudinary.com/dv4z2vwnno/image/upload/v1480539584/site1_sswnqo.jpg");
            dataSave(m1);

            // Handle the camera action
        } else if (id == R.id.nav_categorie) {
            Intent i =new Intent(getApplicationContext(),ListCategorie.class);
            startActivity(i);

        } else if (id == R.id.nav_parcours) {

            startActivity(new Intent(getApplicationContext(),Itineraire.class));


        } else if (id == R.id.nav_experience) {
            firebaseAuth=FirebaseAuth.getInstance();
            FirebaseUser user=firebaseAuth.getCurrentUser();
            if (user!=null){
                startActivity(new Intent(getApplicationContext(), ChoseExperienceActivity.class));}
            else{
                startActivity(new Intent(getApplicationContext(),SigninActivity.class));
            }

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void dataSave(Musees m){
        mRef.child("musee").push().setValue(m);
    }
}
