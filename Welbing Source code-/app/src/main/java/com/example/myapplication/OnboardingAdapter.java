package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OnboardingAdapter extends RecyclerView.Adapter<OnboardingAdapter.onBoardingViewholder>{

     private List<OnboardingItem> onboardingItem;

    public OnboardingAdapter(List<OnboardingItem> onboardingItem) {
        this.onboardingItem = onboardingItem;
    }

    @NonNull
    @Override
    public onBoardingViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new onBoardingViewholder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_container_onboarding,parent,false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull onBoardingViewholder holder, int position) {
        holder.OnboardingData(onboardingItem.get(position));
    }

    @Override
    public int getItemCount() {
        return onboardingItem.size();
    }

    class onBoardingViewholder extends RecyclerView.ViewHolder{
        private TextView textTitle;
        private TextView description;
        private ImageView ImageOnboarding;

        public onBoardingViewholder(@NonNull View itemView){
            super(itemView);
            textTitle = itemView.findViewById(R.id.TextTitle);
            description = itemView.findViewById(R.id.description);
            ImageOnboarding = itemView.findViewById(R.id.imageonboarding);

        }
        void OnboardingData(OnboardingItem onboardingItem){
            textTitle.setText(onboardingItem.getTitle());
            description.setText(onboardingItem.getDescription());
            ImageOnboarding.setImageResource(onboardingItem.getImage());
        }

    }

}
