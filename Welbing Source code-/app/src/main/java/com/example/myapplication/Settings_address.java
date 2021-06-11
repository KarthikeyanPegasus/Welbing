package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Settings_address extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_settings_address);
    }

    public void alarms(View view) {
        Intent alarms = new Intent(this, settings_Alarm.class);
        startActivity(alarms);
        finish();
    }

    public void editdevices(View view) {
        Intent editdevice = new Intent(this, settings_editdevices.class);
        startActivity(editdevice);
        finish();
    }

    public void signouts(View view) {
        Intent signout = new Intent(this,settings_signout.class);
        startActivity(signout);
        finish();
    }

    public void save(View view) {
    }

    public void close(View view) {
        finish();
    }
}