package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class settings_signout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_settings_signout);
    }

    public void alarms(View view) {
        Intent alarm = new Intent(this, settings_Alarm.class);
        startActivity(alarm);
        finish();
    }

    public void addresses(View view) {
        Intent address = new Intent(this, Settings_address.class);
        startActivity(address);
        finish();
    }

    public void editdevices(View view) {
        Intent editdevice = new Intent(this, settings_editdevices.class);
        startActivity(editdevice);
        finish();
    }

    public void close(View view) {
        finish();
    }
}