package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class IOT_signin extends AppCompatActivity {

    String username;
    String emailid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_i_o_t_signin);
        emailid = getIntent().getStringExtra("mailid");
        username = getIntent().getStringExtra("username");
    }

    public void IOTnext(View view) {
        Intent pagecarousal = new Intent(getApplicationContext(),Pagecarousel_Introduction.class);
        pagecarousal.putExtra("username",username);
        pagecarousal.putExtra("mailid",emailid);
        startActivity(pagecarousal);
        finish();
    }
}