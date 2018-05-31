package inficare.smadhu.example.com.inficare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void facebook(View view) {
        Intent i = new Intent(this, Facebook.class);
        startActivity(i);
    }

    public void bank(View view) {
        Intent i = new Intent(this, Bank.class);
        startActivity(i);
    }

    public void quiz(View view) {
        Intent i = new Intent(this, Quiz.class);
        startActivity(i);
    }
}