package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class analysing_animation extends AppCompatActivity {

    int age,weight,height,temperature,pulse,sugar;
    String dosageprediction,feverprediction,pulseprediction,sugarprediction,emailid,patientname;
    String return_prediction;
    public ArrayList<Integer> prediction_values = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_analysing_animation);
        age = getIntent().getIntExtra("age",0);
        weight = getIntent().getIntExtra("weight",0);
        height = getIntent().getIntExtra("height",0);
        temperature = getIntent().getIntExtra("temperature",0);
        pulse = getIntent().getIntExtra("pulse",0);
        sugar =getIntent().getIntExtra("sugar",0);
        emailid = getIntent().getStringExtra("emailid");
        patientname = getIntent().getStringExtra("patientname");

        prediction_values.add(age);
        prediction_values.add(temperature);
        prediction_values.add(age);
        prediction_values.add(pulse);
        prediction_values.add(age);
        prediction_values.add(sugar);
        prediction_values.add(height);
        prediction_values.add(weight);

        background process = new background();
        process.start();




        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                preanimate();
            }
        },10000);

    }

    class background extends Thread{
        @Override
        public void run() {
            return_prediction = new herokuapicall().apicall(prediction_values);

            //if return_prediction == unable to predict ; redirect to the temperature detection; else
            Log.d("iot_data_check", "onCreate: "+return_prediction);

            try {
                JSONObject object = new JSONObject(return_prediction);
                dosageprediction = object.getString("dosage_prediction");
                feverprediction = object.getString("fever_prediction");
                pulseprediction = object.getString("pulse_prediction");
                sugarprediction = object.getString("sugar_prediction");


            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.d("this is the prediction", "online_prediction: "+dosageprediction+feverprediction+pulseprediction+sugarprediction);

        }
    }


    public void preanimate(){
        Intent animate = new Intent(this,Symptoms.class);
        animate.putExtra("predicteddose",dosageprediction);
        animate.putExtra("predictedfever",feverprediction);
        animate.putExtra("temperature",temperature);
        animate.putExtra("emailid",emailid);
        animate.putExtra("patientname",patientname);
        startActivity(animate);
        finish();
    }
}