package sim.espritmobile.com.histoiretunisie;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import sim.espritmobile.com.histoiretunisie.Experience.ExperiencesUsersActivity;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button valider;
    private EditText email,password,name;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        firebaseAuth = FirebaseAuth.getInstance();
        valider = (Button) findViewById(R.id.valider);
        email = (EditText) findViewById(R.id.maill);
        password = (EditText) findViewById(R.id.passw);
        valider.setOnClickListener(this);
    }


    public void registerUser(){
        String login= email.getText().toString().trim();
        String pass=password.getText().toString().trim();
        System.out.println( login);
        System.out.println( pass);


        if (login==null) {
            Toast.makeText(this, "Vérifier votre Login", Toast.LENGTH_LONG);
        }
        if (pass==null) {
            Toast.makeText(this, "Vérifier votre mot de passe", Toast.LENGTH_LONG);
        }



        firebaseAuth.createUserWithEmailAndPassword(login,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    startActivity(new Intent(getApplicationContext(),ExperiencesUsersActivity.class) );
                    System.out.println( "create user...");
                }
                else {
                    System.out.println("introuvable");
                }
            }
        });

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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.valider:
                registerUser();
                break;
    }
}
}
