package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addfamilymember extends AppCompatActivity {

    EditText name,height,weight,age,previousallergies;
    Button male,female;
    String mname,mheight,mweight,mage,mpreviousallergies,msexuality,icon;
    int sexuality = 0;
    String Sexuality,mailid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_addfamilymember);
        mailid = getIntent().getStringExtra("mailid");

        name = findViewById(R.id.fullname);
        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);
        age = findViewById(R.id.age);
        previousallergies = findViewById(R.id.previousallergies);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);

    }

    public void addfamilymembernext(View view) {
        mname = name.getEditableText().toString();
        mheight = height.getEditableText().toString();
        mweight = weight.getEditableText().toString();
        mage = age.getEditableText().toString();
        mpreviousallergies = previousallergies.getEditableText().toString();
        if (sexuality == 1){
            Sexuality = "male";
        }
        else if(sexuality == 2){
            Sexuality = "female";
        }
        else{
            //do nothing
        }

        if(validateage() | validateheight() | validatename() | validatepreviousallergies() | validatesexuality() | validateweight()){

            if (Sexuality == "male"){
                if (Integer.parseInt(mage)<18){
                    icon = "2";
                }
                else if (Integer.parseInt(mage)>18 && Integer.parseInt(mage)<50)
                {
                    icon = "3";
                }
                else if (Integer.parseInt(mage)>50){
                    icon = "1";
                }
            }
            else if (Sexuality == "female"){
                if (Integer.parseInt(mage)<18){
                    icon = "4";
                }
                else if (Integer.parseInt(mage)>18 && Integer.parseInt(mage)<50)
                {
                    icon = "5";
                }
                else if (Integer.parseInt(mage)>50){
                    icon = "6";
                }
            }
            addmemberhelperclass addmemberhelperclass = new addmemberhelperclass(mname,mage,mheight,mweight,Sexuality,mpreviousallergies,icon);


            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Accountdetails");
            DatabaseReference user = reference.child(mailid);
            DatabaseReference familymember = user.child("familymember");
            DatabaseReference addmember = familymember.child(mname);
            DatabaseReference healthdetails = addmember.child("healthdetails");
            healthdetails.setValue(addmemberhelperclass);

            Intent homeactivity = new Intent(this,HomeActivity.class);
            homeactivity.putExtra("mailid",mailid);
            startActivity(homeactivity);
            finish();



        }
        else
        {
            return;
        }


    }
    public boolean validatename(){
        if (mname.isEmpty()){
            name.setError("Enter the fullname");
            return false;
        }
        else{
            name.setError(null);
            return true;
        }
    }
    public boolean validateheight(){
        if(mheight.isEmpty()){
            height.setError("height is empty");
            return false;
        }
        else{
            height.setError(null)   ;
            return true;
        }

    }
    public boolean validateweight(){
        if(mweight.isEmpty()){
            weight.setError("weight is empty");
            return false;
        }
        else{
            weight.setError(null);
            return true;
        }

    }
    public boolean validateage(){
        if(mage.isEmpty()){
            age.setError("Age is empty");
            return false;
        }
        else{
            age.setError(null);
            return true;
        }

    }
    public boolean validatepreviousallergies(){
        if(mpreviousallergies.isEmpty()){
            previousallergies.setError("please enter NILL if you dont have any allergies");
            return false;
        }
        else{
            previousallergies.setError(null);
            return true;
        }

    }
    public boolean validatesexuality(){
        if (sexuality == 0){
            male.setError("select the sexuality");
            female.setError("select the sexuality");
            return false;
        }
        else{
            male.setError(null);
            female.setError(null);
            return true;
        }

    }
    public void addfamilymemberback(View view) {
        finish();
    }

    public void selectfemale(View view) {
        sexuality = 2;
        male.setBackgroundResource(R.drawable.boyselector);
        female.setBackgroundResource(R.drawable.badgirl);
        male.setError(null);
        female.setError(null);
    }

    public void selectmale(View view) {
        sexuality = 1;
        male.setBackgroundResource(R.drawable.badboy);
        female.setBackgroundResource(R.drawable.badbtn);
        male.setError(null);
        female.setError(null);
    }
}