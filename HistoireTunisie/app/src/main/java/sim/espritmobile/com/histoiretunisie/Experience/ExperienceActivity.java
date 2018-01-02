package sim.espritmobile.com.histoiretunisie.Experience;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import sim.espritmobile.com.histoiretunisie.ChoseExperienceActivity;
import sim.espritmobile.com.histoiretunisie.LoginActivity;
import sim.espritmobile.com.histoiretunisie.R;
import sim.espritmobile.com.histoiretunisie.SigninActivity;
import sim.espritmobile.com.histoiretunisie.musees.FirebaseListMusees;

/**
 * Created by sarra on 23/12/2016.
 */

public class ExperienceActivity extends AppCompatActivity {
    private LinearLayoutManager lLayout;
    FirebaseExperince firebaseClient;
    RecyclerView rv;
    private FirebaseAuth firebaseAuth;

    String DB_URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience);
        setTitle(null);


        Toolbar topToolBar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(topToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
       // topToolBar.setLogo(R.drawable.logo);
        //topToolBar.setLogoDescription(getResources().getString(R.string.logo_desc));


        DB_URL="https://histoiretunisie-fa10e.firebaseio.com/experiences";
        rv=(RecyclerView)findViewById(R.id.recycler_view) ;
        rv.setLayoutManager(new LinearLayoutManager(this));
    //List<Experience> rowListItem = getAllItemList();

      //  RecyclerViewAdapter rcAdapter = new RecyclerViewAdapter(ExperienceActivity.this, rowListItem);
      //  rv.setAdapter(rcAdapter);

        firebaseClient=new FirebaseExperince(this,DB_URL,rv);
        firebaseClient.retriveData();










    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            startActivity(new Intent(getApplicationContext(), SigninActivity.class));

            FirebaseAuth.getInstance().signOut();

        }
        if(id == R.id.action_refresh){
            startActivity(new Intent(getApplicationContext(), AddExperienceActivity.class));

            Toast.makeText(ExperienceActivity.this, "Add Experience ", Toast.LENGTH_LONG).show();
        }
        /*if(id == R.id.action_new){
            Toast.makeText(ExperienceActivity.this, "Create Text", Toast.LENGTH_LONG).show();
        }*/

 if(id==R.id.home)
 { onBackPressed();
     return true;

 }



    return super.onOptionsItemSelected(item);

}




}
