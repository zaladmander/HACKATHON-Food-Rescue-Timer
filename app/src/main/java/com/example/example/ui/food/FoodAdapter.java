package com.example.example.ui.food;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.example.R;

import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    private List<FoodItem> items;

    public FoodAdapter(List<FoodItem> items) {
        this.items = items;
    }

    public void updateData(List<FoodItem> newItems) {
        if (newItems == null) {
            this.items = new ArrayList<>();
        } else {
            this.items = new ArrayList<>(newItems);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_food, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        FoodItem item = items.get(position);
        holder.textTitle.setText(item.getTitle());
        holder.textDesc.setText(item.getDescription());
        holder.textTimer.setText(item.getMinutesLeft() + " min left");
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    static class FoodViewHolder extends RecyclerView.ViewHolder {

        TextView textTitle;
        TextView textDesc;
        TextView textTimer;

        FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.textTitle);
            textDesc = itemView.findViewById(R.id.textDesc);
            textTimer = itemView.findViewById(R.id.textTimer);
        }
    }

}
