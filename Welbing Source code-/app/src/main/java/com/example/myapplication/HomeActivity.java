package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import androidx.viewpager2.widget.ViewPager2;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.utils.Utils;

import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import  java.util.*;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;

public class HomeActivity extends AppCompatActivity implements familymemberAdapter.OnItemClickListener {

    private static final String EXTRA_MEMBERPROFILE = "IMAGEURL";
    final List<Model> models = new ArrayList<>();
    private Adapter adapter;
    private familymemberAdapter familymemberAdapters;
    private  String Title = "Error title";
    private String Description= "Error desc";
    CardView progressing;
    List<familymembermodel> familymembermodels = new ArrayList<>();
    String emailid,status;
    public static String patientname,disease;
    ImageView profile;

    String usernaming,dbicon;
    TextView usernamefromdb;
    ViewPager2 homecarouselViewpager;
    ViewPager2 familymembercarousel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_home);
        usernamefromdb = findViewById(R.id.usernamefromdb);
        String username = getIntent().getStringExtra("usernamefromdatbase");
        emailid = getIntent().getStringExtra("mailid");
        progressing = findViewById(R.id.progressing);
        homecarouselViewpager = findViewById(R.id.homemaincarousel);;
        familymembercarousel = findViewById(R.id.familymembercarousel);
        profile = findViewById(R.id.profile);
        if (emailid == null){
            emailid = getIntent().getStringExtra("emailid");
            emailid =emailid.replace('.',',');
        }
        if (username == null){
            username = getIntent().getStringExtra("username");

        }



        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Accountdetails");
        final DatabaseReference user = reference.child(emailid);
        user.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    dbicon = dataSnapshot.child("icon").getValue(String.class);
                    if (dbicon.equals("1")){profile.setImageResource(R.drawable.m1);}
                    else if (dbicon.equals("2")){profile.setImageResource(R.drawable.m2);}
                    else if (dbicon.equals("3")){profile.setImageResource(R.drawable.m3);}
                    else if (dbicon.equals("4")){profile.setImageResource(R.drawable.f1);}
                    else if (dbicon.equals("5")){profile.setImageResource(R.drawable.f2);}
                    else if (dbicon.equals("6")){profile.setImageResource(R.drawable.f3);}
                    else{
                        profile.setImageResource(R.drawable.m2);
                    }
                }
                catch (Exception e){

                }


                if (dataSnapshot.hasChild("Recent")){
                    DatabaseReference recent = user.child("Recent");
                    recent.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            patientname = dataSnapshot.child("patientname").getValue(String.class);
                            disease = dataSnapshot.child("disease").getValue(String.class);
                            Model dp = new Model();
                            dp.setBarchart(View.VISIBLE);
                            dp.setConfirmationl(View.INVISIBLE);
                            List<DataEntry> seriesData = new ArrayList<>();
                            seriesData.add(new CustomDataEntry("sep", 17.982, 10.941, 9.835, 4.047, 2.841));
                            seriesData.add(new CustomDataEntry("oct", 17.574, 8.659, 6.230, 2.627, 2.242));
                            seriesData.add(new CustomDataEntry("dec", 19.75, 10.35, 6.292, 3.595, 2.136));
                            seriesData.add(new CustomDataEntry("jan", 30.6, 17.2, 16.1, 5.4, 5.2));
                            dp.setIndiastat(seriesData);
                            Model sp = new Model();
                            sp.setTopic(patientname);
                            sp.setCardbackground(R.drawable.bgwhite);
                            sp.setDescription(disease);
                            sp.setBarchart(View.INVISIBLE);
                            sp.setTextcolor(R.color.bodyColor);
                            sp.setHidewatermark(View.INVISIBLE);

                            models.add(sp);
                            models.add(dp);
                            adapter = new Adapter(models);

                            progressing.setVisibility(View.INVISIBLE);
                            homecarouselViewpager.setAdapter(adapter);


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                else{

                    patientname = dataSnapshot.child("patientname").getValue(String.class);
                    disease = dataSnapshot.child("disease").getValue(String.class);
                    Model dp = new Model();
                    dp.setBarchart(View.VISIBLE);
                    dp.setConfirmationl(View.INVISIBLE);
                    List<DataEntry> seriesData = new ArrayList<>();
                    seriesData.add(new CustomDataEntry("feb", 17.982, 10.941, 9.835, 4.047, 2.841));
                    seriesData.add(new CustomDataEntry("march", 17.574, 8.659, 6.230, 2.627, 2.242));
                    seriesData.add(new CustomDataEntry("april", 19.75, 10.35, 6.292, 3.595, 2.136));
                    seriesData.add(new CustomDataEntry("may", 30.6, 17.2, 16.1, 5.4, 5.2));
                    dp.setIndiastat(seriesData);
                    Model sp = new Model();
                    sp.setTopic(patientname);
                    sp.setCardbackground(R.drawable.bgwhite);
                    sp.setDescription(disease);
                    sp.setBarchart(View.INVISIBLE);
                    sp.setTextcolor(R.color.bodyColor);
                    sp.setHidewatermark(View.INVISIBLE);


                    models.add(dp);
                    adapter = new Adapter(models);
                    progressing.setVisibility(View.INVISIBLE);
                    homecarouselViewpager.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });






        setuphomecarousel();







        user.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                usernaming = dataSnapshot.child("username").getValue(String.class);
                usernamefromdb.setText(usernaming);
                usernamefromdb.setAllCaps(true);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        final DatabaseReference familymember = user.child("familymember");

        ValueEventListener eventListener =new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                familymembermodel member[] = new familymembermodel[7];
                int i = 0;
                for(DataSnapshot ds : dataSnapshot.getChildren()) {

                    member[i] = new familymembermodel();
                    member[i].setName(ds.getKey());
                    member[i].setOnclick(1);
                    member[i].setIcon(emailid);
                    familymembermodels.add(member[i]);
                    if (i==0){
                        user.child("icon").setValue(member[i].getIcon());
                    }
                    i = i+1;

                }

                familymemberAdapters = new familymemberAdapter(familymembermodels);
                familymemberAdapters.setOnItemClickListener(HomeActivity.this);
                familymembercarousel.setAdapter(familymemberAdapters);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        familymember.addValueEventListener(eventListener);







}


    public static String getCountry(){
        Locale locale = Locale.getDefault();
        String country = String.valueOf(locale.getCountry());
        return country.toLowerCase();
    }




    public void setuphomecarousel(){

























    }



    public void aboutforward(View view) {

    }

    public void showqr(View view) {

    }

    public void editprofile(View view) {


    }

    public void starttreatment(View view) {
        Intent starttreatment = new Intent(this,selectfamilymember.class);
        starttreatment.putExtra("emailid",emailid);
        startActivity(starttreatment);;
       

    }

    public void more(View view) {
    }

    public void nearbypharmacy(View view) {

        Intent pharmacy = new Intent(this, mapFindPharmacy.class);
        startActivity(pharmacy);
    }

    public void certification(View view) {
    }

    public void emergency(View view) {
    }

    public void nearbyhospital(View view) {

        Intent hospital = new Intent(this, Map_find_hospital.class);
        startActivity(hospital);
    }

    public void bookappointment(View view) {
        Intent intent = new Intent(this,BookandAppointment.class);
        intent.putExtra("emailid",emailid);
        startActivity(intent);
    }

    public void invitefrnds(View view) {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "this is where we have to add the body contents";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Welbing");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    @Override
    public void OnItemClick(int position) {
        Intent familymemberstat = new Intent(this, com.example.myapplication.familymemberstat.class);
        familymembermodel clickedItem = familymembermodels.get(position);

        familymemberstat.putExtra("name",clickedItem.getName());
        familymemberstat.putExtra("emailid",emailid);

        startActivity(familymemberstat);
    }

    public void settings(View view) {
        Intent settingshome = new Intent(this, settings_home.class);
        startActivity(settingshome);
    }

    public void addfamilmember(View view) {
        Intent addmember = new Intent(this,addfamilymember.class);
        addmember.putExtra("mailid",emailid);
        startActivity(addmember);

    }




    public void uncured(View view) {
    }

    public void cured(View view) {



        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Accountdetails");
        final DatabaseReference refemailid = reference.child(emailid);

        final DatabaseReference user = reference.child(emailid);
        user.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("Recent")){
                    final DatabaseReference recent = user.child("Recent");
                    recent.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            patientname = dataSnapshot.child("patientname").getValue(String.class);
                            disease = dataSnapshot.child("disease").getValue(String.class);
                            DatabaseReference family = refemailid.child("familymember");
                            DatabaseReference patient = family.child(patientname);
                            DatabaseReference mdetail = patient.child("medicaldetail");
                            DatabaseReference recentd = mdetail.child("Recentmedicine");
                            recentd.child("status").setValue("cured");
                            final DatabaseReference hist = recent.child("history");
                            recentd.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    String date = dataSnapshot.child("date").getValue(String.class);
                                    DatabaseReference maindate = hist.child(date);
                                    long millisInDay = 60 * 60 * 24 * 1000;
                                    long currentTime = new Date().getTime();
                                    long dateOnly = (currentTime / millisInDay) * millisInDay;
                                    Date clearDate = new Date(dateOnly);

                                    String today = clearDate.toString();

                                    maindate.child("cureddate").setValue(today);
                                    maindate.child("status").setValue("cured");
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

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
        });


        refemailid.child("Recent").removeValue();

        finish();
        startActivity(getIntent());






    }

    public void refresh(View view) {
        startActivity(getIntent());
    }


    private class CustomDataEntry extends ValueDataEntry {
        CustomDataEntry(String x, Number value, Number value2, Number value3, Number value4, Number value5) {
            super(x, value);
            setValue("value2", value2);
            setValue("value3", value3);
            setValue("value4", value4);
            setValue("value5", value5);
        }
    }


}