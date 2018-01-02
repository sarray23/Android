package sim.espritmobile.com.histoiretunisie.musees;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import sim.espritmobile.com.histoiretunisie.DetailsMusees;
import sim.espritmobile.com.histoiretunisie.adaptater.ListMuseesAdapter;
import sim.espritmobile.com.histoiretunisie.models.Details;
import sim.espritmobile.com.histoiretunisie.models.Musees;
import sim.espritmobile.com.histoiretunisie.utils.RecyclerItemClickListener;

/**
 * Created by sarra on 11/17/2016.
 */

public class FirebaseListMusees extends AppCompatActivity{

    Firebase mRef;
    Context context;
    RecyclerView rv;
    String DB_URL;
    ListMuseesAdapter adapter;
    ArrayList<Musees> array=new ArrayList<>();



    public FirebaseListMusees( Context context, String DB_URL,RecyclerView rv)  {
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
            Musees musees=new Musees();

          //  System.out.println("eertyui: "+ data.getValue(Musees.class).getNom());
            musees.setNom(data.getValue(Musees.class).getNom());
            musees.setDescription(data.getValue(Musees.class).getDescription());
            musees.setImg(data.getValue(Musees.class).getImg());
            musees.setDetailsList(data.getValue(Musees.class).getDetailsList());
            musees.setId(data.getKey());
            musees.setLattitude(data.getValue(Musees.class).getLattitude());
            musees.setLongitude(data.getValue(Musees.class).getLongitude());

            array.add(musees);


        }

        if (array.size()>0){
            adapter=new ListMuseesAdapter(context,array);
            rv.setAdapter(adapter);



            rv.addOnItemTouchListener(new RecyclerItemClickListener(context, new RecyclerItemClickListener.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {

                    Intent intent = new Intent(context, DetailsMusees.class);
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


