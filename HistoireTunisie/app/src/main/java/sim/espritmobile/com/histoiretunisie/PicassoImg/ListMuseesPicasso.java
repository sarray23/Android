package sim.espritmobile.com.histoiretunisie.PicassoImg;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


import sim.espritmobile.com.histoiretunisie.R;



public class ListMuseesPicasso {


    public static void downloadImg(Context c , String url, ImageView img){
        if (url!=null && url.length()>0){
            Picasso.with(c).load(Uri.parse(url)).placeholder(R.drawable.beb).into(img);
        }else {

        }
    }
}
