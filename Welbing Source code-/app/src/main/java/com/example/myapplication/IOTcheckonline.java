package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class IOTcheckonline extends AppCompatActivity {

    String patientname,emailid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_i_o_tcheckonline);
       patientname = getIntent().getStringExtra("patientname");
       emailid = getIntent().getStringExtra("emailid");
    }



    public void Reconfig(View view) {
        Intent reconfig = new Intent(this,IOT_signin.class);
        startActivity(reconfig);
    }

    public void Proceedcheckup(View view) {
        Intent preceedcheckup = new Intent(this, startiot.class);
        preceedcheckup.putExtra("patientname",patientname);
        preceedcheckup.putExtra("emailid",emailid);
        startActivity(preceedcheckup);
        finish();
    }
}