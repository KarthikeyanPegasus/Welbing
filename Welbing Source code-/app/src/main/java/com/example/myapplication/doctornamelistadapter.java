package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class doctornamelistadapter extends RecyclerView.Adapter<doctornamelistadapter.bookappointmentViewHolder> {
    List<bookappointmentmodel> models;
    Context context;
    String emailid;


    public doctornamelistadapter(List<bookappointmentmodel> models, Context context,String emailid) {
        this.models = models;
        this.context = context;
        this.emailid = emailid;
    }

    @NonNull
    @Override
    public bookappointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new bookappointmentViewHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.doctorlistitem, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final bookappointmentViewHolder holder, final int position) {
        holder.drname.setText(models.get(position).getDrname());
        holder.drspeciality.setText(models.get(position).getDrspeciality());
        holder.dravail.setText(models.get(position).getDravailability());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent appointmenthome = new Intent(context, com.example.myapplication.appointmenthome.class);
                appointmenthome.putExtra("drname", models.get(position).getDrname());
                appointmenthome.putExtra("emailid",emailid);
                context.startActivity(appointmenthome);

            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }


    public static class bookappointmentViewHolder extends RecyclerView.ViewHolder {
        public TextView drname, drspeciality, dravail;

        public bookappointmentViewHolder(@NonNull View itemView) {
            super(itemView);
            drname = itemView.findViewById(R.id.doctorname);
            drspeciality = itemView.findViewById(R.id.specialisation);
            dravail = itemView.findViewById(R.id.availabletime);


        }
    }
}
