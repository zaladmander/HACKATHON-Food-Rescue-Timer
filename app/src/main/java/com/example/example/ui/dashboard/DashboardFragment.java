package com.example.example.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.example.R;
import com.example.example.ui.food.FoodAdapter;
import com.example.example.ui.food.FoodViewModel;

import org.jspecify.annotations.NonNull;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private RecyclerView recyclerView;
    private FoodAdapter adapter;
    private FoodViewModel foodViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        recyclerView = root.findViewById(R.id.recyclerFood);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new FoodAdapter(new ArrayList<String>());
        recyclerView.setAdapter(adapter);

        foodViewModel = new ViewModelProvider(requireActivity())
                .get(FoodViewModel.class);

        foodViewModel.getItems().observe(getViewLifecycleOwner(), items -> {
            adapter.updateData(items);
        });

        return root;
    }
}
