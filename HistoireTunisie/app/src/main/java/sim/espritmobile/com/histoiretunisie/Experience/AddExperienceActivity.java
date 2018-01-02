package sim.espritmobile.com.histoiretunisie.Experience;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Random;

import sim.espritmobile.com.histoiretunisie.R;
import sim.espritmobile.com.histoiretunisie.SigninActivity;

/**
 * Created by sarra on 25/12/2016.
 */

public class AddExperienceActivity extends AppCompatActivity {
EditText name;
    EditText image;
    EditText desc;
    Button addButton;
  // private static final String FIREBASE_URL = "https://histoiretunisie-fa10e.firebaseio.com/experiences";

    private Firebase mRootRef;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_experience);
        name=(EditText)findViewById(R.id.name);
        image=(EditText)findViewById(R.id.image);
        desc=(EditText)findViewById(R.id.desc);

        addButton=(Button)findViewById(R.id.btnAdd);
        Toolbar topToolBar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(topToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Firebase.setAndroidContext(this);
        mRootRef = new Firebase("https://histoiretunisie-fa10e.firebaseio.com/experiences");
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Experience experienc=new Experience();
                experienc.setName(name.getText().toString());
                experienc.setImage(image.getText().toString());
                experienc.setDescription(desc.getText().toString());

                mRootRef.child("-KXkI1yX-FgaABkk-jSJ").setValue(experienc);
            }
        });
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


        if(id==R.id.home)
        { onBackPressed();
            return true;

        }



        return super.onOptionsItemSelected(item);

    }
















}
