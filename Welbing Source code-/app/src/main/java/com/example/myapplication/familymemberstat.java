package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class familymemberstat extends AppCompatActivity {

    String name,emailid;
    TextView membernames , age,height,weight,total,serious;
    String mage,mheight,mweight;
    String dbicon;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_familymemberstat);
        name = getIntent().getStringExtra("name");
        emailid = getIntent().getStringExtra("emailid");


        membernames = findViewById(R.id.membername);
        age = findViewById(R.id.age);
        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);
        image = findViewById(R.id.memberprofile);
        membernames.setAllCaps(true);
        membernames.setText(name);


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Accountdetails");
        DatabaseReference user = reference.child(emailid);
        DatabaseReference familymembers = user.child("familymember");
        DatabaseReference membername = familymembers.child(name);
        DatabaseReference healthdetails = membername.child("healthdetails");
        DatabaseReference medicaldetails = membername.child("medicaldetail");

        healthdetails.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                mage = dataSnapshot.child("age").getValue(String.class);
                mheight = dataSnapshot.child("height").getValue(String.class);
                mweight = dataSnapshot.child("weight").getValue(String.class);
                dbicon = dataSnapshot.child("icon").getValue(String.class);
                if (dbicon.equals("1")){image.setImageResource(R.drawable.m1);}
                else if (dbicon.equals("2")){ image.setImageResource(R.drawable.m2);}
                else if (dbicon.equals("3")){ image.setImageResource(R.drawable.m3);}
                else if (dbicon.equals("4")){ image.setImageResource(R.drawable.f1);}
                else if (dbicon.equals("5")){ image.setImageResource(R.drawable.f2);}
                else if (dbicon.equals("6")){ image.setImageResource(R.drawable.f3);}
                else{
                    image.setImageResource(R.drawable.m2);
                }
                image.setVisibility(View.VISIBLE);
                age.setText(mage);
                height.setText(mheight);
                weight.setText(mweight);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });







    }

    public void recentprescribed(View view) {

        Intent intent = new Intent(this,recentmedicines.class);
        intent.putExtra("name",name);
        intent.putExtra("emailid",emailid);
        startActivity(intent);

    }

    public void booktheappointment(View view) {
        Intent intent = new Intent(this,BookandAppointment.class);
        intent.putExtra("emailid",emailid);
        startActivity(intent);
    }

    public void home(View view) {
        this.finish();
    }

    public void settings(View view) {
        Intent settings = new Intent(this,settings_home.class);
        startActivity(settings);
    }
}