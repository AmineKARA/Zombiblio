package com.example.zombiblio;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Update extends AppCompatActivity {

    public class GetFiliere extends AsyncTask {

        SharedPreferences sharedPreferences;
        public GetFiliere(SharedPreferences sharedPreferences)
        {
            this.sharedPreferences= sharedPreferences;
        }

        @Override
        protected String[] doInBackground(Object[] objects) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            Log.d("FILIERE: ", "test");
            try {
                URL url = new URL("https://zombiblioevry.000webhostapp.com/script_php_android/get_filiere.php");
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();


                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";
                int i=0;

                while ((line = reader.readLine()) != null) {

                    buffer.append(line+"\n");
                    //Log.d("Response ", "> " + line);   //here u ll get whole response...... :-)
                }

                String[] separated = buffer.toString().split("/");

                int cle_requete = 0, cle_valeur =0;

                String[][] arr_questions;

                for (int z=0; z<separated.length; z++){

                    //Log.d("Value", separated[z]);
                }

                return separated;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }



    }
}
