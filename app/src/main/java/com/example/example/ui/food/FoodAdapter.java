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

    private List<String> items;

    public FoodAdapter(List<String> items) {
        this.items = items;
    }

    public void updateData(List<String> newItems) {
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
        String desc = items.get(position);
        holder.textDesc.setText(desc);
        // later: set name, distance, etc
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    static class FoodViewHolder extends RecyclerView.ViewHolder {

        TextView textDesc;

        FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            textDesc = itemView.findViewById(R.id.textDesc);
        }
    }
}
