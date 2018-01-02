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
import sim.espritmobile.com.histoiretunisie.models.Monuments;

/**
 * Created by Sarra on 11/22/2016.
 */

public class ListMonumentAdapter extends RecyclerView.Adapter<ListMuseesHolder> {


        Context context;
        ArrayList<Monuments> monuments;

        public ListMonumentAdapter(Context context, ArrayList<Monuments> monuments) {
                this.context = context;
                this.monuments = monuments;
        }


        @Override
        public ListMuseesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_musees, parent, false);
                ListMuseesHolder holder = new ListMuseesHolder(v);
                return holder;
        }

        @Override
        public void onBindViewHolder(ListMuseesHolder holder, int position) {
                holder.nom.setText(monuments.get(position).getNom());
                holder.description.setText(monuments.get(position).getDescription());
                ListMuseesPicasso.downloadImg(context, monuments.get(position).getImg(), holder.img);
        }

        @Override
        public int getItemCount() {
                return monuments.size();
        }
}













