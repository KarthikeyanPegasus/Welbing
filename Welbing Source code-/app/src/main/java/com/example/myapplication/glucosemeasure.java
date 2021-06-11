package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class glucosemeasure extends AppCompatActivity {
    String emailid,patientname;
    int age,weight,height,temperature,pulse,sugar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_glucosemeasure);
        age = getIntent().getIntExtra("age",0);
        weight = getIntent().getIntExtra("weight",0);
        height = getIntent().getIntExtra("height",0);
        temperature = getIntent().getIntExtra("temperature",0);
        pulse = getIntent().getIntExtra("pulse",0);
        sugar = 120;
        emailid =getIntent().getStringExtra("emailid");
        patientname = getIntent().getStringExtra("patientname");


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Analyse();
            }
        },2000);
    }
    public void Analyse(){
        Intent analysing = new Intent(this,analysing_animation.class);
        analysing.putExtra("age",age);
        analysing.putExtra("weight",weight);
        analysing.putExtra("height",height);
        analysing.putExtra("temperature",temperature);
        analysing.putExtra("pulse",pulse);
        analysing.putExtra("sugar",sugar);
        analysing.putExtra("emailid",emailid);
        analysing.putExtra("patientname",patientname);
        startActivity(analysing);
        finish();
    }
}