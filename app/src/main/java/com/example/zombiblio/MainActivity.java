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


        setContentView(R.layout.intro);

        MainListener obs = new MainListener(this);


        Button start_button = (Button) findViewById(R.id.start);

        start_button.setOnClickListener(obs);
    }
}