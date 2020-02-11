package com.example.zombiblio;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Intro extends AppCompatActivity {

    public int[] myDrawIntro;
    public int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.myDrawIntro  = setIntroArray();

        this.id = 0;
        setContentView(R.layout.intro_histoire);

        ImageView intro =(ImageView) findViewById(R.id.image);


        intro.setImageResource(this.myDrawIntro[id]);

        IntroListener obs = new IntroListener(this, this.id, this.myDrawIntro, intro);

        Button suivant = (Button) findViewById(R.id.suiv);
        suivant.setOnClickListener(obs);

        Button preced = (Button) findViewById(R.id.prec);
        preced.setOnClickListener(obs);

        Button fin = (Button) findViewById(R.id.fin);
        fin.setOnClickListener(obs);

    }

    private int[] setIntroArray(){

        int[] values = new int[]{R.drawable.intro_1, R.drawable.intro_2,R.drawable.intro_3, R.drawable.intro_4,
                R.drawable.intro_5, R.drawable.intro_6,R.drawable.intro_7, R.drawable.intro_8};

        return values;
    }

    public void updateImage(){

    }
}

