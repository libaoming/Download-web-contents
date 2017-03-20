package com.lbm100.trycatch;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {


    public class  DownloadTask extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... params) {


            String result = "";
            URL url;
            HttpURLConnection httpURLConnection;

            try {
                url = new URL(params[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();

                InputStream in = httpURLConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();

                while (data != -1) {

                    char current = (char) data;
                    result += current;

                    data = reader.read();
                }

                return  result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return  "failed";

            } catch (IOException e) {
                e.printStackTrace();
                return  "failed";
            }


        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DownloadTask downloadTask = new DownloadTask();

        try {
            String result = downloadTask.execute("http://www.vinci.im").get();
            Log.i("Result",result);


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
}
