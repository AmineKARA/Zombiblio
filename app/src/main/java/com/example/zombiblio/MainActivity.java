package com.example.zombiblio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        MainListener obs = new MainListener(this);


        ImageButton module1 = (ImageButton) findViewById(R.id.imageModule1);

        module1.setOnClickListener(obs);
    }
}