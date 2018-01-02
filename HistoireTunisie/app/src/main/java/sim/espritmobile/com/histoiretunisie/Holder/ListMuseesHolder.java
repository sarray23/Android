package sim.espritmobile.com.histoiretunisie.Holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import sim.espritmobile.com.histoiretunisie.R;

/**
 * Created by sarra on 11/17/2016.
 */

public class ListMuseesHolder extends RecyclerView.ViewHolder {

    public TextView nom;
    public TextView description;
    public ImageView img;

    public ListMuseesHolder(View itemView) {
        super(itemView);

        nom=(TextView) itemView.findViewById(R.id.txt_musees);
        description=(TextView)itemView.findViewById(R.id.description_musees);
        img=(ImageView) itemView.findViewById(R.id.imgee_musees);
    }
}
