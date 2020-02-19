package com.example.zombiblio;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;

public class ModuleListener implements View.OnClickListener {

    public Activity context;

    public ModuleListener (Activity c){

        this.context = c;
    }

    @Override
    public void onClick(View v) {


        if(v.getId() == R.id.retour){
            this.context.finish();
        }
    }
}
