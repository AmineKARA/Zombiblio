package com.example.zombiblio;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class SelectFiliere extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.filiere);

        LinearLayout ll = (LinearLayout) findViewById(R.id.testbutton);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        String filieres= lireFichier("filiere.txt");
        String[] separated = filieres.split("/");


        for(int i=0;i<separated.length-1;i++){
            Button myButton = new Button(this);

            android.widget.LinearLayout.LayoutParams l = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,300); // 60 is height you can set it as u need
            l.leftMargin = 5;
            myButton.setLayoutParams(lp);
            myButton.setLayoutParams(l);
            myButton.setText(separated[i]);
            myButton.setHeight(200);
            myButton.setId(i);
            myButton.setTextSize(80);
            Typeface typeface = ResourcesCompat.getFont(this, R.font.shlop);
            myButton.setTypeface(typeface);

            int ColorText = Color.parseColor("#60F521");
            myButton.setTextColor(ColorText);


            int ColorValue = Color.parseColor("#673AB7");
            myButton.setBackgroundColor(ColorValue);
            ll.addView(myButton, lp);
            myButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button b = (Button)v;
                    String buttonText = b.getText().toString();
                    System.out.println(buttonText);

                    SharedPreferences.Editor editor = getApplicationContext().getSharedPreferences("Time", MODE_PRIVATE).edit();
                    editor.putString("filiere_selectionne", buttonText);
                    editor.apply();

                    Intent menu = new Intent(SelectFiliere.this , ModuleMenu.class);
                    SelectFiliere.this.startActivity(menu);
                }
            });
        }





    }

    public void startModule(){


    }


    public String lireFichier(String name)
    {
        FileInputStream fileInputStream = null;
        StringBuilder stringBuilder = null;

        try {
            fileInputStream = openFileInput(name);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            stringBuilder = new StringBuilder();
            String line;

            while((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line + "\n");
            }

            //Log.d("RES :", line.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null)
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

        if (stringBuilder != null) {
            System.out.println(stringBuilder);
            return String.valueOf(stringBuilder);
        }else
            System.err.println("stringBuilder est null");
            return "vide";
    }
}

