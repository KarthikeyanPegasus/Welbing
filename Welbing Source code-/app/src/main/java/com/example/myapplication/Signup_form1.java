package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class Signup_form1 extends AppCompatActivity {

    EditText Addressline1,Addressline2,pincode,landmark,phonenumber,rephonenumber;
    TextView Signin, mismatch;
    Button signupback2, signupnext2;
    String Phonenumber1;
    String username;
    String emailid;


    FirebaseDatabase rootNode;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_signup_form1);


        Addressline1 = findViewById(R.id.Addressline1);
        Addressline2 = findViewById(R.id.Addressline2);
        pincode = findViewById(R.id.Pincode);
        landmark = findViewById(R.id.Landmark);
        phonenumber = findViewById(R.id.Phonenumber);
        rephonenumber = findViewById(R.id.Re_Phonenumber);
        Signin = findViewById(R.id.SignUp);
        signupback2 =  findViewById(R.id.bckbtn);
        signupnext2 = findViewById(R.id.nextbtn);
        mismatch = findViewById(R.id.mismatch);

        signupnext2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                String Addresslines1 = Addressline1.getText().toString();
                String Addresslines2 = Addressline2.getText().toString();
                String Address = Addresslines1+Addresslines2;
                String pincodes = pincode.getText().toString();
                String landmarks = landmark.getText().toString();
                Phonenumber1 = phonenumber.getText().toString();
                String Phonenumber2 = rephonenumber.getText().toString();
                username = getIntent().getStringExtra("Username");
                String password = getIntent().getStringExtra("password");
                String fullname = getIntent().getStringExtra("fullname");
                String Emailid = getIntent().getStringExtra("mailid");
                String DOB = getIntent().getStringExtra("dob");
                emailid = Emailid.replace('.',',');



                    Signuphelperclass2 helperclass2 = new Signuphelperclass2(Address, pincodes, landmarks, Phonenumber1,fullname,emailid,DOB,username,password);

                    rootNode = FirebaseDatabase.getInstance();
                    reference = rootNode.getReference("Accountdetails");
                    reference.child(emailid).setValue(helperclass2);

                    SignupNext();

            }
        });


    }

    public boolean validaddressline1(){
        String a1 = Addressline1.getEditableText().toString();

        if(a1.isEmpty()){
            Addressline1.setError("enter the Address");
            return false;
        }
        else{
            Addressline1.setError(null);
            return true;
        }
    }
    public boolean validateaddressline2(){

        String a2 =Addressline2.getEditableText().toString();

        if (a2.isEmpty()){
            Addressline2.setError("enter the Address");
            return false;
        }
        else {
            Addressline2.setError(null);
            return true;
        }
    }
    public boolean validatepincode(){

        String pin =  pincode.getEditableText().toString();

        if (pin.isEmpty()){
            pincode.setError("enter the pincode");
            return false;
        }
        else{
            pincode.setError(null);
            return true;
        }

    }
    public boolean validatelandmarks(){

        String landmarks = landmark.getEditableText().toString();

        if(landmarks.isEmpty()){
            landmark.setError("enter the nearby landmark");
            return false;
        }
        else{
            landmark.setError(null);
            return true;
        }

    }
    public boolean phonenumber(){
        String phone = phonenumber.getEditableText().toString();
        String regex = "(0/91)?[7-9][0-9]{9}";

        if(phone.isEmpty()){
            phonenumber.setError("enter the mobile number");
            return false;
        }
        else if(!phone.matches(regex)){
            phonenumber.setError("enter a valid phone number");
            return false;
        }
        else{
            phonenumber.setError(null);
            return true;
        }

    }
    public boolean rephonenumber(){
        String rephone = rephonenumber.getEditableText().toString();

        if (rephone.isEmpty()){
            rephonenumber.setError("re enter the phone number");
            return false;
        }
        else{
            rephonenumber.setError(null);
            return true;
        }

    }
    public boolean matchphonenumber(){

        String p1 ,p2;
        p1 = phonenumber.getEditableText().toString();
        p2 = rephonenumber.getEditableText().toString();

        if(!p1.equals(p2)){
            rephonenumber.setError("phone number mismatch");
            return false;
        }
        else{
            rephonenumber.setError(null);
            return true;
        }

    }

    public void SignupNext() {

        if(validaddressline1() | validateaddressline2() | validatelandmarks() | validatepincode() | phonenumber() | rephonenumber() | matchphonenumber()){
            Intent intent;
            final View imglogo3 = findViewById(R.id.Icon);
            final View nametransition = findViewById(R.id.AppName);

            intent = new Intent(this, OTP.class);
            intent.putExtra("phonenumber",Phonenumber1);
            intent.putExtra("username",username);
            intent.putExtra("mailid",emailid);
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, Pair.create(nametransition, "nametransition"),
                    Pair.create(imglogo3, "logo3"));
            startActivity(intent, options.toBundle());
            finish();
        }
        else{

            return;

        }





    }

    public void SignUpBack(View view) {
        Intent intent;
        final View imglogo3 = findViewById(R.id.Icon);
        final View nametransition = findViewById(R.id.AppName);

        intent = new Intent(this, signup_form2.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, Pair.create(nametransition, "nametransition"),
                Pair.create(imglogo3, "logo3"));
        startActivity(intent, options.toBundle());
    }

    public void SignIn(View view) {
        Intent intent;
        final View imglogo3 = findViewById(R.id.Icon);
        final View nametransition = findViewById(R.id.AppName);

        intent = new Intent(this, Login_form.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, Pair.create(nametransition, "nametransition"),
                Pair.create(imglogo3, "logo3"));
        startActivity(intent, options.toBundle());
    }
}