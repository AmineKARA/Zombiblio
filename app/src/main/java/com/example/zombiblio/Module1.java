package com.example.zombiblio;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;
import android.content.Context;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Module1 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        setContentView(R.layout.module1);

        ImageView question = (ImageView) findViewById(R.id.question);

        //new GetQuestion().execute();


        //GESTION CLIQUE RETOUR
        ModuleListener obs = new ModuleListener(this);
        Button retour = (Button) findViewById(R.id.retour);
        retour.setOnClickListener(obs);


        SharedPreferences prefs = getApplicationContext().getSharedPreferences("Time", MODE_PRIVATE);
        long milis = prefs.getLong("milis", 0); //0 is the default value.

        new CountDownTimer(milis, 1000) {

            TextView counter = (TextView)  findViewById(R.id.timer);

            public void onTick(long millisUntilFinished) {
                //counter.setText("Temps restant: " + millisUntilFinished / 1000);

                String text = String.format(Locale.getDefault(), "%02d:%02d",
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) % 60,
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60);
                counter.setText(text);


            }

            public void onFinish() {
                //counter.setText("done!");
            }
        }.start();

    }

    protected void getSharedPref(){

        SharedPreferences prefs = getApplicationContext().getSharedPreferences("Time", MODE_PRIVATE);
        long milis = prefs.getLong("milis", 0); //0 is the default value.

        Log.d("SHARED","Value :"+milis);
    }
}