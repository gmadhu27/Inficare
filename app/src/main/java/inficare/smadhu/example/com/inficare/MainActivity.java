package inficare.smadhu.example.com.inficare;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import inficare.smadhu.example.com.inficare.facebook.Facebook;
import inficare.smadhu.example.com.inficare.quiz.Quiz;
import inficare.smadhu.example.com.inficare.quiz.TextQuizInput;

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
        AlertDialog.Builder alertbox = new AlertDialog.Builder(MainActivity.this);
        alertbox.setTitle("You have option to select quiz");
        alertbox.setCancelable(true);
        alertbox.setPositiveButton("Text Input", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MainActivity.this, TextQuizInput.class);
                startActivity(intent);
            }
        });
        alertbox.setNegativeButton("Select Option", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent inten = new Intent(MainActivity.this,Quiz.class);
                startActivity(inten);
            }
        });
        alertbox.show();
    }
}