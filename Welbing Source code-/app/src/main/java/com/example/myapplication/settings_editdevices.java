package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class settings_editdevices extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_settings_editdevices);
    }

    public void alarms(View view) {
        Intent alarm = new Intent(this,settings_Alarm.class);
        startActivity(alarm);
        finish();
    }

    public void addresses(View view) {
        Intent address = new Intent(this, Settings_address.class);
        startActivity(address);
        finish();
    }

    public void signout(View view) {
        Intent signouts = new Intent(this, settings_signout.class);
        startActivity(signouts);
        finish();
    }

    public void close(View view) {
        finish();
    }
}