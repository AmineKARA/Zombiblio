package com.example.zombiblio;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Module5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();



        SharedPreferences prefs = getApplicationContext().getSharedPreferences("Time", MODE_PRIVATE);


        /* GESTION AVANCEMENT */

        SharedPreferences.Editor editor = getApplicationContext().getSharedPreferences("Time", MODE_PRIVATE).edit();


        if(prefs.getInt("module5_win",0)==1){
            setContentView(R.layout.module5p6);
            delete_item();
        }


        if(!prefs.contains("m5_page")){
            editor.putInt("m5_page", 0);
            editor.apply();
        }


        if(prefs.getInt("m5_page",0)==0){
            setContentView(R.layout.module5);

        }

        if(prefs.getInt("m5_page",0)==1){
            setContentView(R.layout.module5p2);
        }

        if(prefs.getInt("m5_page",0)==2){
            setContentView(R.layout.module5p3);
        }

        if(prefs.getInt("m5_page",0)==3){
            setContentView(R.layout.module5p4);
        }

        if(prefs.getInt("m5_page",0)==4){
            setContentView(R.layout.module5p5);
        }
        if(prefs.getInt("m5_page",0)==5){
            setContentView(R.layout.module5p6);
        }
        /*--------------------*/


        /*GESTION VALIDATION CLIQUE*/

        Button valide = (Button)  findViewById(R.id.valider);
        valide.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {




                SharedPreferences prefs = getApplicationContext().getSharedPreferences("Time", MODE_PRIVATE);

                SharedPreferences.Editor editor = getApplicationContext().getSharedPreferences("Time", MODE_PRIVATE).edit();
                editor.putInt("m5_page", prefs.getInt("m5_page",0)+1);
                editor.apply();

                if(prefs.getInt("m5_page",0)==6){

                    editor.putInt("module5_win", 1);
                    editor.apply();
                }

                finish();
                startActivity(getIntent());

            }
        });

        /*-------------------------*/


        //GESTION CLIQUE RETOUR
        ModuleListener obs = new ModuleListener(this);
        Button retour = (Button) findViewById(R.id.retour);
        retour.setOnClickListener(obs);
        /*---------------------------*/

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
                Intent perdu = new Intent(Module5.this , Perdu.class);
                Module5.this.startActivity(perdu);
                //counter.setText("done!");
            }
        }.start();
    }

    public void delete_item() {

        TextView text = (TextView) findViewById (R.id.texte);
        Button valider = (Button) findViewById(R.id.valider);
        ImageView image = (ImageView) findViewById(R.id.imageView3);
        image.setImageResource(R.drawable.gagne);


        valider.setVisibility(View.GONE);
        text.setVisibility(View.GONE);

    }
}
