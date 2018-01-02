package sim.espritmobile.com.histoiretunisie;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import sim.espritmobile.com.histoiretunisie.Experience.AddExperienceActivity;
import sim.espritmobile.com.histoiretunisie.Experience.ExperienceActivity;
import sim.espritmobile.com.histoiretunisie.Experience.ExperiencesUsersActivity;

/**
 * Created by sarra on 19/12/2016.
 */

public class ChoseExperienceActivity extends AppCompatActivity {
    Button btn,btn1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose_experience);
        Toolbar topToolBar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(topToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        btn=(Button)findViewById(R.id.show);
      //  btn1=(Button)findViewById(R.id.add);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ExperienceActivity.class));

            }
        });

       /* btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddExperienceActivity.class));

            }
        });*/

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(getApplicationContext(), SigninActivity.class));

            FirebaseAuth.getInstance().signOut();
        }
        if(id == R.id.action_refresh){
            startActivity(new Intent(getApplicationContext(), AddExperienceActivity.class));

            Toast.makeText(ChoseExperienceActivity.this, "Add Experience ", Toast.LENGTH_LONG).show();
        }

        if(id==R.id.home)
        { onBackPressed();
            return true;

        }



        return super.onOptionsItemSelected(item);

    }




}
