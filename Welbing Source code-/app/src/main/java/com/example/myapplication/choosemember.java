package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class choosemember extends AppCompatActivity {
    RecyclerView selectname;
    List<String> name;
    choosememberadapter adapter;
    String drname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_selectfamilymember);
        selectname = findViewById(R.id.newrecyclerview);
        drname = getIntent().getStringExtra("drname");

        name = new ArrayList<>();

        name.add("kakashi");
        name.add("naruto");
        name.add("sasuke");
        name.add("sakura");
        name.add("kakashi");
        name.add("naruto");
        name.add("sasuke");
        name.add("sakura");
        name.add("kakashi");
        name.add("naruto");
        name.add("sasuke");
        name.add("sakura");

        adapter = new choosememberadapter(name, drname,this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        selectname.setLayoutManager(gridLayoutManager);
        selectname.setAdapter(adapter);
    }
}