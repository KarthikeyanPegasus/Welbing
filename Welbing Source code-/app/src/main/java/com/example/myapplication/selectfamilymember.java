package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class selectfamilymember extends AppCompatActivity {


    RecyclerView selectname;
    List<String> name;
    selectfamilymemberadapter adapter;
    String emailid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_selectfamilymember);
        selectname = findViewById(R.id.newrecyclerview);
        emailid = getIntent().getStringExtra("emailid");


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Accountdetails");
        DatabaseReference user = reference.child(emailid);
        final DatabaseReference familymember = user.child("familymember");

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                name = new ArrayList<>();


                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    name.add(ds.getKey());
                }

                adapter = new selectfamilymemberadapter(name,selectfamilymember.this,emailid);

                GridLayoutManager gridLayoutManager = new GridLayoutManager(selectfamilymember.this,2,GridLayoutManager.VERTICAL,false);
                selectname.setLayoutManager(gridLayoutManager);
                selectname.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        familymember.addValueEventListener(eventListener);



    }
}