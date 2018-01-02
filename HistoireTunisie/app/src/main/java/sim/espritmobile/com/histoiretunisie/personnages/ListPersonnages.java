package sim.espritmobile.com.histoiretunisie.personnages;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import sim.espritmobile.com.histoiretunisie.R;


public class ListPersonnages extends AppCompatActivity {
    FirebaseListPersonnages firebaseClient;
    RecyclerView rv;
    String DB_URL;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_musees);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        DB_URL="https://histoiretunisie-fa10e.firebaseio.com/personnage";
        rv=(RecyclerView)findViewById(R.id.listmusees) ;

        rv.setLayoutManager(new LinearLayoutManager(this));
        firebaseClient=new FirebaseListPersonnages(this,DB_URL,rv);
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
}
