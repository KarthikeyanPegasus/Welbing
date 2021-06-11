package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Pattern;

public class Login_form extends AppCompatActivity {
    EditText User,pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_login_form);
        User = findViewById(R.id.Username);
        pass = findViewById(R.id.Password);
    }

    private boolean validateusername() {
        String username = User.getEditableText().toString();
        String email = username;


        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null){
            User.setError("Enter the mail id");
            return false;}
        if(!pat.matcher(email).matches()){
            User.setError("Enter a valid mail id");
        }
        return pat.matcher(email).matches();


    }
    private boolean validatepassword(){
        String password = pass.getEditableText().toString();

        if(password.isEmpty()){
            pass.setError("enter the password");
            return false;
        }
        else{
            pass.setError(null);
            return true;
        }
    }

    private void isUser(){
        final String userentered = User.getEditableText().toString();
        final String userenteredusername = userentered.replace('.',',');
        final String userenteredpassword = pass.getEditableText().toString();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Accountdetails");
        Query checkuser = reference.orderByChild("emailid").equalTo(userenteredusername);

        checkuser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){

                    User.setError(null);

                    String passwordfromdb = dataSnapshot.child(userenteredusername).child("password").getValue(String.class);


                    if(passwordfromdb != null && passwordfromdb.equals(userenteredpassword)){

                        String usernamefromdatabase = dataSnapshot.child(userenteredusername).child("fullname").getValue(String.class);

                        Intent homeactivity =new Intent(getApplicationContext(),HomeActivity.class);
                        homeactivity.putExtra("usernamefromdatbase",usernamefromdatabase);
                        homeactivity.putExtra("emailid",User.getEditableText().toString());
                        startActivity(homeactivity);
                        finish();
                    }
                    else{
                        pass.setError("Password wrong");
                        pass.requestFocus();
                    }
                }
                else{
                    User.setError("No such user exist, create an account");
                    User.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void LoginNext(View view) {
        //validating user

        if(!validateusername() | !validatepassword()){
            return;
        }
        else{
            isUser();
        }

    }

    public void LoginBack(View view) {
        Intent intent;
        final View imglogo3 = findViewById(R.id.logo3);
        final View nametransition = findViewById(R.id.AppName);

        intent = new Intent(this, WelcomePage.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, Pair.create(nametransition, "nametransition"),
                Pair.create(imglogo3, "logo3"));
        startActivity(intent, options.toBundle());
    }

    public void SignUp(View view) {
        Intent intent;
        final View imglogo3 = findViewById(R.id.logo3);
        final View nametransition = findViewById(R.id.AppName);

        intent = new Intent(this, signup_form2.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, Pair.create(nametransition, "nametransition"),
                Pair.create(imglogo3, "logo3"));
        startActivity(intent, options.toBundle());
    }

    public void forgotPassword(View view) {
        // forward to a website to do the verification and than allow him to change the password
    }
}