package sim.espritmobile.com.histoiretunisie.personnages;

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

import sim.espritmobile.com.histoiretunisie.SousDetails;
import sim.espritmobile.com.histoiretunisie.adaptater.ListPersonnagesAdapter;
import sim.espritmobile.com.histoiretunisie.models.Musees;
import sim.espritmobile.com.histoiretunisie.models.Personnage;
import sim.espritmobile.com.histoiretunisie.utils.RecyclerItemClickListener;


public class FirebaseListPersonnages {
    Firebase mRef;

    Context context;
    RecyclerView rv;
    String DB_URL;
    ListPersonnagesAdapter adapter;
    ArrayList<Personnage> array=new ArrayList<>();


    public FirebaseListPersonnages(Context context, String DB_URL, RecyclerView rv) {
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
            Personnage personnage=new Personnage();
            Log.d("ahmed",data.toString());
            personnage.setNom(data.getValue(Musees.class).getNom());
            personnage.setDescription(data.getValue(Musees.class).getDescription());
            personnage.setImg(data.getValue(Musees.class).getImg());
            array.add(personnage);

        }

        if (array.size()>0){
            adapter=new ListPersonnagesAdapter(context,array);
            rv.setAdapter(adapter);
            rv.addOnItemTouchListener(new RecyclerItemClickListener(context, new RecyclerItemClickListener.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {

                    Intent intent = new Intent(context, SousDetails.class);
                    intent.putExtra("titre", array.get(position).getNom());
                    intent.putExtra("contenu", array.get(position).getDescription());
                    intent.putExtra("image", array.get(position).getImg());


                    context.startActivity(intent);

                }
            }));
        }
        else{
            System.out.println("No Data...!");
        }

    }


}


