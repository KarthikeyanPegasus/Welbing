package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OTP extends AppCompatActivity {

    TextView phonenumbercheckup;


    EditText otp1,otp2,otp3,otp4,otp5,otp6;
    String username;
    String phonenumbers;
    String verificationid;
    String phonenumber ;
    String emailid;

    String OTP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_o_t_p);

        username = getIntent().getStringExtra("username");
        phonenumbers = getIntent().getStringExtra("phonenumber");
        emailid = getIntent().getStringExtra("mailid");
        phonenumber = "+" + 91 + phonenumbers;
        phonenumbercheckup = findViewById(R.id.phonenumbercheck);
        otp1 = findViewById(R.id.otp1);
        otp2 = findViewById(R.id.otp2);
        otp3 = findViewById(R.id.otp3);
        otp4 = findViewById(R.id.otp4);
        otp5 = findViewById(R.id.otp5);
        otp6 = findViewById(R.id.otp6);



        phonenumbercheckup.setText(phonenumber);

        textchange();

        sendotptouser(phonenumber);


    }

    private void verification() {
        PhoneAuthCredential phoneAuthCredential =  PhoneAuthProvider.getCredential(
                verificationid,OTP
        );
        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            Intent intent;
                            final View imglogo3 = findViewById(R.id.Notifygif);
                            final View nametransition = findViewById(R.id.textOTP);

                            intent = new Intent(getApplicationContext(), IOT_signin.class);
                            intent.putExtra("username",username);
                            intent.putExtra("mailid",emailid);
                            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(OTP.this, Pair.create(nametransition, "nametransition"),
                                    Pair.create(imglogo3, "logo3"));
                            startActivity(intent, options.toBundle());

                        }
                        else{
                            Toast.makeText(OTP.this, "The OTP you have entered is incorrect", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void sendotptouser(String phonenumber) {

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phonenumber,
                60L,
                TimeUnit.SECONDS,
                OTP.this,
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Toast.makeText(OTP.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                       verificationid = verificationId;
                    }


                }
        );
    }


    private void textchange() {
        otp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(otp1.getEditableText().toString().length() == 1){
                    otp2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        otp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(otp2.getEditableText().toString().length() == 1){
                    otp3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        otp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(otp3.getEditableText().toString().length() == 1){
                    otp4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        otp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(otp4.getEditableText().toString().length() == 1){
                    otp5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        otp5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(otp5.getEditableText().toString().length() == 1){
                    otp6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });




    }




    public void OTP_NEXT(View view) {
       otplengthverification();
        verification();
    }

    private void otplengthverification() {
        OTP = otp1.getEditableText().toString()+otp2.getEditableText().toString()+otp3.getEditableText().toString()+otp4.getEditableText().toString()+otp5.getEditableText().toString()+otp6.getEditableText().toString();

        if(OTP.length() < 6){
            otp6.setError("Please enter the OTP correctly");
        }
    }


    public void PhoneOTP_goBack(View view) {
        Intent intent;
        final View imglogo3 = findViewById(R.id.Notifygif);
        final View nametransition = findViewById(R.id.textOTP);

        intent = new Intent(this, Signup_form1.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, Pair.create(nametransition, "nametransition"),
                Pair.create(imglogo3, "logo3"));
        startActivity(intent, options.toBundle());
    }

    public void wrongnumber(View view) {
        Intent intent;
        final View imglogo3 = findViewById(R.id.Notifygif);
        final View nametransition = findViewById(R.id.textOTP);

        intent = new Intent(this, Signup_form1.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, Pair.create(nametransition, "nametransition"),
                Pair.create(imglogo3, "logo3"));
        startActivity(intent, options.toBundle());

    }
}