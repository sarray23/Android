package sim.espritmobile.com.histoiretunisie.adaptater;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import sim.espritmobile.com.histoiretunisie.Holder.ListMuseesHolder;
import sim.espritmobile.com.histoiretunisie.PicassoImg.ListMuseesPicasso;
import sim.espritmobile.com.histoiretunisie.R;
import sim.espritmobile.com.histoiretunisie.models.Site;

/**
 * Created by Rami on 11/22/2016.
 */

public class ListSitesAdapter  extends RecyclerView.Adapter<ListMuseesHolder> {


    Context context;
    ArrayList<Site> sites;

    public ListSitesAdapter(Context context, ArrayList<Site> sites) {
        this.context = context;
        this.sites = sites;
    }


    @Override
    public ListMuseesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_musees, parent, false);
        ListMuseesHolder holder = new ListMuseesHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ListMuseesHolder holder, int position) {
        holder.nom.setText(sites.get(position).getNom());
        holder.description.setText(sites.get(position).getDescription());
        ListMuseesPicasso.downloadImg(context, sites.get(position).getImg(), holder.img);
    }

    @Override
    public int getItemCount() {
        return sites.size();
    }
}













