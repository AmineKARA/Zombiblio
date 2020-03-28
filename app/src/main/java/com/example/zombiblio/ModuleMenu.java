package com.example.zombiblio;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.concurrent.TimeUnit;


public class ModuleMenu extends AppCompatActivity {

    public int time=8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        setContentView(R.layout.activity_main);

        MainListener obs = new MainListener(this);


        SharedPreferences prefs = getApplicationContext().getSharedPreferences("Time", MODE_PRIVATE);





        ImageButton module1 = (ImageButton) findViewById(R.id.imageModule1);
        module1.setOnClickListener(obs);


        String value = prefs.getString("filiere_selectionne", "");

        System.out.println(value);


        new CountDownTimer(900000000, 1000) {
            //new CountDownTimer(10000, 1000) {

            TextView counter = (TextView)  findViewById(R.id.timer);

            public void onTick(long millisUntilFinished) {
                counter.setText(""+millisUntilFinished / 1000);

                String text = String.format(Locale.getDefault(), "%02d:%02d",
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) % 60,
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60);
                counter.setText(text);


                SharedPreferences.Editor editor = getApplicationContext().getSharedPreferences("Time", MODE_PRIVATE).edit();
                editor.putLong("milis", millisUntilFinished);
                editor.apply();
                SharedPreferences prefs = getApplicationContext().getSharedPreferences("Time", MODE_PRIVATE);
                long milis = prefs.getLong("milis", 0); //0 is the default value.
                //Log.d("SHARED","Value :"+milis);

                ImageButton module1 = (ImageButton) findViewById(R.id.imageModule1);
                if(prefs.getInt("module1_win",0)==1){
                    module1.setImageResource(R.drawable.gagner_petit);
                }
            }

            public void onFinish() {
                Intent perdu = new Intent(ModuleMenu.this , Perdu.class);
                ModuleMenu.this.startActivity(perdu);
                //counter.setText("done!");
            }
        }.start();
    }




}
