package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class measureoxygen extends AppCompatActivity {


    String emailid,patientname;
    int age,weight,height,temperature,pulse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_measureoxygen);

        age = getIntent().getIntExtra("age",0);
        weight = getIntent().getIntExtra("weight",0);
        height = getIntent().getIntExtra("height",0);
        temperature = getIntent().getIntExtra("temperature",0);
        pulse = getIntent().getIntExtra("pulse",0);
        emailid = getIntent().getStringExtra("emailid");
        patientname = getIntent().getStringExtra("patientname");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                glucomeasure();
            }
        },2000);
    }
    public void glucomeasure(){
        Intent glucomeasure = new Intent(this, glucosemeasure.class);
        glucomeasure.putExtra("age",age);
        glucomeasure.putExtra("weight",weight);
        glucomeasure.putExtra("height",height);
        glucomeasure.putExtra("temperature",temperature);
        glucomeasure.putExtra("pulse",pulse);
        glucomeasure.putExtra("emailid",emailid);
        glucomeasure.putExtra("patientname",patientname);

        startActivity(glucomeasure);
        finish();
    }
}