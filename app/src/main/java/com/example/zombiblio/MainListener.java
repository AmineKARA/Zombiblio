package com.example.zombiblio;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;



public class MainListener implements View.OnClickListener {

    public Activity context;

    public MainListener (Activity c){

        this.context = c;
    }

    @Override
    public void onClick(View v) {


        if(v.getId() == R.id.imageModule1){


            Toast.makeText(context, "Vous accèdez au module n°1", Toast.LENGTH_LONG).show();
            Intent intentMod1 = new Intent(this.context , Module1.class);
            this.context.startActivity(intentMod1);
        }
    }
}

