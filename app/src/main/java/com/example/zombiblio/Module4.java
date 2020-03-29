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

public class Module4 extends AppCompatActivity {

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


        setContentView(R.layout.module4);

        SharedPreferences prefs = getApplicationContext().getSharedPreferences("Time", MODE_PRIVATE);



        /*GESTION VALIDATION CLIQUE*/

        Button valide = (Button)  findViewById(R.id.valider);
        valide.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                EditText etage = findViewById(R.id.etage);
                String val_etage =  etage.getText().toString();
                EditText livre = findViewById(R.id.nblivre);
                String val_livre =  livre.getText().toString();
                EditText semaine = findViewById(R.id.temps);
                String val_semaine =  semaine.getText().toString();
                SharedPreferences prefs = getApplicationContext().getSharedPreferences("Time", MODE_PRIVATE);


                if(val_etage.equals("0") && val_livre.equals("8") && val_semaine.equals("3")){

                    SharedPreferences.Editor editor = getApplicationContext().getSharedPreferences("Time", MODE_PRIVATE).edit();
                    editor.putInt("module4_win",1);
                    editor.apply();
                    delete_item();
                }


                etage.getText().clear();
                livre.getText().clear();
                semaine.getText().clear();

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
                Intent perdu = new Intent(Module4.this , Perdu.class);
                Module4.this.startActivity(perdu);
                //counter.setText("done!");
            }
        }.start();


    }


    public void delete_item() {

        TextView etage = (TextView) findViewById (R.id.etage);
        TextView nblivre = (TextView) findViewById (R.id.nblivre);
        TextView temps = (TextView) findViewById (R.id.temps);
        Button valider = (Button) findViewById(R.id.valider);
        ImageView image = (ImageView) findViewById(R.id.imgm3);
        image.setImageResource(R.drawable.gagne);


        valider.setVisibility(View.GONE);
        etage.setVisibility(View.GONE);
        nblivre.setVisibility(View.GONE);
        temps.setVisibility(View.GONE);

    }
}
