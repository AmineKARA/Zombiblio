package com.example.zombiblio;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class IntroListener implements View.OnClickListener {


    public Activity context;
    public int[] myDrawIntro;
    public int id;
    public ImageView intro;


    public IntroListener (Activity c, int i, int[] a, ImageView in){

        this.context = c;
        this.id =i;
        this.myDrawIntro=a;
        this.intro=in;
    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.suiv){

            if(this.id<7){
                //ECRAN D'INTRO SUIVANT
                Toast.makeText(context, "Page d'introduciton suivante", Toast.LENGTH_LONG).show();
                ImageView intro =(ImageView) this.intro.findViewById(R.id.image);
                intro.setImageResource(this.myDrawIntro[id+1]);
                this.id++;
            }else{
                //ACCES AU MENU DES MODULES
                Toast.makeText(context, "Redirection vers les modules", Toast.LENGTH_LONG).show();
                Intent moduleMenu = new Intent(this.context , ModuleMenu.class);
                this.context.startActivity(moduleMenu);
            }

        }

        if(view.getId() == R.id.prec){

            if(this.id!=0) {
                //ECRAN D'INTRO PRECEDENT
                Toast.makeText(context, "Retour à la page précedente", Toast.LENGTH_LONG).show();
                ImageView intro = (ImageView) this.intro.findViewById(R.id.image);
                intro.setImageResource(this.myDrawIntro[id - 1]);
                this.id--;
            }else{
                //RETOUR A L'ACCEUIL
                Toast.makeText(context, "Retour à l'acceuil", Toast.LENGTH_LONG).show();
                Intent acceuil = new Intent(this.context , MainActivity.class);
                this.context.startActivity(acceuil);
            }
        }

        if(view.getId() == R.id.fin){

            //ACCES AU MENU DES MODULES
            Toast.makeText(context, "Redirection vers les modules", Toast.LENGTH_LONG).show();
            Intent moduleMenu = new Intent(this.context , ModuleMenu.class);
            this.context.startActivity(moduleMenu);
        }

    }
}
