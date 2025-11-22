package com.example.example.ui.food;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class FoodViewModel extends ViewModel {

    private final MutableLiveData<List<String>> items =
            new MutableLiveData<>(new ArrayList<String>());

    public LiveData<List<String>> getItems() {
        return items;
    }

    public void addDonation(String description) {
        if (description == null || description.trim().isEmpty()) {
            return;
        }

        List<String> current = items.getValue();
        if (current == null) {
            current = new ArrayList<>();
        }

        current.add(description);
        // trigger observers
        items.setValue(new ArrayList<>(current));
    }
}
