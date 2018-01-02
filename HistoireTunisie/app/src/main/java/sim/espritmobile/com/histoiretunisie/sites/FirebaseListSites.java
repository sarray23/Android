package sim.espritmobile.com.histoiretunisie.sites;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

import sim.espritmobile.com.histoiretunisie.DetailSite;
import sim.espritmobile.com.histoiretunisie.DetailsMusees;
import sim.espritmobile.com.histoiretunisie.adaptater.ListSitesAdapter;
import sim.espritmobile.com.histoiretunisie.models.Musees;
import sim.espritmobile.com.histoiretunisie.models.Site;
import sim.espritmobile.com.histoiretunisie.utils.RecyclerItemClickListener;


public class FirebaseListSites {
    Firebase mRef;

    Context context;
    RecyclerView rv;
    String DB_URL;
    ListSitesAdapter adapter;
    ArrayList<Site> array=new ArrayList<>();


    public FirebaseListSites(Context context, String DB_URL, RecyclerView rv) {
        this.DB_URL = DB_URL;
        this.context = context;
        this.rv = rv;
       Firebase.setAndroidContext(context);
        mRef = new Firebase(DB_URL);


        //
    }
    public void retriveData() {

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                getupdate(dataSnapshot);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });



    }
    private void getupdate(DataSnapshot ds){


        for(DataSnapshot data : ds.getChildren()){
            Site sites=new Site();

            sites.setNom(data.getValue(Musees.class).getNom());
            sites.setDescription(data.getValue(Musees.class).getDescription());
            sites.setImg(data.getValue(Musees.class).getImg());
            sites.setDetailsList(data.getValue(Musees.class).getDetailsList());
            sites.setId(data.getKey());
            sites.setLattitude(data.getValue(Musees.class).getLattitude());
            sites.setLongitude(data.getValue(Musees.class).getLongitude());
            array.add(sites);

        }

        if (array.size()>0){
            adapter=new ListSitesAdapter(context,array);
            rv.setAdapter(adapter);
            rv.addOnItemTouchListener(new RecyclerItemClickListener(context, new RecyclerItemClickListener.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {

                    Intent intent = new Intent(context, DetailSite.class);
                    intent.putExtra("titre", array.get(position).getNom());
                    intent.putExtra("contenu", array.get(position).getDescription());
                    intent.putExtra("image", array.get(position).getImg());
                    intent.putExtra("id",array.get(position).getId());
                    intent.putExtra("longitude",array.get(position).getLongitude());
                    intent.putExtra("lattitude",array.get(position).getLattitude());


                    context.startActivity(intent);

                }
            }));


        }
        else{
            System.out.println("No Data...!");
        }

    }


}


