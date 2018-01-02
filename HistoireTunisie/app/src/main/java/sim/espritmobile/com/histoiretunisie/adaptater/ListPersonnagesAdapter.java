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
import sim.espritmobile.com.histoiretunisie.models.Personnage;

/**
 * Created by Sarra on 11/22/2016.
 */

public class ListPersonnagesAdapter  extends RecyclerView.Adapter<ListMuseesHolder> {


    Context context;
    ArrayList<Personnage> personnages;

    public ListPersonnagesAdapter(Context context, ArrayList<Personnage> personnages) {
        this.context = context;
        this.personnages = personnages;
    }


    @Override
    public ListMuseesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_musees, parent, false);
        ListMuseesHolder holder = new ListMuseesHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ListMuseesHolder holder, int position) {
        holder.nom.setText(personnages.get(position).getNom());
        holder.description.setText(personnages.get(position).getDescription());
        ListMuseesPicasso.downloadImg(context, personnages.get(position).getImg(), holder.img);
    }

    @Override
    public int getItemCount() {
        return personnages.size();
    }
}













