package com.example.zombiblio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainListener obs = new MainListener(this);


        Button module1 = (Button) findViewById(R.id.module1);

        module1.setOnClickListener(obs);
    }
}
