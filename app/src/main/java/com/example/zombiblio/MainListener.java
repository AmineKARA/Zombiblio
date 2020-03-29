package com.example.zombiblio;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;



public class MainListener implements View.OnClickListener {

    public Activity context;

    public MainListener (Activity c){

        this.context = c;
    }

    @Override
    public void onClick(View v) {


        if(v.getId() == R.id.imageModule1){

            Intent intentMod1 = new Intent(this.context , Module1.class);
            this.context.startActivity(intentMod1);
        }
        if(v.getId() == R.id.imageButton2){

            Intent intentMod1 = new Intent(this.context , Module2.class);
            this.context.startActivity(intentMod1);
        }
        if(v.getId() == R.id.imagem4){

            Intent intentMod1 = new Intent(this.context , Module4.class);
            this.context.startActivity(intentMod1);
        }

        if(v.getId() == R.id.imagem5){

            Intent intentMod1 = new Intent(this.context , Module5.class);
            this.context.startActivity(intentMod1);
        }


        if(v.getId() == R.id.start){


            Intent intentMod1 = new Intent(this.context , Intro.class);


            this.context.startActivity(intentMod1);
        }

    }
}

