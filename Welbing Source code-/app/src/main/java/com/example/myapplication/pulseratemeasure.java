package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class pulseratemeasure extends AppCompatActivity {


    String emailid,patientname;
    int age,weight,height,temperature,pulse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_pulseratemeasure);
        age = getIntent().getIntExtra("age",0);
        weight = getIntent().getIntExtra("weight",0);
        height = getIntent().getIntExtra("height",0);
        temperature = getIntent().getIntExtra("temperature",0);
        emailid = getIntent().getStringExtra("emailid");
        patientname = getIntent().getStringExtra("patientname");

        pulse = 56;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                measureoxyge();
            }
        },2000);
    }
    public void measureoxyge(){
        Intent measureoxygen = new Intent(this, com.example.myapplication.measureoxygen.class);
        measureoxygen.putExtra("age",age);
        measureoxygen.putExtra("weight",weight);
        measureoxygen.putExtra("height",height);
        measureoxygen.putExtra("temperature",temperature);
        measureoxygen.putExtra("pulse",pulse);
        measureoxygen.putExtra("emailid",emailid);
        measureoxygen.putExtra("patientname",patientname);
        startActivity(measureoxygen);
        finish();
    }
}