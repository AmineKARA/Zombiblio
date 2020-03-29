package com.example.zombiblio;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("Time", MODE_PRIVATE);
        prefs.edit().clear().commit();

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        setContentView(R.layout.intro);

        MainListener obs = new MainListener(this);


        Button start_button = (Button) findViewById(R.id.start);


        start_button.setOnClickListener(obs);




    }

    @Override
    protected void onStart() {
        super.onStart();
        String str, str_question;
        GetFiliere test = new GetFiliere();


        if(isNetworkAvailable()){
            try {
                test.execute().get();

                str = test.getResponseMsg();

                Log.d("Value", str);

                SharedPreferences.Editor editor = getApplicationContext().getSharedPreferences("Time", MODE_PRIVATE).edit();
                editor.putString("filiere", str);
                editor.apply();

                ecrireFichier("filiere.txt", str);

            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String filieres= lireFichier("filiere.txt");
            String[] separated = filieres.split("/");


            /*MODULE 1*/
            for(int i=0;i<separated.length-1;i++) {


                GetQuestion question = new GetQuestion(separated[i]);

                try {
                    question.execute().get();


                    str_question = question.getResponseMsg();

                    //Log.d("Question", str_question);
                    ecrireFichier(separated[i]+"_module1.txt", str_question);


                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        /*MODULE 2*/
            for(int i=0;i<separated.length-1;i++) {


                GetQuestionModuleD question_m2 = new GetQuestionModuleD(separated[i]);

                try {
                    question_m2.execute().get();


                    str_question = question_m2.getResponseMsg();

                    Log.d("Question", str_question);
                    ecrireFichier(separated[i]+"_module2.txt", str_question);


                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    public void ecrireFichier(String name, String value)
    {
        String contenu = value;

        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = openFileOutput(name, Context.MODE_PRIVATE);
            fileOutputStream.write(contenu.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null)
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
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