package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class familymemberAdapter extends RecyclerView.Adapter<familymemberAdapter.familymembercarousel> {

    private List<familymembermodel> familymembermodels;

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void OnItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public familymemberAdapter(List<familymembermodel> familymembermodels) {
        this.familymembermodels = familymembermodels;
    }


    @NonNull
    @Override
    public familymembercarousel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new familymembercarousel(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.familymemberitem,parent,false)

        );
    }

    @Override
    public void onBindViewHolder(@NonNull familymembercarousel holder, int position) {
        holder.familymemberdata(familymembermodels.get(position));
    }

    @Override
    public int getItemCount() {
        return familymembermodels.size();
    }

    public class familymembercarousel extends RecyclerView.ViewHolder {
        ImageView profile;
        TextView name;
        CardView members;
        public familymembercarousel(@NonNull View itemView) {
            super(itemView);
            profile = itemView.findViewById(R.id.memberprofile);
            name = itemView.findViewById(R.id.membername);
            members = itemView.findViewById(R.id.membercard);


        }

        public void familymemberdata(familymembermodel familymembermodels){
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Accountdetails");
            DatabaseReference emailid = reference.child(familymembermodels.getIcon());
            DatabaseReference member = emailid.child("familymember");
            DatabaseReference patient = member.child(familymembermodels.getName());
            DatabaseReference healthdetails = patient.child("healthdetails");
            healthdetails.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String dbicon = dataSnapshot.child("icon").getValue(String.class);
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

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            name.setText(familymembermodels.getName());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (mListener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                           mListener.OnItemClick(position);
                        }
                    }

                }
            });

        }




    }




}
