package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookandAppointment extends AppCompatActivity {


    doctornamelistadapter dcadapter;
    List<bookappointmentmodel> model = new ArrayList<>();
    RecyclerView recyclerView;
    String emailid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_bookand_appointment);
        recyclerView = findViewById(R.id.doctorrecyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        emailid = getIntent().getStringExtra("emailid");

        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Doctor");

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i = 0;
                model.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    final String doctorname = ds.getKey();
                    final String[] spec = new String[1];
                    final String[] time = new String[1];
                    DatabaseReference newreference = reference.child(doctorname);
                    newreference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            spec[0] = dataSnapshot.child("speciality").getValue(String.class);
                            time[0] = dataSnapshot.child("Time").getValue(String.class);

                            bookappointmentmodel bookingmodel = new bookappointmentmodel(doctorname,spec[0],time[0]);
                            model.add(bookingmodel);
                            dcadapter = new doctornamelistadapter(model,BookandAppointment.this,emailid);
                            recyclerView.setAdapter(dcadapter);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        reference.addValueEventListener(eventListener);


    }


}