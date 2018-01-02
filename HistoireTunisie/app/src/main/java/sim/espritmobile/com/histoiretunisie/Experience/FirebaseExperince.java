package sim.espritmobile.com.histoiretunisie.Experience;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

import sim.espritmobile.com.histoiretunisie.DetailMonument;
import sim.espritmobile.com.histoiretunisie.utils.RecyclerItemClickListener;

/**
 * Created by sarra on 24/12/2016.
 */

public class FirebaseExperince extends AppCompatActivity{
    Firebase mRef;

    Context context;
    RecyclerView rv;
    String DB_URL;
    RecyclerViewAdapter adapter;
    ArrayList<Experience> array=new ArrayList<>();
    public FirebaseExperince(Context context, String DB_URL, RecyclerView rv)
    {

        this.DB_URL = DB_URL;
        this.context = context;
        this.rv = rv;
        Firebase.setAndroidContext(context);
        mRef = new Firebase(DB_URL);

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
            Experience experiences=new Experience();

            experiences.setName(data.getValue(Experience.class).getName());
            experiences.setImage(data.getValue(Experience.class).getImage());
            experiences.setId(data.getKey());


            array.add(experiences);

        }

        if (array.size()>0){
            adapter=new RecyclerViewAdapter(context,array);
            rv.setAdapter(adapter);



        }
        else{
            System.out.println("No Data...!");
        }

    }



}
