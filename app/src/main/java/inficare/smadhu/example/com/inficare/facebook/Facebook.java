package inficare.smadhu.example.com.inficare.facebook;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

import inficare.smadhu.example.com.inficare.R;
import inficare.smadhu.example.com.inficare.facebook.Fsupport;

public class Facebook extends AppCompatActivity {
    LoginButton loginButton;
    TextView textView;
    //ImageView im;
    CallbackManager callbackManager;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseauthlis;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook);
        progressBar= (ProgressBar) findViewById(R.id.progressBar1);
        loginButton = (LoginButton) findViewById(R.id.login_button);
        //  textView = (TextView) findViewById(R.id.textt);
        //im = (ImageView) findViewById(R.id.imgg);
        callbackManager = CallbackManager.Factory.create();
        loginButton.setReadPermissions(Arrays.asList("email"));
        loginButton.setReadPermissions(Arrays.asList("pages_messaging_phone_number"));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
               handlefacebookToken(loginResult.getAccessToken());
            }
            @Override
            public void onCancel() {
                textView.setText("cancle");
            }
            @Override
            public void onError(FacebookException error) {
            }
        });
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseauthlis=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=firebaseAuth.getCurrentUser();
                if(user!=null){
                   /* String name=user.getDisplayName();
                    String uid=user.getUid();
                    String email=user.getEmail();
                   // Picasso.with(MainActivity.this).load("https://graph.facebook.com/" + name+ "/picture?type=large").into(im);
                    textView.setText(name +email +uid);*/
                     //gootherActivity();
                }
            }
        };
    }
    private void handlefacebookToken(AccessToken accessToken) {
        progressBar.setVisibility(View.VISIBLE);
        loginButton.setVisibility(View.GONE);
        AuthCredential credential= FacebookAuthProvider.getCredential(accessToken.getToken());
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"error firebase",Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.VISIBLE);
                loginButton.setVisibility(View.GONE);
                gootherActivity();


            }
        });
    }
    private void gootherActivity() {
        Intent intent=new Intent(this,Fsupport.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }
    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(firebaseauthlis);
    }
    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.addAuthStateListener(firebaseauthlis);
    }

}


