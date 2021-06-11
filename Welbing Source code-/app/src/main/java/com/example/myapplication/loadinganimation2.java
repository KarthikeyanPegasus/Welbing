package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;

public class loadinganimation2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_loadinganimation2);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                animation2();
            }
        },500);
    }
    private void animation2(){
        Intent intent;
        final View imglogo1 = findViewById(R.id.logo1);
        final View imglogo2 = findViewById(R.id.logo2);
        final View imglogo3 = findViewById(R.id.logo3);
        final View nametransition = findViewById(R.id.appname);

        intent = new Intent(this, WelcomePage.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, Pair.create(nametransition, "nametransition"),
                 Pair.create(imglogo3, "logo3"));
        startActivity(intent, options.toBundle());
    }
}