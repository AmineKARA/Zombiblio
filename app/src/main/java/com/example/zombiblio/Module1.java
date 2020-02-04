package com.example.zombiblio;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;
import android.content.Context;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Module1 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module1);

        ImageView question = (ImageView) findViewById(R.id.question);

        new GetQuestion().execute();

        new CountDownTimer(60000, 1000) {

            TextView counter = (TextView)  findViewById(R.id.counter);

            public void onTick(long millisUntilFinished) {
                counter.setText("Temps restant: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                counter.setText("done!");
            }
        }.start();

        //question.setBackgroundColor(0xFF00FF00);
        //Picasso.get() // Context
               // .load("https://img.favpng.com/0/17/19/computer-icons-software-testing-icon-design-png-favpng-u2kV0w1yAF5YhWvyFE8T6Fmxm.jpg")
               // .into(question);

    }
}