package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Symptoms extends AppCompatActivity {

    String dose,fever,emailid,patientname;
    Button headache,cold,bodypain,stomachpain,nausea,dizziness,diarrhoea,proceed;
    int head = 0,nose=0,body=0,stomach=0,mouth=0,weak=0,loose=0,temperature=0,dosage=1,feverish=1;
    public ArrayList<Integer> fever_prediction = new ArrayList<>();
    public ArrayList<Integer> headache_prediction = new ArrayList<>();
    public ArrayList<Integer> cold_prediction = new ArrayList<>();
    public ArrayList<Integer> bodypain_prediction = new ArrayList<>();
    public ArrayList<Integer> stomach_prediction = new ArrayList<>();
    public ArrayList<Integer> nausea_prediction = new ArrayList<>();
    public ArrayList<Integer> dizziness_prediction = new ArrayList<>();
    public ArrayList<Integer> diarrhoea_prediction = new ArrayList<>();
    public ArrayList<String> morning = new ArrayList<>();
    public ArrayList<String> Afternoon = new ArrayList<>();
    public ArrayList<String> night = new ArrayList<>();
    public ArrayList<String> avoid = new ArrayList<>();
    public ArrayList<String> take = new ArrayList<>();
    public ArrayList<String> tm1 = new ArrayList<>();
    public ArrayList<String> tm2 = new ArrayList<>();

    TextView hidedtext;
    ImageView hideimg;
    LottieAnimationView animation;
    String rn_fever_prediction = null,rn_headache_prediction= null,rn_cold_prediction= null,rn_bodypain_prediction= null,rn_stomachpain_prediction= null,rn_nausea_prediction= null,rn_dizziness_prediction= null,rn_diarrheoa_prediction= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_symptoms);
        headache = findViewById(R.id.headache);
        cold = findViewById(R.id.cold);
        bodypain = findViewById(R.id.bodypain);
        stomachpain = findViewById(R.id.stomachpain);
        nausea = findViewById(R.id.nausea);
        dizziness = findViewById(R.id.dizziness);
        diarrhoea = findViewById(R.id.diarrhoea);
        proceed = findViewById(R.id.proceed);
        hidedtext = findViewById(R.id.hideid);
        hideimg = findViewById(R.id.hidimg);
        animation = findViewById(R.id.animationview);
        dose = getIntent().getStringExtra("predicteddose");
        fever = getIntent().getStringExtra("predictedfever");
        temperature = getIntent().getIntExtra("temperature",0);
        emailid = getIntent().getStringExtra("emailid");
        patientname = getIntent().getStringExtra("patientname");


        hidedtext.setVisibility(View.INVISIBLE);
        hideimg.setVisibility(View.INVISIBLE);
        animation.setVisibility(View.INVISIBLE);
        String tst = dose+fever+temperature;
        Log.d("check element", "onCreate: "+tst);
        if (temperature >= 96.8){
            feverish = 0;
        }
        if (dose == "[\"adult\"]"){
            dosage =0;
        }

        Log.d("check element", "feverish: "+feverish+" dosage :"+dosage);
        headache.setOnClickListener(new View.OnClickListener() {
            int a=0;
            @Override
            public void onClick(View view) {
                if (a == 0){
                    head = 1;
                    a = 1;
                    headache.setBackgroundResource(R.drawable.bgyellowbtn);
                }
                else if (a == 1){
                    head = 0;
                    a = 0;
                    headache.setBackgroundResource(R.drawable.btn_selector);
                }
            }
        });
        cold.setOnClickListener(new View.OnClickListener() {
            int a=0;
            @Override
            public void onClick(View view) {
                if (a == 0){
                    nose = 2;
                    a = 1;
                    cold.setBackgroundResource(R.drawable.bgyellowbtn);
                }
                else if (a == 1){
                    nose = 0;
                    a = 0;
                    cold.setBackgroundResource(R.drawable.btn_selector);

                }
            }
        });
        bodypain.setOnClickListener(new View.OnClickListener() {
            int a=0;
            @Override
            public void onClick(View view) {
                if (a == 0){
                    body = 3;
                    a = 1;
                    bodypain.setBackgroundResource(R.drawable.bgyellowbtn);
                }
                else if (a == 1){
                    body = 0;
                    a = 0;
                    bodypain.setBackgroundResource(R.drawable.btn_selector);
                }
            }
        });
        stomachpain.setOnClickListener(new View.OnClickListener() {
            int a  = 0;
            @Override
            public void onClick(View view) {
                if (a == 0){
                    stomach = 4;
                    a = 1;
                    stomachpain.setBackgroundResource(R.drawable.bgyellowbtn);
                }
                else if (a == 1){
                    stomach = 0;
                    a = 0;
                    stomachpain.setBackgroundResource(R.drawable.btn_selector);
                }
            }
        });
        nausea.setOnClickListener(new View.OnClickListener() {
            int a  = 0 ;
            @Override
            public void onClick(View view) {
                if (a == 0){
                    mouth = 5;
                    a = 1;
                    nausea.setBackgroundResource(R.drawable.bgyellowbtn);
                }
                else if (a == 1){
                    mouth = 0;
                    a=0;
                    nausea.setBackgroundResource(R.drawable.btn_selector);
                }
            }
        });
        dizziness.setOnClickListener(new View.OnClickListener() {
            int a = 0 ;
            @Override
            public void onClick(View view) {
             if (a == 0){
                 weak = 6;
                 a = 1;
                 dizziness.setBackgroundResource(R.drawable.bgyellowbtn);
             }
             else if(a == 1){
                 weak = 0;
                 a = 0;
                 dizziness.setBackgroundResource(R.drawable.btn_selector);
             }
            }
        });
        diarrhoea.setOnClickListener(new View.OnClickListener() {
            int a = 0 ;
            @Override
            public void onClick(View view) {
                if (a == 0){
                    loose = 7;
                    a = 1;
                    diarrhoea.setBackgroundResource(R.drawable.bgyellowbtn);
                }
                else if(a == 1){
                    loose = 0;
                    a = 0;
                    diarrhoea.setBackgroundResource(R.drawable.btn_selector);
                }
            }
        });


        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backgroundprocess process = new backgroundprocess();
                process.start();
                hidedtext.setVisibility(View.VISIBLE);
                animation.setVisibility(View.VISIBLE);
                hideimg.setVisibility(View.VISIBLE);
                headache.setVisibility(View.INVISIBLE);
                cold.setVisibility(View.INVISIBLE);
                bodypain.setVisibility(View.INVISIBLE);
                stomachpain.setVisibility(View.INVISIBLE);
                nausea.setVisibility(View.INVISIBLE);
                dizziness.setVisibility(View.INVISIBLE);
                diarrhoea.setVisibility(View.INVISIBLE);
                proceed.setVisibility(View.INVISIBLE);

                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Intent predicted_medicines = new Intent(Symptoms.this, predicted_medicine.class);
                predicted_medicines.putExtra("morning",morning);
                predicted_medicines.putExtra("afternoon",Afternoon);
                predicted_medicines.putExtra("night",night);
                predicted_medicines.putExtra("totalmedicine1",tm1);
                predicted_medicines.putExtra("totalmedicine2",tm2);
                predicted_medicines.putExtra("avoid",avoid);
                predicted_medicines.putExtra("take",take);
                predicted_medicines.putExtra("emailid",emailid);
                predicted_medicines.putExtra("patientname",patientname);
                predicted_medicines.putExtra("head",head);
                predicted_medicines.putExtra("nose",nose);
                predicted_medicines.putExtra("body",body);
                predicted_medicines.putExtra("stomach",stomach);
                predicted_medicines.putExtra("mouth",mouth);
                predicted_medicines.putExtra("weak",weak);
                predicted_medicines.putExtra("loose",loose);
                predicted_medicines.putExtra("feverish",feverish);
                startActivity(predicted_medicines);
                finish();

            }



        });



    }

    class backgroundprocess  extends  Thread {

        @Override
        public void run() {
            if (feverish == 0){
                fever_prediction.add(0);
                fever_prediction.add(dosage);
                rn_fever_prediction = new medicineapicall().apicall(fever_prediction);
                Log.d("rn_fever_prediction", "onCreate: "+rn_fever_prediction);
                String fev_mor = null,fev_aft= null,fev_nt= null,fev_tm1= null,fev_tm2= null,fev_take= null,fev_avd= null;

                try {
                    JSONObject fevobj = new JSONObject(rn_fever_prediction);
                    fev_mor = fevobj.getString("morning");
                    fev_aft = fevobj.getString("afternoon");
                    fev_nt = fevobj.getString("night");
                    fev_tm1 = fevobj.getString("totalmedicin1");
                    fev_tm2 = fevobj.getString("totalmedicine2");
                    fev_take = fevobj.getString("take");
                    fev_avd = fevobj.getString("avoid");

                    if (!fev_mor.equals("nil")){
                        morning.add(fev_mor);
                    }
                    if (!fev_aft.equals("nil")){
                        Afternoon.add(fev_aft);
                    }
                    if (!fev_nt.equals("nil")){
                        night.add(fev_nt);
                    }
                    if (!fev_tm1.equals("nil")){
                        tm1.add(fev_tm1);
                    }
                    if (!fev_tm2.equals("nil")){
                        tm2.add(fev_tm2);
                    }
                    if (avoid.isEmpty()){
                        avoid.add(fev_avd);
                    }
                    if (take.isEmpty()){
                        take.add(fev_take);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
            if (head == 1){
                headache_prediction.add(1);
                headache_prediction.add(dosage);
                rn_headache_prediction = new medicineapicall().apicall(headache_prediction);
                String fev_mor = null,fev_aft= null,fev_nt= null,fev_tm1= null,fev_tm2= null,fev_take= null,fev_avd= null;

                try {
                    JSONObject fevobj = new JSONObject(rn_headache_prediction);
                    fev_mor = fevobj.getString("morning");
                    fev_aft = fevobj.getString("afternoon");
                    fev_nt = fevobj.getString("night");
                    fev_tm1 = fevobj.getString("totalmedicin1");
                    fev_tm2 = fevobj.getString("totalmedicine2");
                    fev_take = fevobj.getString("take");
                    fev_avd = fevobj.getString("avoid");

                    if (!fev_mor.equals("nil")){
                        morning.add(fev_mor);
                    }
                    if (!fev_aft.equals("nil")){
                        Afternoon.add(fev_aft);
                    }
                    if (!fev_nt.equals("nil")){
                        night.add(fev_nt);
                    }
                    if (!fev_tm1.equals("nil")){
                        tm1.add(fev_tm1);
                    }
                    if (!fev_tm2.equals("nil")){
                        tm2.add(fev_tm2);
                    }
                    if (avoid.isEmpty()){
                        avoid.add(fev_avd);
                    }
                    if (take.isEmpty()){
                        take.add(fev_take);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if (nose == 2){
                cold_prediction.add(2);
                cold_prediction.add(dosage);
                rn_cold_prediction = new medicineapicall().apicall(cold_prediction);
                String fev_mor = null,fev_aft= null,fev_nt= null,fev_tm1= null,fev_tm2= null,fev_take= null,fev_avd= null;

                try {
                    JSONObject fevobj = new JSONObject(rn_cold_prediction);
                    fev_mor = fevobj.getString("morning");
                    fev_aft = fevobj.getString("afternoon");
                    fev_nt = fevobj.getString("night");
                    fev_tm1 = fevobj.getString("totalmedicin1");
                    fev_tm2 = fevobj.getString("totalmedicine2");
                    fev_take = fevobj.getString("take");
                    fev_avd = fevobj.getString("avoid");

                    if (!fev_mor.equals("nil")){
                        morning.add(fev_mor);
                    }
                    if (!fev_aft.equals("nil")){
                        Afternoon.add(fev_aft);
                    }
                    if (!fev_nt.equals("nil")){
                        night.add(fev_nt);
                    }
                    if (!fev_tm1.equals("nil")){
                        tm1.add(fev_tm1);
                    }
                    if (!fev_tm2.equals("nil")){
                        tm2.add(fev_tm2);
                    }
                    if (avoid.isEmpty()){
                        avoid.add(fev_avd);
                    }
                    if (take.isEmpty()){
                        take.add(fev_take);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if (body == 3) {
                bodypain_prediction.add(3);
                bodypain_prediction.add(dosage);
                rn_bodypain_prediction = new medicineapicall().apicall(bodypain_prediction);
                String fev_mor = null,fev_aft= null,fev_nt= null,fev_tm1= null,fev_tm2= null,fev_take= null,fev_avd= null;

                try {
                    JSONObject fevobj = new JSONObject(rn_bodypain_prediction);
                    fev_mor = fevobj.getString("morning");
                    fev_aft = fevobj.getString("afternoon");
                    fev_nt = fevobj.getString("night");
                    fev_tm1 = fevobj.getString("totalmedicin1");
                    fev_tm2 = fevobj.getString("totalmedicine2");
                    fev_take = fevobj.getString("take");
                    fev_avd = fevobj.getString("avoid");


                    if (!fev_mor.equals("nil")){
                        morning.add(fev_mor);
                    }
                    if (!fev_aft.equals("nil")){
                        Afternoon.add(fev_aft);
                    }
                    if (!fev_nt.equals("nil")){
                        night.add(fev_nt);
                    }
                    if (!fev_tm1.equals("nil")){
                        tm1.add(fev_tm1);
                    }
                    if (!fev_tm2.equals("nil")){
                        tm2.add(fev_tm2);
                    }
                    if (avoid.isEmpty()){
                        avoid.add(fev_avd);
                    }
                    if (take.isEmpty()){
                        take.add(fev_take);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if (stomach == 4){
                stomach_prediction.add(4);
                stomach_prediction.add(dosage);
                rn_stomachpain_prediction = new medicineapicall().apicall(stomach_prediction);
                String fev_mor = null,fev_aft= null,fev_nt= null,fev_tm1= null,fev_tm2= null,fev_take= null,fev_avd= null;

                try {
                    JSONObject fevobj = new JSONObject(rn_stomachpain_prediction);
                    fev_mor = fevobj.getString("morning");
                    fev_aft = fevobj.getString("afternoon");
                    fev_nt = fevobj.getString("night");
                    fev_tm1 = fevobj.getString("totalmedicin1");
                    fev_tm2 = fevobj.getString("totalmedicine2");
                    fev_take = fevobj.getString("take");
                    fev_avd = fevobj.getString("avoid");

                    if (!fev_mor.equals("nil")){
                        morning.add(fev_mor);
                    }
                    if (!fev_aft.equals("nil")){
                        Afternoon.add(fev_aft);
                    }
                    if (!fev_nt.equals("nil")){
                        night.add(fev_nt);
                    }
                    if (!fev_tm1.equals("nil")){
                        tm1.add(fev_tm1);
                    }
                    if (!fev_tm2.equals("nil")){
                        tm2.add(fev_tm2);
                    }
                    if (avoid.isEmpty()){
                        avoid.add(fev_avd);
                    }
                    if (take.isEmpty()){
                        take.add(fev_take);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if (mouth == 5){
                nausea_prediction.add(5);
                nausea_prediction.add(dosage);
                rn_nausea_prediction = new medicineapicall().apicall(nausea_prediction);
                String fev_mor = null,fev_aft= null,fev_nt= null,fev_tm1= null,fev_tm2= null,fev_take= null,fev_avd= null;

                try {
                    JSONObject fevobj = new JSONObject(rn_nausea_prediction);
                    fev_mor = fevobj.getString("morning");
                    fev_aft = fevobj.getString("afternoon");
                    fev_nt = fevobj.getString("night");
                    fev_tm1 = fevobj.getString("totalmedicin1");
                    fev_tm2 = fevobj.getString("totalmedicine2");
                    fev_take = fevobj.getString("take");
                    fev_avd = fevobj.getString("avoid");

                    if (!fev_mor.equals("nil")){
                        morning.add(fev_mor);
                    }
                    if (!fev_aft.equals("nil")){
                        Afternoon.add(fev_aft);
                    }
                    if (!fev_nt.equals("nil")){
                        night.add(fev_nt);
                    }
                    if (!fev_tm1.equals("nil")){
                        tm1.add(fev_tm1);
                    }
                    if (!fev_tm2.equals("nil")){
                        tm2.add(fev_tm2);
                    }
                    if (avoid.isEmpty()){
                        avoid.add(fev_avd);
                    }
                    if (take.isEmpty()){
                        take.add(fev_take);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if (weak == 6){
                dizziness_prediction.add(6);
                dizziness_prediction.add(dosage);
                rn_dizziness_prediction = new medicineapicall().apicall(dizziness_prediction);
                String fev_mor = null,fev_aft= null,fev_nt= null,fev_tm1= null,fev_tm2= null,fev_take= null,fev_avd= null;

                try {
                    JSONObject fevobj = new JSONObject(rn_dizziness_prediction);
                    fev_mor = fevobj.getString("morning");
                    fev_aft = fevobj.getString("afternoon");
                    fev_nt = fevobj.getString("night");
                    fev_tm1 = fevobj.getString("totalmedicin1");
                    fev_tm2 = fevobj.getString("totalmedicine2");
                    fev_take = fevobj.getString("take");
                    fev_avd = fevobj.getString("avoid");

                    if (!fev_mor.equals("nil")){
                        morning.add(fev_mor);
                    }
                    if (!fev_aft.equals("nil")){
                        Afternoon.add(fev_aft);
                    }
                    if (!fev_nt.equals("nil")){
                        night.add(fev_nt);
                    }
                    if (!fev_tm1.equals("nil")){
                        tm1.add(fev_tm1);
                    }
                    if (!fev_tm2.equals("nil")){
                        tm2.add(fev_tm2);
                    }
                    if (avoid.isEmpty()){
                        avoid.add(fev_avd);
                    }
                    if (take.isEmpty()){
                        take.add(fev_take);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if (loose == 7){
                diarrhoea_prediction.add(7);
                diarrhoea_prediction.add(dosage);
                rn_diarrheoa_prediction = new medicineapicall().apicall(diarrhoea_prediction);
                String fev_mor = null,fev_aft= null,fev_nt= null,fev_tm1= null,fev_tm2= null,fev_take= null,fev_avd= null;

                try {
                    JSONObject fevobj = new JSONObject(rn_diarrheoa_prediction);
                    fev_mor = fevobj.getString("morning");
                    fev_aft = fevobj.getString("afternoon");
                    fev_nt = fevobj.getString("night");
                    fev_tm1 = fevobj.getString("totalmedicin1");
                    fev_tm2 = fevobj.getString("totalmedicine2");
                    fev_take = fevobj.getString("take");
                    fev_avd = fevobj.getString("avoid");

                    if (!fev_mor.equals("nil")){
                        morning.add(fev_mor);
                    }
                    if (!fev_aft.equals("nil")){
                        Afternoon.add(fev_aft);
                    }
                    if (!fev_nt.equals("nil")){
                        night.add(fev_nt);
                    }
                    if (!fev_tm1.equals("nil")){
                        tm1.add(fev_tm1);
                    }
                    if (!fev_tm2.equals("nil")){
                        tm2.add(fev_tm2);
                    }
                    if (avoid.isEmpty()){
                        avoid.add(fev_avd);
                    }
                    if (take.isEmpty()){
                        take.add(fev_take);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            Set<String> morningobj = new HashSet<>();
            morningobj.addAll(morning);
            morning.clear();
            for ( String X : morningobj){
                morning.add(X);
            }

            Set<String> Afternoonobj = new HashSet<>();
            Afternoonobj.addAll(Afternoon);
            Afternoon.clear();
            for ( String X : Afternoonobj){
                Afternoon.add(X);
            }

            Set<String> nightobj = new HashSet<>();
            nightobj.addAll(night);
            night.clear();
            for ( String X : nightobj){
                night.add(X);
            }

            Set<String> avoidobj = new HashSet<>();
            avoidobj.addAll(avoid);
            avoid.clear();
            for ( String X : avoidobj){
                avoid.add(X);
            }
            Set<String> takeobj = new HashSet<>();
            takeobj.addAll(take);
            take.clear();
            for ( String X : takeobj){
                take.add(X);
            }
            Set<String> tm1obj = new HashSet<>();
            tm1obj.addAll(tm1);
            tm1.clear();
            for ( String X : tm1obj){
                tm1.add(X);
            }

            Set<String> tm2obj = new HashSet<>();
            tm2obj.addAll(tm2);
            tm2.clear();
            for ( String X : tm2obj){
                tm2.add(X);
            }








            Log.d("values of mor", "onClick: "+morning);
            Log.d("values of aft", "onClick: "+Afternoon);
            Log.d("values of aft", "onClick: "+night);
            Log.d("values of aft", "onClick: "+tm1);
            Log.d("values of aft", "onClick: "+tm2);
            Log.d("values of aft", "onClick: "+avoid);
            Log.d("values of aft", "onClick: "+take);







        }
    }
}