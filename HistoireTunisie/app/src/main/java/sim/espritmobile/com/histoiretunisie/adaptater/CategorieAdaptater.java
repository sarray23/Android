package sim.espritmobile.com.histoiretunisie.adaptater;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;
import sim.espritmobile.com.histoiretunisie.R;
import sim.espritmobile.com.histoiretunisie.models.CategorieEntity;
import sim.espritmobile.com.histoiretunisie.monuments.ListMonuments;
import sim.espritmobile.com.histoiretunisie.musees.ListMusees;

/**
 * Created by sarra on 08/11/2016.
 */





public class CategorieAdaptater extends  ArrayAdapter<CategorieEntity> {
    Context context;
    int resource;
    List<CategorieEntity> mediaItems=null;


    public CategorieAdaptater(Context context, int resource, List<CategorieEntity> mediaItems) {
        super(context, resource, mediaItems);
        this.context=context;
        this.resource=resource;
        this.mediaItems=mediaItems;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {

        View view =convertView;
        ArticleHolder pr=new ArticleHolder();
        if (view==null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(resource, parent, false);
            pr.image=(ImageView) view.findViewById(R.id.imgee);
            pr.textTitle=(TextView)view.findViewById(R.id.txt) ;
            view.setTag(pr);
        }else{pr=(ArticleHolder) view.getTag();}
            pr.textTitle.setText(getItem(position).getTitre());
            Picasso.with(context).load(getItem(position).getImg()).into(pr.image);


        return view;
    }

}



class ArticleHolder extends AppCompatActivity{
    ImageView image;
    TextView textTitle;



}