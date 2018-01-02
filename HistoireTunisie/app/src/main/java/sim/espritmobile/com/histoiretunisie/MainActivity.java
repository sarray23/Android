package sim.espritmobile.com.histoiretunisie;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private int progStatut=0;
    private Handler handler= new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageView imgback=(ImageView)findViewById(R.id.backgroundimg);
        RotateAnimation anim = new RotateAnimation(0.0f, 360.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setInterpolator(new LinearInterpolator());
        anim.setRepeatCount(Animation.INFINITE);
        anim.setDuration(1300);
        imgback.startAnimation(anim);

        progressBar = (ProgressBar) findViewById(R.id.progressBarr);
        progStatut=2;
        progressBar.setProgress(progStatut);


        progressBar.getProgressDrawable().setColorFilter(
                Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progStatut < 100) {
                    progStatut++;
                    // .System.out.println(progStatut);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //System.out.println( "runprog"+progStatut);
                            progressBar.setProgress(progStatut);
                        }
                    });
                    try {
                        Thread.sleep(60);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


                Intent intent = new Intent(getApplicationContext(), Accueil.class);
                startActivity(intent);
                finish();
            }
        }).start();
    }
}
