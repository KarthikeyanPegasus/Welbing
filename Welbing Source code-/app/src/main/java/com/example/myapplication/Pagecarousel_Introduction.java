package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class Pagecarousel_Introduction extends AppCompatActivity {
    private OnboardingAdapter onboardingAdapter;
    private LinearLayout layoutOnBoardingIndicator;
    private Button buttonOnBoardingAction;
    String username;
    String emailid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_pagecarousel__introduction);
        buttonOnBoardingAction = findViewById(R.id.buttonOnBoardingAction);

        username = getIntent().getStringExtra("username");
        emailid = getIntent().getStringExtra("mailid");
        layoutOnBoardingIndicator = findViewById(R.id.layoutOnboardingIndicator);
        setupOnboardingItems();

        final ViewPager2 onboardingViewPager =   findViewById(R.id.onBoardViewPager);
        onboardingViewPager.setAdapter(onboardingAdapter);

        setupOnboardingIndicator();
        setCurrentOnboardingIndicator(0);
        onboardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnboardingIndicator(position);
            }
        });

        buttonOnBoardingAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onboardingViewPager.getCurrentItem() + 1 < onboardingAdapter.getItemCount()){
                    onboardingViewPager.setCurrentItem(onboardingViewPager.getCurrentItem()+1);
                }
                else{
                    // here comes the next activity we are gonna use the sharedpreferences technique for this in order to add animations;
                    startActivity(new Intent(getApplicationContext(),HomeActivity.class).putExtra("username",username).putExtra("mailid",emailid));
                    finish();
                }
            }
        });
    }
    private void setupOnboardingItems(){
        List<OnboardingItem> onboardingItems = new ArrayList<>();

        OnboardingItem IOTthermometer = new OnboardingItem();
        IOTthermometer.setTitle("Thermometer");
        IOTthermometer.setDescription("This is the IOT based thermometer which is come with the WelBing toolkit");
        IOTthermometer.setImage(R.drawable.onboarding1);

        OnboardingItem IOTPressuresensor = new OnboardingItem();
        IOTPressuresensor.setTitle("Blood Pressure Analyser");
        IOTPressuresensor.setDescription("This is the IOT based machinized spygmomanometer which is used to analyse the bloodPressure");
        IOTPressuresensor.setImage(R.drawable.onboarding1);

        onboardingItems.add(IOTthermometer);
        onboardingItems.add(IOTPressuresensor);

        onboardingAdapter = new OnboardingAdapter(onboardingItems);
    }

    private void setupOnboardingIndicator(){
        ImageView[] indicators = new ImageView[onboardingAdapter.getItemCount()];
        LinearLayout.LayoutParams LayoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        LayoutParams.setMargins(8,0,8,0);
        for(int i = 0 ; i< indicators.length;i++){
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(),
                    R.drawable.onboarding_indicator_passive
            ));
            indicators[i].setLayoutParams(LayoutParams);
            layoutOnBoardingIndicator.addView(indicators[i]);
        }
    }
    private void setCurrentOnboardingIndicator(int index){
        int childcount = layoutOnBoardingIndicator.getChildCount();
        for (int i = 0 ; i < childcount; i++){
            ImageView imageView = (ImageView)layoutOnBoardingIndicator.getChildAt(i);
            if(i == index){
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(),R.drawable.onboarding_indicator_active)
                );
            }
            else{
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.onboarding_indicator_passive));

            }
        }

        if(index == onboardingAdapter.getItemCount()-1){
            buttonOnBoardingAction.setText("Start") ;
        }
        else{
            buttonOnBoardingAction.setText("Next");
        }
    }
}