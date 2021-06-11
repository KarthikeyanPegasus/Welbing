package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class appointmentdetails extends AppCompatActivity {

    TextView appointyname, appointytime, appointydate, Drname;
    DatePicker appointmentdate;
    TimePicker appointmenttime;

    String aptyname,drname,emailid;

    Button online, offline;
    int on = 0, off = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_appointmentdetails);
        appointydate = findViewById(R.id.appointydate);
        appointyname = findViewById(R.id.appointyname);
        appointytime = findViewById(R.id.appointytime);
        online = findViewById(R.id.idonline);
        offline = findViewById(R.id.idoffline);


        Drname = findViewById(R.id.Drname);

        appointmenttime = findViewById(R.id.appointmenttime);
        appointmentdate = findViewById(R.id.appointmentdate);

        drname = getIntent().getStringExtra("drname");
        Drname.setText(drname);
        aptyname = getIntent().getStringExtra("appointyname");
        appointyname.setText(aptyname);
        emailid = getIntent().getStringExtra("emailid");

        appointmentdate.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                String date;
                date = i2+"/"+(int)(i1+1)+"/"+i;
                appointydate.setText(date);
            }
        });

        appointmenttime.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                String time,af;
                if (i < 12) time = i+":"+i1+"AM";
                else time = i+":" +i1+"PM";

                appointytime.setText(time);

            }
        });




    }

    public void choose(View view) {
        Intent choose = new Intent(this,choosemember.class);
        choose.putExtra("drname",drname);
       startActivity(choose);

    }

    public boolean verifyapptyname(){
        if (appointyname.getText() == null){
            appointyname.setError("Please select the name");
            return false;
        }
        else{
            Drname.setError(null);
            return true;
        }

    }
    public boolean verifydate(){
        if (appointydate.getText() == "PLEASE SELECT") {
            appointydate.setError("Please select the Date for the appointment");
            appointmentdate.requestFocus();
            return false;
        }
        else{
            appointydate.setError(null);
            return true;

        }
    }
    public boolean verifytime(){
        if (appointytime.getText() == "PLEASE SELECT"){
            appointytime.setError("please select time");
            appointmenttime.requestFocus();
            return false;
        }
        else{
            appointytime.setError(null);
            return true;
        }

    }
    public boolean verifymode(){

        if(on == 0 && off == 0){
            offline.setError("Please select the mode of the appointment");
            offline.requestFocus();
            return false;
        }
        else{
            offline.setError(null);
            return true;
        }

    }

    public void bookappointment(View view) {


        if(!verifyapptyname() | !verifydate() | !verifymode() | !verifytime()){
            return;
        }
        else{
                String name = (String) appointyname.getText();
                String date = (String) appointydate.getText();
                String time = (String) appointytime.getText();
                String drname = (String) Drname.getText();
                String mode;
                if (on == 1 && off == 0){
                    mode= " online";
                }
                else{
                    mode = "offline";
                }

                appointmenthelperclass helper = new appointmenthelperclass(name,time,date,mode);
                appointmenthelperclass newhelper = new appointmenthelperclass(drname,time,date,mode);

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Doctor");
            DatabaseReference doctoename = reference.child(drname);
            DatabaseReference appointment = doctoename.child("Appointment");
            appointment.child(name).setValue(helper);

            DatabaseReference newreference =  FirebaseDatabase.getInstance().getReference("Accountdetails");
            DatabaseReference newemailid = newreference.child(emailid);
            DatabaseReference newpatient = newemailid.child("familymember");
            DatabaseReference newmember = newpatient.child(name);
            DatabaseReference newappointment = newmember.child("Appointment");
            newappointment.child(drname).setValue(newhelper);



        }

    }

    public void selectonline(View view) {
        offline.setBackgroundResource(R.drawable.bgyellowbtn);
        online.setBackgroundResource(R.drawable.boyselector);
        on = 1;
        off = 0;

    }

    public void selectoffline(View view) {
        offline.setBackgroundResource(R.drawable.badgirl);
        online.setBackgroundResource(R.drawable.successbtn);
        on = 0;
        off = 1;
    }
}