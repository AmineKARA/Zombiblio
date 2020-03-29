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

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Module2 extends AppCompatActivity {

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


        setContentView(R.layout.module2);


        SharedPreferences prefs = getApplicationContext().getSharedPreferences("Time", MODE_PRIVATE);


        /*LECTURE DU FICHIER */

        String filieres= lireFichier(prefs.getString("filiere_selectionne", "")+"_module2.txt");
        String[] separated = filieres.split("//");
        for (int i=0; i<separated.length; i++){
            System.out.println(separated[i]);
        }
        /*--------------------------------*/




        /* GESTION AVANCEMENT */

        SharedPreferences.Editor editor = getApplicationContext().getSharedPreferences("Time", MODE_PRIVATE).edit();
        editor.putLong("nb_question_m2", separated.length-2);
        editor.apply();


        if(prefs.getInt("module2_win",0)==1){
            delete_item();
        }


        if(!prefs.contains("module2_actual")){
            editor.putInt("module2_actual", 0);
            editor.apply();
        }

        /*--------------------*/



        /*GESTION DES TEXTVIEW*/

        TextView auteur = (TextView) findViewById (R.id.auteur);
        TextView titre = (TextView) findViewById (R.id.titre);

        /*--------------------*/



        /*LECTURE SUITE*/
        String[] question_realise = separated[prefs.getInt("module2_actual",0)].split("/");
        editor.putString("questresp_m2", question_realise[3]);
        editor.apply();
        auteur.setText(question_realise[0]);
        titre.setText(question_realise[1]);

        System.out.println(question_realise[3]);
        /*-----------------------*/


        /*GESTION VALIDATION CLIQUE*/

        Button valide = (Button)  findViewById(R.id.valider);
        valide.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                EditText txtname = findViewById(R.id.reponse);
                String val =  txtname.getText().toString();
                SharedPreferences prefs = getApplicationContext().getSharedPreferences("Time", MODE_PRIVATE);

                if (prefs.getInt("module2_actual",0)< prefs.getLong("nb_question_m2", 0)){
                    if(prefs.getString("questresp_m2","").equals(val)){
                        SharedPreferences.Editor editor = getApplicationContext().getSharedPreferences("Time", MODE_PRIVATE).edit();
                        editor.putInt("module2_actual",(prefs.getInt("module2_actual",0)+1));
                        editor.apply();
                        finish();
                        startActivity(getIntent());
                    }
                }else{
                    SharedPreferences.Editor editor = getApplicationContext().getSharedPreferences("Time", MODE_PRIVATE).edit();
                    editor.putInt("module2_win",1);
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
                Intent perdu = new Intent(Module2.this , Perdu.class);
                Module2.this.startActivity(perdu);
                //counter.setText("done!");
            }
        }.start();


    }

    public void delete_item() {

        TextView auteur = (TextView) findViewById (R.id.auteur);
        TextView titre = (TextView) findViewById (R.id.titre);
        TextView reponse = (TextView) findViewById (R.id.reponse);
        Button valider = (Button) findViewById(R.id.valider);
        ImageView image = (ImageView) findViewById(R.id.imageView2);
        image.setImageResource(R.drawable.gagne);


        valider.setVisibility(View.GONE);
        reponse.setVisibility(View.GONE);
        auteur.setVisibility(View.GONE);
        titre.setVisibility(View.GONE);

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
