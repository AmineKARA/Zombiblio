package com.example.zombiblio;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;
import android.content.Context;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Module1 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module1);

        ImageView question = (ImageView) findViewById(R.id.question);

        //question.setBackgroundColor(0xFF00FF00);
        Picasso.get() // Context
                .load("https://img.favpng.com/0/17/19/computer-icons-software-testing-icon-design-png-favpng-u2kV0w1yAF5YhWvyFE8T6Fmxm.jpg") // URL or file
                .into(question); // An ImageView object to show the loaded image

    }
}