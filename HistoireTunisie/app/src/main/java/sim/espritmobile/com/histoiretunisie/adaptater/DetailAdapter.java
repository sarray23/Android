package sim.espritmobile.com.histoiretunisie.adaptater;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import sim.espritmobile.com.histoiretunisie.Holder.DetailHolder;
import sim.espritmobile.com.histoiretunisie.Holder.ListMuseesHolder;
import sim.espritmobile.com.histoiretunisie.PicassoImg.ListMuseesPicasso;
import sim.espritmobile.com.histoiretunisie.R;
import sim.espritmobile.com.histoiretunisie.models.Details;
import sim.espritmobile.com.histoiretunisie.models.Musees;

/**
 * Created by sarra on 12/8/2016.
 */

public class DetailAdapter extends RecyclerView.Adapter<DetailHolder> {

    Context context;
    ArrayList<Details> details;

    public DetailAdapter(Context context, ArrayList<Details> details) {
        this.context = context;
        this.details = details;
    }




    @Override
    public DetailHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.content_details_musees,parent,false);
        DetailHolder holder=new DetailHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(DetailHolder holder, int position) {
        holder.titredetail.setText(details.get(position).getTitre());
        ListMuseesPicasso.downloadImg(context,details.get(position).getImg(),holder.img);

    }

    @Override
    public int getItemCount() {
        return details.size();
    }
}
