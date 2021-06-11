package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class settings_home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_settings_home);
    }

    public void settingsalarm(View view) {
        Intent alarm = new Intent(this, settings_Alarm.class);
        startActivity(alarm);
        finish();
    }

    public void settingsaddress(View view) {
        Intent address = new Intent(this , Settings_address.class);
        startActivity(address);
        finish();
    }

    public void editiotdevices(View view) {
        Intent iotdevices =  new Intent(this, settings_editdevices.class);
        startActivity(iotdevices);
        finish();
    }

    public void signout(View view) {
        Intent signoutsettings = new Intent(this, settings_signout.class);
        startActivity(signoutsettings);
        finish();
    }

    public void close(View view) {
        finish();
    }
}