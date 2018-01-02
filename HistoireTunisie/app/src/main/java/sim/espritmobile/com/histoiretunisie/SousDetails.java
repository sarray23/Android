package sim.espritmobile.com.histoiretunisie;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import sim.espritmobile.com.histoiretunisie.PicassoImg.ListMuseesPicasso;

public class SousDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sous_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        Bundle bundle=getIntent().getExtras();
        String titre=bundle.getString("titre");
        String contenu=bundle.getString("contenu");
        String img=bundle.getString("image");
        String id=bundle.getString("id");
        String imgmus=bundle.getString("imgcover");





        ImageView imgd= (ImageView)findViewById(R.id.imgsousdetails);
        TextView texte=(TextView)findViewById(R.id.titresousdetails);
        TextView descrip=(TextView)findViewById(R.id.descriptionsousdetail);









        Intent intent = getIntent();
        CardView cardView = (CardView) findViewById(R.id.card_view);



        //These are lines helping Details_Card To Animate
        //===============================================
        AnimatorSet animationSet = new AnimatorSet();

        //Translating Details_Card in Y Scale
        ObjectAnimator card_y = ObjectAnimator.ofFloat(cardView, View.TRANSLATION_Y, 70);
        card_y.setDuration(2500);
        card_y.setRepeatMode(ValueAnimator.REVERSE);
        card_y.setRepeatCount(ValueAnimator.INFINITE);
        card_y.setInterpolator(new LinearInterpolator());

        animationSet.play(card_y);


        animationSet.start();



        ListMuseesPicasso.downloadImg(getApplicationContext(),img, imgd);
        texte.setText(titre);
        descrip.setText(contenu);


        System.out.println(contenu+"htrertyi");








    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
