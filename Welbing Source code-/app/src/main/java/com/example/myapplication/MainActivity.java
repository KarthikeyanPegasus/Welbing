package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;

import java.text.BreakIterator;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main);




        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                animation();
            }
        },500);


    }
    private void animation(){
        Intent intent;
        final View imglogo1 = findViewById(R.id.logo1);
        final View imglogo2 = findViewById(R.id.logo2);
        final View imglogo3 = findViewById(R.id.logo3);
        intent = new Intent(this, loadinganimation1.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, Pair.create(imglogo1, "logo1"),
                Pair.create(imglogo2, "logo2"), Pair.create(imglogo3, "logo3"));
        startActivity(intent, options.toBundle());
    }
}