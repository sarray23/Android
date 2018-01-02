package sim.espritmobile.com.histoiretunisie.Experience;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import sim.espritmobile.com.histoiretunisie.PicassoImg.ListMuseesPicasso;
import sim.espritmobile.com.histoiretunisie.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolders> {

    private List<Experience> itemList;
    private Context context;

    public RecyclerViewAdapter(Context context, List<Experience> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_list, null);
        RecyclerViewHolders hover = new RecyclerViewHolders(layoutView);
        return hover;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {
        holder.countryName.setText(itemList.get(position).getName());
     //  holder.countryPhoto.setImageResource(itemList.get(position).getPhoto());
        ListMuseesPicasso.downloadImg(context,itemList.get(position).getImage(),holder.countryPhoto);

    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}