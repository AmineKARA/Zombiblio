package com.example.zombiblio;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class ModuleMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainListener obs = new MainListener(this);


        ImageButton module1 = (ImageButton) findViewById(R.id.imageModule1);

        module1.setOnClickListener(obs);
    }
}
