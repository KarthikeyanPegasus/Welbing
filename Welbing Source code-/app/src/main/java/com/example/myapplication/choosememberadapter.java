package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class choosememberadapter extends RecyclerView.Adapter<choosememberadapter.chooseViewholder>{
    List<String> name;
    Context contexts;
    String drname;

    public choosememberadapter(List<String> name,String drname, Context contexts) {
        this.name = name;
        this.drname = drname;
        this.contexts = contexts;
    }

    @NonNull
    @Override
    public chooseViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new chooseViewholder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.selectfamilymember_grid, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull chooseViewholder holder, final int position) {
    holder.mname.setText(name.get(position));
    holder.cardview.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(contexts,appointmentdetails.class);
            intent.putExtra("appointyname",name.get(position));
            intent.putExtra("drname",drname);
            contexts.startActivity(intent);
        }
    });
    }

    @Override
    public int getItemCount() {
        return name.size();
    }


    public class chooseViewholder extends RecyclerView.ViewHolder {

        TextView mname;
        CardView cardview;
        public chooseViewholder(@NonNull View itemView) {
            super(itemView);
            mname=itemView.findViewById(R.id.select_name_1);
            cardview = itemView.findViewById(R.id.familymembercard);
        }
    }
}
