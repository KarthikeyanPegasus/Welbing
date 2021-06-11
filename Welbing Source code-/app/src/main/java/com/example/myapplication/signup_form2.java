package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.ActivityOptions;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class signup_form2 extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    EditText Fullname, mailid, username, dob, password, repassword;
    Button signupnextbtn, signupbackbtn;
    TextView signinbtn;
    String usernames,fullname,Emailid,DOB,passwords;
    int age;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_signup_form2);

        Fullname = findViewById(R.id.Fullname);
        mailid = findViewById(R.id.mailid);
        username = findViewById(R.id.Username);
        dob = findViewById(R.id.DoB);
        password = findViewById(R.id.Password);
        repassword = findViewById(R.id.Re_Password);
        signinbtn = findViewById(R.id.SignUp);
        signupnextbtn = findViewById(R.id.signupnext);
        signupbackbtn = findViewById(R.id.bckbtn);




    }
    public boolean validatefullname(){
        String fullnames =Fullname.getEditableText().toString();

        if(fullnames.isEmpty()){
            Fullname.setError("Enter the fullname");
            return false;
        }
        else{
            Fullname.setError(null);
            return true;
        }

    }
    public boolean validateemailid(){
        String validmail = mailid.getEditableText().toString();
        String email = validmail;


        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null){
            mailid.setError("Enter the mail id");
            return false;}
        if(!pat.matcher(email).matches()){

            mailid.setError("Enter a valid mail id");
            return false;
        }
        else {
            return pat.matcher(email).matches();
        }
    }
    public boolean validateusername(){
        String usernames = username.getEditableText().toString();

        if(usernames.isEmpty()){
            username.setError("please enter the username");
            return false;
        }

        else{
            username.setError(null);
            return true;
        }

    }
    public boolean validatedateofbirth(){
        int currentyear = Calendar.getInstance().get(Calendar.YEAR);
        if (currentyear - age < 18){
            dob.setError("Minor Age Detected");
            return false;
        }
        else{
            dob.setError(null);
            return true;
        }


    }
    public boolean validatepassword(){
        String passworded = password.getEditableText().toString();
        // Regex to check valid password.
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        // If the password is empty
        // return false
        if (passworded == null) {
            password.setError("Enter the password");
            return false;
        }

        // Pattern class contains matcher() method
        // to find matching between given password
        // and regular expression.
        Matcher m = p.matcher(passworded);

        if(!m.matches()){
            password.setError("Weak Password detected \n atleast 1 numeric character \n atleast 1 symbol \n atleast 1 alphabetic character required");
            return false;
        }
        // Return if the password
        // matched the ReGex
        else {
            return m.matches();
        }

    }
    public boolean validaterepassword(){
        String consider = repassword.getEditableText().toString();
        if(consider == null) {
            repassword.setError("re enter the password");
            return false;
        }
        else
        {
            repassword.setError(null);
            return true;
        }

    }

    public boolean matchpassword(){
        String first = password.getEditableText().toString();
        String second = repassword.getEditableText().toString();

        if (first.equals(second)){
            repassword.setError(null);
            return true;
        }
        else {
            repassword.setError("password mismatch");
            return false;
        }
    }


    public void SignupNext(View view) {

        if (!validatefullname() | !validateemailid() | !validatedateofbirth() | !validateusername() | !validatepassword() | !validaterepassword() | !matchpassword()) {
            return;
        }
        else{


            usernames = username.getEditableText().toString();
            fullname = Fullname.getEditableText().toString();
            Emailid = mailid.getEditableText().toString();
            DOB = dob.getEditableText().toString();
            passwords = password.getEditableText().toString();



            Intent intent;
            final View imglogo3 = findViewById(R.id.Icon);
            final View nametransition = findViewById(R.id.appName);

            intent = new Intent(this, Signup_form1.class);
            intent.putExtra("Username", usernames);
            intent.putExtra("fullname", fullname);
            intent.putExtra("mailid", Emailid);
            intent.putExtra("dob", DOB);
            intent.putExtra("password", passwords);
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, Pair.create(nametransition, "nametransition"),
                    Pair.create(imglogo3, "logo3"));
            startActivity(intent, options.toBundle());
            finish();
        }


    }

    public void SignUpBack(View view) {
        Intent intent;
        final View imglogo3 = findViewById(R.id.Icon);
        final View nametransition = findViewById(R.id.appName);

        intent = new Intent(this, Login_form.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, Pair.create(nametransition, "nametransition"),
                Pair.create(imglogo3, "logo3"));
        startActivity(intent, options.toBundle());
    }

    public void SignIn(View view) {
        Intent intent;
        final View imglogo3 = findViewById(R.id.Icon);
        final View nametransition = findViewById(R.id.appName);

        intent = new Intent(this, Login_form.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, Pair.create(nametransition, "nametransition"),
                Pair.create(imglogo3, "logo3"));
        startActivity(intent, options.toBundle());
    }

    public void datepicker() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        this.age = i;
        int month = i1 +1;
        String date = i2 + "/" +month + "/" + i ;
        dob.setText(date);
    }

    public void datepickeronclick(View view) {
        datepicker();
    }
}