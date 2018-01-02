package sim.espritmobile.com.histoiretunisie.adaptater;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import sim.espritmobile.com.histoiretunisie.DetailsMusees;
import sim.espritmobile.com.histoiretunisie.Holder.ListMuseesHolder;
import sim.espritmobile.com.histoiretunisie.PicassoImg.ListMuseesPicasso;
import sim.espritmobile.com.histoiretunisie.R;
import sim.espritmobile.com.histoiretunisie.models.Musees;

/**
 * Created by Sarra on 11/17/2016.
 */

public class ListMuseesAdapter extends RecyclerView.Adapter<ListMuseesHolder> {


    Context context;
    ArrayList<Musees> musees;

    public ListMuseesAdapter(Context context, ArrayList<Musees> musees) {
        this.context = context;
        this.musees = musees;
    }










    @Override
    public ListMuseesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_musees,parent,false);
        ListMuseesHolder holder=new ListMuseesHolder(v);




        return holder;
    }

    @Override
    public void onBindViewHolder(ListMuseesHolder holder, int position) {
        holder.nom.setText(musees.get(position).getNom());
        holder.description.setText(musees.get(position).getDescription());
        ListMuseesPicasso.downloadImg(context,musees.get(position).getImg(),holder.img);


    }

    @Override
    public int getItemCount() {
        return musees.size();
    }
}
