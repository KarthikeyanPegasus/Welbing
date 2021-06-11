package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class predicting_animation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_predicting_animation);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                predictmedicin();
            }
        },2000);
    }
    public void predictmedicin(){
        Intent premedicine = new Intent(this, predicted_medicine.class);
        startActivity(premedicine);
    }
}