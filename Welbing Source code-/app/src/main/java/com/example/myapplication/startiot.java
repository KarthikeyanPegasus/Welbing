package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class startiot extends AppCompatActivity {

    String patientname,emailid;
    String age,weight,height,temperature;
    int agei,weighti,heighti,temperaturei,position;
    DatabaseReference patientdetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_startiot);

        patientname = getIntent().getStringExtra("patientname");
        emailid = getIntent().getStringExtra("emailid");
        position = getIntent().getIntExtra("postion",0);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Accountdetails");
        DatabaseReference user = reference.child(emailid);
        final DatabaseReference familymember = user.child("familymember");
        familymember.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i = 0;
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    if (i == position){
                        String patientname = ds.getKey();
                        patientdetails = familymember.child(patientname);

                        DatabaseReference healthdetails = patientdetails.child("healthdetails");
                        healthdetails.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                age = dataSnapshot.child("age").getValue(String.class);
                                weight = dataSnapshot.child("weight").getValue(String.class);
                                height = dataSnapshot.child("height").getValue(String.class);
                                temperature = "108";

                                agei = Integer.parseInt(age);
                                weighti = Integer.parseInt(weight);
                                heighti = Integer.parseInt(height);
                                temperaturei = Integer.parseInt(temperature);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                    }
                    i++;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pulserate();
            }
        },2000);



    }

    public void pulserate(){
        Intent pulserate = new Intent(this, pulseratemeasure.class);
        pulserate.putExtra("age",agei);
        pulserate.putExtra("weight",weighti);
        pulserate.putExtra("height",heighti);
        pulserate.putExtra("temperature",temperaturei);
        pulserate.putExtra("emailid",emailid);
        pulserate.putExtra("patientname",patientname);
        startActivity(pulserate);
        finish();
    }
}