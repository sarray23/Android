package sim.espritmobile.com.histoiretunisie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import sim.espritmobile.com.histoiretunisie.adaptater.CategorieAdaptater;
import sim.espritmobile.com.histoiretunisie.monuments.ListMonuments;
import sim.espritmobile.com.histoiretunisie.musees.ListMusees;
import sim.espritmobile.com.histoiretunisie.personnages.ListPersonnages;
import sim.espritmobile.com.histoiretunisie.sites.ListSites;
import sim.espritmobile.com.histoiretunisie.utils.CategorieContent;


//









public class ListCategorie extends AppCompatActivity  {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorie);
        ListView maListViewPerso = (ListView)findViewById(R.id.listCategorie);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        CategorieAdaptater adapter =new CategorieAdaptater(this,R.layout.item_categorie, CategorieContent.getContent());

        maListViewPerso.setAdapter(adapter);
//        maListViewPerso.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                Toast.makeText(getApplicationContext(), "MUSEE", Toast.LENGTH_LONG);
//                return true;
//            }
//        });


        maListViewPerso.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position==0){
                Intent intent =new Intent(getApplicationContext(),ListMusees.class);
                startActivity(intent);}
                else if(position==1){
                    Intent i =new Intent(getApplicationContext(),ListMonuments.class);
                    startActivity(i);
                }
                else if (position==2){
                    Intent ii =new Intent(getApplicationContext(),ListSites.class);
                    startActivity(ii);
                }
                else if (position==3){
                    Intent iii =new Intent(getApplicationContext(),ListPersonnages.class);
                    startActivity(iii);
                }
            }
        });





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
