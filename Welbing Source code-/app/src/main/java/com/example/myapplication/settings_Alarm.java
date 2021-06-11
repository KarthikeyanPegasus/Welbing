package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;

public class settings_Alarm extends AppCompatActivity {

    TimePicker morning;
    TimePicker afternoon;
    TimePicker evening;
    String morningtime, afternoontime, eveningtime;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_settings__alarm);

        morning = findViewById(R.id.morningpicker);
        afternoon = findViewById(R.id.afternoonpicker);
        evening = findViewById(R.id.eveningpicker);

        morning.setIs24HourView(false);
        afternoon.setIs24HourView(false);
        evening.setIs24HourView(false);

        morning.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hour, int min) {

                morningtime = Integer.toString(hour) + ':' + Integer.toString(min) ;

            }
        });
        afternoon.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hour, int min) {
                afternoontime =Integer.toString(hour) + ':' + Integer.toString(min) ;
            }
        });
        evening.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hour, int min) {
                eveningtime = Integer.toString(hour) + ':' + Integer.toString(min) ;
            }
        });
    }

    public void save(View view) {
    }

    public void address(View view) {
        Intent address = new Intent(this,Settings_address.class);
        startActivity(address);
        finish();

    }

    public void editdevice(View view) {
        Intent editdevices = new Intent(this,settings_editdevices.class);
        startActivity(editdevices);
        finish();
    }

    public void signout(View view) {
        Intent signouts  = new Intent(this, settings_signout.class);
        startActivity(signouts);
        finish();
    }

    public void close(View view) {
        finish();
    }
}