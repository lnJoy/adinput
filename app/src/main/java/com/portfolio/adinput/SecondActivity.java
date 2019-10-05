package com.portfolio.adinput;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SecondActivity extends AppCompatActivity {

    EditText oEditTitle;
    EditText oEditAdex;
    EditText oEditYoutubeid;
    EditText oEditEmail;
    EditText oEditLongex;
    EditText oEditPrecautions;
    Button oButtonNext;

    private static String IP_ADDRESS = "104.198.84.89";
    private static String TAG = "PHP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        oEditTitle = (EditText) findViewById(R.id.title);
        oEditAdex = (EditText) findViewById(R.id.adex);
        oEditYoutubeid = (EditText) findViewById(R.id.youtubeid);
        oEditEmail = (EditText) findViewById(R.id.email);
        oEditLongex = (EditText) findViewById(R.id.longex);
        oEditPrecautions = (EditText) findViewById(R.id.precautions);

        oButtonNext = (Button) findViewById(R.id.next);
        oButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = oEditTitle.getText().toString();
                String adex = oEditAdex.getText().toString();
                String youtubeid = oEditYoutubeid.getText().toString();
                String email = oEditEmail.getText().toString();
                String longex = oEditLongex.getText().toString();
                String precautions = oEditPrecautions.getText().toString();

                SecondActivity.InsertData task = new SecondActivity.InsertData();
                task.execute("http://" + IP_ADDRESS + "/insert.php", title, adex, youtubeid, email, longex, precautions);

                oEditTitle.setText("");
                oEditAdex.setText("");
                oEditYoutubeid.setText("");
                oEditEmail.setText("");
                oEditLongex.setText("");
                oEditPrecautions.setText("");
            }
        });
    }

    private void moveToActivityTwo() {
        Intent intent = new Intent(SecondActivity.this, SecondActivity.class);
        startActivity(intent);
    }

    class InsertData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(SecondActivity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            Log.d(TAG, "POST response  - " + result);
        }


        @Override
        protected String doInBackground(String... params) {

            String title = (String)params[1];
            String adex = (String)params[2];
            String youtubeid = (String)params[3];
            String email = (String)params[4];
            String longex = (String)params[5];
            String precautions = (String)params[6];

            String serverURL = (String)params[0];
            String postParameters = "title=" + title + "&adex=" + adex + "&youtubeid=" + youtubeid + "&email=" + email + "&longex=" + longex + "&precautions" + precautions;


            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.connect();


                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();


                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "POST response code - " + responseStatusCode);

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line = null;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }


                bufferedReader.close();


                return sb.toString();


            } catch (Exception e) {

                Log.d(TAG, "InsertData: Error ", e);

                return new String("Error: " + e.getMessage());
            }

        }
    }


}

