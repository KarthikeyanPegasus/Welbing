package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class appointmenthome extends AppCompatActivity {

    TextView drname,drrating,drspec,drtime,drstatus,drhospital;
    ProgressBar onestar,twostar,threestar,fourstar,fivestar;
    RecyclerView Reviewtext;
    DoctorReviewadapter drreadapter;
    String Drname,emailid;

    String doctorspeciality, doctoravail, doctorhospital, doctorstaus;
    String ones,twos,threes,fours,fives;
    int total, p1,p2,p3,p4,p5,max;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointmenthome);


        drname = findViewById(R.id.Drname);
        drrating = findViewById(R.id.drratings);
        drspec = findViewById(R.id.dbspeciality);
        drtime = findViewById(R.id.dbavailability);
        drstatus = findViewById(R.id.dbworkingstatus);
        drhospital = findViewById(R.id.dbhospitalname);


        onestar = findViewById(R.id.onestar);
        twostar = findViewById(R.id.twostar);
        threestar = findViewById(R.id.threestar);
        fourstar = findViewById(R.id.fourstar);
        fivestar = findViewById(R.id.fivestar);

        Reviewtext = findViewById(R.id.doctorreviews);

        Drname = getIntent().getStringExtra("drname");
        drname.setText(Drname);
        emailid = getIntent().getStringExtra("emailid");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Doctor");
        DatabaseReference Doctordetails = reference.child(Drname);

        Doctordetails.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            doctorspeciality = dataSnapshot.child("speciality").getValue(String.class);
            doctoravail = dataSnapshot.child("Time").getValue(String.class);
            doctorstaus = dataSnapshot.child("Status").getValue(String.class);
            doctorhospital = dataSnapshot.child("Hospital").getValue(String.class);

            drspec.setText(doctorspeciality);
            drtime.setText(doctoravail);
            drstatus.setText(doctorstaus);
            drhospital.setText(doctorhospital);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        DatabaseReference ratings = Doctordetails.child("Ratings");

        ratings.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ones = dataSnapshot.child("One").getValue(String.class);
                twos = dataSnapshot.child("Two").getValue(String.class);
                threes = dataSnapshot.child("Three").getValue(String.class);
                fours = dataSnapshot.child("Four").getValue(String.class);
                fives = dataSnapshot.child("Five").getValue(String.class);



                total = Integer.parseInt(ones)+Integer.parseInt(twos)+Integer.parseInt(threes)+Integer.parseInt(fours)+Integer.parseInt(fives);

                p1 = (Integer.parseInt(ones)*100/total);
                p2 = (Integer.parseInt(twos)*100/total);
                p3 = (Integer.parseInt(threes)*100/total);
                p4 = (Integer.parseInt(fours)*100/total);
                p5 = (Integer.parseInt(fives)*100/total);


                drrating.setText(""+total);

                onestar.setProgress(p1);
                twostar.setProgress(p2);
                threestar.setProgress(p3);
                fourstar.setProgress(p4);
                fivestar.setProgress(p5);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        LinearLayoutManager manager = new LinearLayoutManager(this);
        Reviewtext.setLayoutManager(manager);



        DatabaseReference reviews = Doctordetails.child("Reviews");
        reviews.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<doctorreviewmodel> models = new ArrayList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                   String name = ds.getKey();
                   String reviews = ds.getValue(String.class);

                    doctorreviewmodel one = new doctorreviewmodel(name,reviews);

                    models.add(one);
                }
                drreadapter = new DoctorReviewadapter(models);
                Reviewtext.setAdapter(drreadapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }



    public void appointmentdetails(View view) {

        Intent intent = new Intent(this,appointmentdetails.class);
        intent.putExtra("drname",Drname);
        intent.putExtra("emailid",emailid);
        startActivity(intent);
    }
}