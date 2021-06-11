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

public class selectfamilymemberadapter extends RecyclerView.Adapter<selectfamilymemberadapter.viewholder>{

    List<String> name;
    LayoutInflater inflater;
    Context contexts;
    String emailid;

    public selectfamilymemberadapter(List<String> name, Context context,String emailid) {
        this.name = name;
        this.inflater = LayoutInflater.from(context);
        this.contexts = context;
        this.emailid = emailid;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.selectfamilymember_grid,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final viewholder holder, final int position) {
        holder.gridname.setText(name.get(position));
        holder.cardname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(contexts,IOTcheckonline.class);
                intent.putExtra("patientname",name.get(position));
                intent.putExtra("emailid",emailid);
                intent.putExtra("position",position);
                contexts.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        TextView gridname;
        CardView cardname;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            gridname = itemView.findViewById(R.id.select_name_1);
            cardname = itemView.findViewById(R.id.familymembercard);

        }
    }
}
