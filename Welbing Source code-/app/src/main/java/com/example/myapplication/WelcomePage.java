package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;

public class WelcomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_welcome_page);
    }

    public void WelcomeNext(View view) {
        Intent intent;
        final View imglogo3 = findViewById(R.id.icon);
        final View nametransition = findViewById(R.id.appname);

        intent = new Intent(this, Login_form.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, Pair.create(nametransition, "nametransition"),
                Pair.create(imglogo3, "logo3"));
        startActivity(intent, options.toBundle());
    }
}