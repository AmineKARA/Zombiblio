package com.example.zombiblio;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class GetQuestion extends AsyncTask {
    @Override
    protected Object doInBackground(Object[] objects) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        Log.d("test: ", "test");
        try {
            URL url = new URL("https://zombiblio.000webhostapp.com/connect.php");
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

                Log.d("Value", separated[z]);

                if(separated[z]!="end_req"){


                }


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
