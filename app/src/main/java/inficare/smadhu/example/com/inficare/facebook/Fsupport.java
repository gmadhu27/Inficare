package inficare.smadhu.example.com.inficare.facebook;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import inficare.smadhu.example.com.inficare.R;

public class Fsupport extends AppCompatActivity {
    TextView textView;
    ImageView im;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fsupport);
        textView= (TextView) findViewById(R.id.textt);
        im= (ImageView) findViewById(R.id.imgg);
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        if(user !=null){
            String name=user.getDisplayName();
            Uri uri=user.getPhotoUrl();
           // String uid=user.getUid();
            //String email=user.getEmail();
            //String ph=user.getPhoneNumber();
            textView.setText(name);
            //Picasso.with(Fsupport.this).load("https://graph.facebook.com/" + name+ "/picture?type=large").into(im);

           Picasso.with(Fsupport.this).load(uri).into(im);
        }
        else {
            goLoginScreen();
        }
    }

    private void goLoginScreen() {
        Intent intent=new Intent(this,Facebook.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    public void Logout(View view){
        FirebaseAuth.getInstance().signOut();
        LoginManager.getInstance().logOut();
        goLoginScreen();
    }
    }

