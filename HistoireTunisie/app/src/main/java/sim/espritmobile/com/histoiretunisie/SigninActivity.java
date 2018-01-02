package sim.espritmobile.com.histoiretunisie;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.os.Bundle;
import android.support.percent.PercentLayoutHelper;
import android.support.percent.PercentRelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import sim.espritmobile.com.histoiretunisie.Experience.AddExperienceActivity;
import sim.espritmobile.com.histoiretunisie.Experience.ExperiencesUsersActivity;

/**
 * Created by sarra on 07/12/2016.
 */

public class SigninActivity extends AppCompatActivity {
    private TextView tvSignupInvoker;
    private LinearLayout llSignup;
    private TextView tvSigninInvoker;
    private LinearLayout llSignin;
    private Button btnSignup;
    private Button btnSignin;
    private TextView info;
    ImageButton backaccueil;
    private LoginButton loginButton;
    CallbackManager callbackManager;

    private Button connect;
    private EditText email,password,emaillog,passwordlog;
    private TextView register;


    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



       //start fb login
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.menu_layout);
        firebaseAuth = FirebaseAuth.getInstance();



        info = (TextView)findViewById(R.id.info);
        loginButton = (LoginButton)findViewById(R.id.login_button);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                info.setText(
                        "User ID: "
                                + loginResult.getAccessToken().getUserId()
                                + "\n" +
                                "Auth Token: "
                                + loginResult.getAccessToken().getToken()
                );
                startActivity(new Intent(getApplicationContext(), ChoseExperienceActivity.class));

            }

            @Override
            public void onCancel() {
                info.setText("Login attempt canceled.");
            }

            @Override
            public void onError(FacebookException error) {
                info.setText("Login attempt failed.");
            }
        });

        //end fb login










        tvSignupInvoker = (TextView) findViewById(R.id.tvSignupInvoker);
        tvSigninInvoker = (TextView) findViewById(R.id.tvSigninInvoker);

        btnSignup= (Button) findViewById(R.id.btnSignup);
        btnSignin=(Button)findViewById(R.id.btnSignin);
        //btnSignin= (Button) findViewById(R.id.btnSignin);

        llSignup = (LinearLayout) findViewById(R.id.llSignup);
        llSignin = (LinearLayout) findViewById(R.id.llSignin);



        //Edit text
        email=(EditText)findViewById(R.id.input_emaill);
        password=(EditText)findViewById(R.id.input_passwordd);

        emaillog=(EditText)findViewById(R.id.input_email);
        passwordlog=(EditText)findViewById(R.id.input_password);

        tvSignupInvoker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSignupForm();
            }
        });












        tvSigninInvoker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSigninForm();
            }
        });
//        showSigninForm();


        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation clockwise= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_right_to_left);
                btnSignup.startAnimation(clockwise);
                registerUser();
            }
        });
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Animation clockwise= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_right_to_left);
               // btnSignup.startAnimation(clockwise);
                loginUser();
            }
        });

        //connexion





        backaccueil=(ImageButton)findViewById(R.id.backaccueil);

        backaccueil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Accueil.class));
            }
        });






    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }


    public void loginUser() {
        String login = emaillog.getText().toString().trim();
        String pass = passwordlog.getText().toString().trim();

        if (login.isEmpty()) {
            Toast.makeText(this, "Vérifier votre Login", Toast.LENGTH_LONG);
        }
        if (pass.isEmpty()) {
            Toast.makeText(this, "Vérifier votre mot de passe", Toast.LENGTH_LONG);
        }

        //progressDialog.setMessage("Connection en cours...");
       // progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(login, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    startActivity(new Intent(getApplicationContext(), ChoseExperienceActivity.class));
                    System.out.println( "authentificate user...");
                } else {
                    System.out.println("utilisateur introuvable ");
                }

            }
        });

    }


    private void registerUser(){

        //getting email and password from edit texts
        String em = email.getText().toString().trim();
        String pass  = password.getText().toString().trim();

        //checking if email and passwords are empty
        if(TextUtils.isEmpty(em)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(pass)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }

        //if the email and password are not empty
        //displaying a progress dialog

       // progressDialog.setMessage("Registering Please Wait...");
        //progressDialog.show();

        //creating a new user
        firebaseAuth.createUserWithEmailAndPassword(em, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if(task.isSuccessful()){
                            //display some message here
                            startActivity(new Intent(getApplicationContext(),ExperiencesUsersActivity.class) );

                            Toast.makeText(SigninActivity.this,"Successfully registered",Toast.LENGTH_LONG).show();
                        }else{
                            //display some message here
                            Toast.makeText(SigninActivity.this,"Registration Error",Toast.LENGTH_LONG).show();
                        }
                       // progressDialog.dismiss();
                    }
                });

    }





































    private void showSignupForm() {
        PercentRelativeLayout.LayoutParams paramsLogin = (PercentRelativeLayout.LayoutParams) llSignin.getLayoutParams();
        PercentLayoutHelper.PercentLayoutInfo infoLogin = paramsLogin.getPercentLayoutInfo();
        infoLogin.widthPercent = 0.15f;
        llSignin.requestLayout();


        PercentRelativeLayout.LayoutParams paramsSignup = (PercentRelativeLayout.LayoutParams) llSignup.getLayoutParams();
        PercentLayoutHelper.PercentLayoutInfo infoSignup = paramsSignup.getPercentLayoutInfo();
        infoSignup.widthPercent = 0.85f;
        llSignup.requestLayout();

        tvSignupInvoker.setVisibility(View.GONE);
        tvSigninInvoker.setVisibility(View.VISIBLE);
        Animation translate= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.translate_right_to_left);
        llSignup.startAnimation(translate);

        Animation clockwise= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_right_to_left);
        btnSignup.startAnimation(clockwise);

    }


    private void showSigninForm() {
        PercentRelativeLayout.LayoutParams paramsLogin = (PercentRelativeLayout.LayoutParams) llSignin.getLayoutParams();
        PercentLayoutHelper.PercentLayoutInfo infoLogin = paramsLogin.getPercentLayoutInfo();
        infoLogin.widthPercent = 0.85f;
        llSignin.requestLayout();


        PercentRelativeLayout.LayoutParams paramsSignup = (PercentRelativeLayout.LayoutParams) llSignup.getLayoutParams();
        PercentLayoutHelper.PercentLayoutInfo infoSignup = paramsSignup.getPercentLayoutInfo();
        infoSignup.widthPercent = 0.15f;
        llSignup.requestLayout();

        Animation translate= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.translate_left_to_right);
        llSignin.startAnimation(translate);

        tvSignupInvoker.setVisibility(View.VISIBLE);
        tvSigninInvoker.setVisibility(View.GONE);
        Animation clockwise= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_left_to_right);
//        btnSignin.startAnimation(clockwise);
    }




}
