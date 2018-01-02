package sim.espritmobile.com.histoiretunisie.Holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import sim.espritmobile.com.histoiretunisie.R;

/**
 * Created by sarra on 12/8/2016.
 */

public class DetailHolder extends RecyclerView.ViewHolder {

    public TextView titredetail;
    public TextView descriptionDetail;
    public ImageView img;

    public DetailHolder(View itemView) {
        super(itemView);


         titredetail=(TextView)itemView.findViewById(R.id.titre_detail);
         descriptionDetail=(TextView)itemView.findViewById(R.id.description_detail);

        img=(ImageView) itemView.findViewById(R.id.imgdetail);
    }
}
