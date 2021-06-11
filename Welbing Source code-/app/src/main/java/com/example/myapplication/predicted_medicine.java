package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class predicted_medicine extends AppCompatActivity {

    public ArrayList<String> morning = new ArrayList<>();
    public ArrayList<String> Afternoon = new ArrayList<>();
    public ArrayList<String> night = new ArrayList<>();
    public ArrayList<String> avoid = new ArrayList<>();
    public ArrayList<String> take = new ArrayList<>();
    public ArrayList<String> tm1 = new ArrayList<>();
    public ArrayList<String> tm2 = new ArrayList<>();
    public ArrayList<String> disease = new ArrayList<>();
    StringBuilder diseases = new StringBuilder();

    String emailid,patientname,patientnames;
    int head = 0,nose=0,body=0,stomach=0,mouth=0,weak=0,loose=0,feverish=1;
    TextView morningtxt, afternoontext, nighttext, foodtotake, foodtoavoid, totalmedicine,patient_name;


    String mor="",aft="",ngt="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_predicted_medicine);
        morning = getIntent().getStringArrayListExtra("morning");
        Afternoon = getIntent().getStringArrayListExtra("afternoon");
        night = getIntent().getStringArrayListExtra("night");
        tm1 = getIntent().getStringArrayListExtra("totalmedicine1");
        tm2 = getIntent().getStringArrayListExtra("totalmedicine2");
        avoid = getIntent().getStringArrayListExtra("avoid");
        take = getIntent().getStringArrayListExtra("take");
        head = getIntent().getIntExtra("head",0);
        nose = getIntent().getIntExtra("nose",0);
        body = getIntent().getIntExtra("body",0);
        stomach = getIntent().getIntExtra("stomach",0);
        mouth = getIntent().getIntExtra("mouth",0);
        weak = getIntent().getIntExtra("weak",0);
        loose = getIntent().getIntExtra("loose",0);
        feverish = getIntent().getIntExtra("feverish",0);

        background process = new background();
        process.start();

        emailid = getIntent().getStringExtra("emailid");
        patientname = getIntent().getStringExtra("patientname");
        patientnames = patientname;

        morningtxt = findViewById(R.id.morningtablets);
        afternoontext = findViewById(R.id.Afternoontablets);
        nighttext = findViewById(R.id.nighttablets);
        foodtotake = findViewById(R.id.take);
        foodtoavoid  = findViewById(R.id.avoid);
        totalmedicine = findViewById(R.id.dosage);
        patient_name = findViewById(R.id.patient_name);
        patient_name.setText(patientname);

        StringBuilder morningtab = new StringBuilder();
        for (String s : morning){
            morningtab.append(s + "\n");
            mor = mor+" "+s;
        }
        morningtxt.setText(morningtab.toString());



        StringBuilder afternoontab = new StringBuilder();
        for (String s: Afternoon){
            afternoontab.append(s + "\n");
            aft = aft+" "+s;
        }
        afternoontext.setText(afternoontab.toString());


        StringBuilder nighttab = new StringBuilder();
        for (String s: night){
            nighttab.append(s+"\n");
            ngt = ngt + " "+s;
        }
        nighttext.setText(nighttab.toString());

        StringBuilder foodavoid = new StringBuilder();
        for (String s: avoid){
            foodavoid.append(s+"  ");
        }
        foodtoavoid.setText(foodavoid.toString());

        StringBuilder foodtake = new StringBuilder();
        for (String s: take){
            foodtake.append(s + "   ");
        }
        foodtotake.setText(foodtake.toString());

        StringBuilder totalmedicinetabs = new StringBuilder();
        for (String s: tm1){
            totalmedicinetabs.append(s+"\n");
        }
        for (String s: tm2){
            totalmedicinetabs.append(s+"\n");
        }
        totalmedicine.setText(totalmedicinetabs.toString());




    }

    public void home(View view) {

        finish();
    }

    class background extends Thread{
        @Override
        public void run() {
            if (feverish == 0){disease.add("fever");}
            if (head == 1){disease.add("headache");}
            if (nose == 2){disease.add("cold");}
            if (body == 3){disease.add("bodypain");}
            if (stomach == 4){disease.add("stomachpain");}
            if (mouth == 5){disease.add("nausea");}
            if (weak == 6){disease.add("dizziness");}
            if (loose == 7){disease.add("diarrhoa");}


            for (String S:disease){diseases.append(S+"  ");}
            long millisInDay = 60 * 60 * 24 * 1000;
            long currentTime = new Date().getTime();
            long dateOnly = (currentTime / millisInDay) * millisInDay;
            Date clearDate = new Date(dateOnly);

            String today = clearDate.toString();

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Accountdetails");
            DatabaseReference refemailid = reference.child(emailid);
            DatabaseReference familymember = refemailid.child("familymember");
            DatabaseReference patient = familymember.child(patientnames);
            DatabaseReference medicaldetails = patient.child("medicaldetail");
            DatabaseReference recentmedicines = medicaldetails.child("Recentmedicine");
            recentmedicines.child("date").setValue(today);
            recentmedicines.child("disease").setValue(diseases.toString());
            recentmedicines.child("morningtablet").setValue(mor);
            recentmedicines.child("afternoontablet").setValue(aft);
            recentmedicines.child("nighttab").setValue(ngt);
            recentmedicines.child("status").setValue("uncured");

            DatabaseReference totalmedicalhistory = medicaldetails.child("history");
            DatabaseReference todaysdate = totalmedicalhistory.child(today);
            todaysdate.child("disease").setValue(diseases.toString());
            todaysdate.child("morningtablet").setValue(mor);
            todaysdate.child("afternoontablet").setValue(aft);
            todaysdate.child("nighttab").setValue(ngt);
            todaysdate.child("status").setValue("uncured");
            todaysdate.child("cureddate").setValue("uncured");






            DatabaseReference refrecentill = refemailid.child("Recent");
            refrecentill.child("patientname").setValue(patientname);
            refrecentill.child("disease").setValue(diseases.toString());
            refrecentill.child("status").setValue("uncured");




        }
    }
}