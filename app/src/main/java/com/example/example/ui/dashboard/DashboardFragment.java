package com.example.example.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.example.R;
import com.example.example.ui.food.FoodAdapter;
import com.example.example.ui.food.FoodItem;
import com.example.example.ui.food.FoodViewModel;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private RecyclerView recyclerFood;
    private FoodAdapter adapter;
    private SearchView searchFoods;
    private FoodViewModel foodViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        recyclerFood = root.findViewById(R.id.recyclerFood);
        searchFoods  = root.findViewById(R.id.searchFoods);

        recyclerFood.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new FoodAdapter(new ArrayList<FoodItem>());
        recyclerFood.setAdapter(adapter);

        foodViewModel = new ViewModelProvider(requireActivity())
                .get(FoodViewModel.class);

        foodViewModel.getItems().observe(getViewLifecycleOwner(), items -> {
            adapter.updateData(items);
        });

        searchFoods.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                foodViewModel.filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                foodViewModel.filter(newText);
                return true;
            }
        });

        return root;
    }
}
