package com.example.zombiblio;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;
import android.content.Context;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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



        SharedPreferences prefs = getApplicationContext().getSharedPreferences("Time", MODE_PRIVATE);

        /*LECTURE DU FICHIER */

        String filieres= lireFichier(prefs.getString("filiere_selectionne", "")+"_module1.txt");
        String[] separated = filieres.split("//");
        for (int i=0; i<separated.length; i++){
            System.out.println(separated[i]);
        }

        /*--------------------------------*/



        /* GESTION AVANCEMENT */

        SharedPreferences.Editor editor = getApplicationContext().getSharedPreferences("Time", MODE_PRIVATE).edit();
        editor.putLong("nb_question", separated.length-2);
        editor.apply();


        if(prefs.getInt("module1_win",0)==1){
            delete_item();
        }


        if(!prefs.contains("module1_actual")){
            editor.putInt("module1_actual", 0);
            editor.apply();
        }

        /*--------------------*/



        /*GESTION DES TEXTVIEW*/

        TextView nbpage = (TextView) findViewById (R.id.nb_p);
        TextView nbmot = (TextView) findViewById (R.id.nb);
        TextView coteA = (TextView) findViewById (R.id.coteA);
        TextView coteB = (TextView) findViewById (R.id.coteB);
        TextView coteC = (TextView) findViewById (R.id.coteC);
        TextView coteD = (TextView) findViewById (R.id.coteD);
        TextView coteE = (TextView) findViewById (R.id.coteE);

        /*--------------------*/




        /*LECTURE SUITE*/
        String[] question_realise = separated[prefs.getInt("module1_actual",0)].split("/");
        editor.putString("questresp", question_realise[2]);
        editor.apply();
        nbpage.setText(question_realise[0]);
        nbmot.setText(question_realise[1]);
        for (int i=3; i<question_realise.length; i++){
            if(i==3){
                if(question_realise[3].equals("--")){

                }else{
                    coteA.setText(question_realise[3]);
                }
            }

            if(i==4) {
                if (question_realise[4].equals("--")) {

                } else {
                    coteB.setText(question_realise[4]);
                }

            }

            if(i==5) {
                if (question_realise[5].equals("--")) {

                } else {
                    coteC.setText(question_realise[5]);
                }
            }


            if(i==6) {
                if (question_realise[6].equals("--")) {

                } else {
                    coteD.setText(question_realise[6]);
                }
            }


            if(i==7) {
                if (question_realise[7].equals("--")) {

                } else {
                    coteE.setText(question_realise[7]);
                }

            }

        }


        /*-----------------------*/







        /*GESTION VALIDATION CLIQUE*/

        Button valide = (Button)  findViewById(R.id.valider);
        valide.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                EditText txtname = findViewById(R.id.reponse);
                String val =  txtname.getText().toString();
                SharedPreferences prefs = getApplicationContext().getSharedPreferences("Time", MODE_PRIVATE);

                if (prefs.getInt("module1_actual",0)< prefs.getLong("nb_question", 0)){
                    if(prefs.getString("questresp","").equals(val)){
                        SharedPreferences.Editor editor = getApplicationContext().getSharedPreferences("Time", MODE_PRIVATE).edit();
                        editor.putInt("module1_actual",(prefs.getInt("module1_actual",0)+1));
                        editor.apply();
                        finish();
                        startActivity(getIntent());
                    }
                }else{
                    SharedPreferences.Editor editor = getApplicationContext().getSharedPreferences("Time", MODE_PRIVATE).edit();
                    editor.putInt("module1_win",1);
                    editor.apply();
                    delete_item();
                }

                txtname.getText().clear();

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
                Intent perdu = new Intent(Module1.this , Perdu.class);
                Module1.this.startActivity(perdu);
                //counter.setText("done!");
            }
        }.start();

    }


    public void delete_item(){

        TextView nbpage = (TextView) findViewById (R.id.nb_p);
        TextView nbmot = (TextView) findViewById (R.id.nb);
        TextView coteA = (TextView) findViewById (R.id.coteA);
        TextView coteB = (TextView) findViewById (R.id.coteB);
        TextView coteC = (TextView) findViewById (R.id.coteC);
        TextView coteD = (TextView) findViewById (R.id.coteD);
        TextView coteE = (TextView) findViewById (R.id.coteE);
        Button valider = (Button) findViewById(R.id.valider);
        TextView text = (TextView) findViewById (R.id.textView4);
        EditText reponse = (EditText) findViewById (R.id.reponse);
        TextView page = (TextView) findViewById (R.id.numPage);
        TextView mot = (TextView) findViewById (R.id.numMot);
        ImageView image = (ImageView) findViewById(R.id.question);
        image.setImageResource(R.drawable.gagne);


        nbpage.setVisibility(View.GONE);
        nbmot.setVisibility(View.GONE);
        coteA.setVisibility(View.GONE);
        coteB.setVisibility(View.GONE);
        coteC.setVisibility(View.GONE);
        coteD.setVisibility(View.GONE);
        coteE.setVisibility(View.GONE);
        valider.setVisibility(View.GONE);
        text.setVisibility(View.GONE);
        reponse.setVisibility(View.GONE);
        page.setVisibility(View.GONE);
        mot.setVisibility(View.GONE);


    }



    public String lireFichier(String name)
    {
        FileInputStream fileInputStream = null;
        StringBuilder stringBuilder = null;

        try {
            fileInputStream = openFileInput(name);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            stringBuilder = new StringBuilder();
            String line;

            while((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line + "\n");
            }

            //Log.d("RES :", line.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null)
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

        if (stringBuilder != null) {
            System.out.println(stringBuilder);
            return String.valueOf(stringBuilder);
        }else
            System.err.println("stringBuilder est null");
        return "vide";
    }
}