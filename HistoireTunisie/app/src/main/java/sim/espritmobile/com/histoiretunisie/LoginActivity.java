package sim.espritmobile.com.histoiretunisie;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import sim.espritmobile.com.histoiretunisie.Experience.ExperiencesUsersActivity;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button connect;
    private EditText email,password;
    private TextView register;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth= FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
        connect=(Button) findViewById(R.id.connect);
        email=(EditText) findViewById(R.id.emaillogin);
        password=(EditText) findViewById(R.id.passwordlogin);
        register=(TextView) findViewById(R.id.register);
        connect.setOnClickListener(this);
        register.setOnClickListener(this);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    public void loginUser() {
        String login = email.getText().toString().trim();
        String pass = password.getText().toString().trim();

        if (login.isEmpty()) {
            Toast.makeText(this, "Vérifier votre Login", Toast.LENGTH_LONG);
        }
        if (pass.isEmpty()) {
            Toast.makeText(this, "Vérifier votre mot de passe", Toast.LENGTH_LONG);
        }

        progressDialog.setMessage("Connection en cours...");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(login, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    startActivity(new Intent(getApplicationContext(), ExperiencesUsersActivity.class));
                    System.out.println( "authentificate user...");
                } else {
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
           case R.id.connect:
               loginUser();
               break;
           case R.id.register:
               startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
               break;
       }

    }
}
