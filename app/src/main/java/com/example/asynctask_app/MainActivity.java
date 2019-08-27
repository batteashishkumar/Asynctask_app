package com.example.asynctask_app;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    String strurl="https://www.google.com";
    TextView text_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text_id=(TextView)findViewById(R.id.text);
        System.out.println("ashish");
        new A().execute();
    }

    public class A extends AsyncTask<String,String,String>
    {
        String value;
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(MainActivity.this,"Data fetched"+value,Toast.LENGTH_LONG).show();
            text_id.setText(value);
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url =new URL(strurl);
                HttpURLConnection con=(HttpURLConnection)url.openConnection();
                con.setRequestMethod("GET");
                con.connect();

                BufferedReader br =new BufferedReader(new InputStreamReader(con.getInputStream()));
                value=br.readLine();
            }
            catch (Exception e) {
                System.out.println(e);
            }
            return null;
        }
    }



}
