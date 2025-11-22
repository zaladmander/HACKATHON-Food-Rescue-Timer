package com.example.example.ui.food;

import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class FoodViewModel extends ViewModel {

    private final MutableLiveData<List<FoodItem>> items =
            new MutableLiveData<>(new ArrayList<>());

    private final Handler handler = new Handler(Looper.getMainLooper());
    private boolean ticking = false;

    public LiveData<List<FoodItem>> getItems() {
        return items;
    }

    public void addDonation(String description) {
        if (description == null || description.trim().isEmpty()) {
            return;
        }

        List<FoodItem> current = items.getValue();
        if (current == null) {
            current = new ArrayList<>();
        }

        current.add(new FoodItem(description, 30)); // start at 30 minutes

        items.setValue(new ArrayList<>(current));

        startTicking();
    }

    private void startTicking() {
        if (ticking) return;
        ticking = true;

        handler.post(tickRunnable);
    }

    private final Runnable tickRunnable = new Runnable() {
        @Override
        public void run() {
            List<FoodItem> current = items.getValue();
            if (current == null || current.isEmpty()) {
                ticking = false;
                return;
            }

            List<FoodItem> updated = new ArrayList<>(current);

            // decrement timer and remove expired items
            Iterator<FoodItem> iterator = updated.iterator();
            while (iterator.hasNext()) {
                FoodItem item = iterator.next();
                int left = item.getMinutesLeft() - 1;
                if (left <= 0) {
                    iterator.remove();
                } else {
                    item.setMinutesLeft(left);
                }
            }

            items.setValue(updated);

            if (updated.isEmpty()) {
                ticking = false;
            } else {
                handler.postDelayed(this, 60000); // 1 minute
            }
        }
    };

    @Override
    protected void onCleared() {
        super.onCleared();
        handler.removeCallbacksAndMessages(null);
        ticking = false;
    }
}
