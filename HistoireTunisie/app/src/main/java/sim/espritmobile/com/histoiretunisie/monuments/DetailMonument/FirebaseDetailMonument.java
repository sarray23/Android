package sim.espritmobile.com.histoiretunisie.monuments.DetailMonument;

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

import sim.espritmobile.com.histoiretunisie.SousDetails;
import sim.espritmobile.com.histoiretunisie.adaptater.DetailAdapter;
import sim.espritmobile.com.histoiretunisie.models.Details;
import sim.espritmobile.com.histoiretunisie.utils.RecyclerItemClickListener;



public class FirebaseDetailMonument extends AppCompatActivity {
    Firebase mRef;
    Context context;
    RecyclerView rv;
    String DB_URL;
    DetailAdapter adapter;
    ArrayList<Details> array=new ArrayList<>();

    public FirebaseDetailMonument(Context context, String DB_URL, RecyclerView rv)  {
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

        System.out.println("hhh"+"      "+ds);

//        Map<String,String> map=ds.getValue(Map.class);
        for(DataSnapshot data : ds.getChildren()){

            System.out.println("hhh"+"      "+ds.getChildren());
            Details details=new Details();
            System.out.println("hhhhhhh");

            //  System.out.println("eertyui: "+ data.getValue(Musees.class).getNom());
            details.setTitre(data.getValue(Details.class).getTitre());
            details.setImg(data.getValue(Details.class).getImg());
            details.setDescription(data.getValue(Details.class).getDescription());
            array.add(details);


            if (array.size()>0){
                adapter=new DetailAdapter(context,array);
                rv.setAdapter(adapter);
                rv.addOnItemTouchListener(new RecyclerItemClickListener(context, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        Intent intent = new Intent(context, SousDetails.class);
                        intent.putExtra("titre", array.get(position).getTitre());
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


}


