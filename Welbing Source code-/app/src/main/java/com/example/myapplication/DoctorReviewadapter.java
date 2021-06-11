package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class DoctorReviewadapter extends RecyclerView.Adapter<DoctorReviewadapter.reviewViewHolder> {
    List<doctorreviewmodel> models;

    public DoctorReviewadapter(List<doctorreviewmodel> models) {
        this.models = models;
    }

    @NonNull
    @Override
    public reviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new reviewViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.doctorreviewitem,parent,false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull final reviewViewHolder holder, final int position) {
    holder.reviewername.setText(models.get(position).getReviewername());
    holder.review.setText(models.get(position).getReviews());

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class reviewViewHolder extends RecyclerView.ViewHolder{
        TextView reviewername,review;
        public reviewViewHolder(@NonNull View itemView) {
            super(itemView);
            review = itemView.findViewById(R.id.reviewsfromreviewer);
            reviewername=itemView.findViewById(R.id.nameofreviewer);
        }
    }
}
