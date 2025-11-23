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

    // full list of items
    private final List<FoodItem> allItems = new ArrayList<>();

    // list currently shown in the UI (after filtering)
    private final MutableLiveData<List<FoodItem>> items =
            new MutableLiveData<>(new ArrayList<FoodItem>());

    private final Handler handler = new Handler(Looper.getMainLooper());
    private boolean ticking = false;

    // keep track of current search query so ticking doesn’t break it
    private String currentQuery = "";

    public LiveData<List<FoodItem>> getItems() {
        return items;
    }

    public void addDonation(String title, String description, int minutes) {
        if (title == null || title.trim().isEmpty()) return;
        if (minutes <= 0) return;

        FoodItem item = new FoodItem(title.trim(), description == null ? "" : description.trim(), minutes);
        allItems.add(item);

        // re-apply filter so new item appears (or not) depending on query
        applyFilter();

        startTicking();
    }

    /** Called from Dashboard fragment SearchView */
    public void filter(String query) {
        currentQuery = (query == null) ? "" : query;
        applyFilter();
    }

    private void applyFilter() {
        List<FoodItem> result = new ArrayList<>();

        if (currentQuery == null || currentQuery.trim().isEmpty()) {
            result.addAll(allItems);
        } else {
            String q = currentQuery.trim().toLowerCase();
            for (FoodItem item : allItems) {
                if (item.getTitle().toLowerCase().contains(q) ||
                        item.getDescription().toLowerCase().contains(q)) {
                    result.add(item);
                }
            }
        }

        items.setValue(result);
    }

    // ---------- timer logic (minutes) ----------

    private void startTicking() {
        if (ticking) return;
        ticking = true;
        handler.post(tickRunnable);
    }

    private final Runnable tickRunnable = new Runnable() {
        @Override
        public void run() {
            if (allItems.isEmpty()) {
                ticking = false;
                return;
            }

            Iterator<FoodItem> it = allItems.iterator();
            while (it.hasNext()) {
                FoodItem item = it.next();
                int left = item.getMinutesLeft() - 1;
                if (left <= 0) {
                    it.remove();
                } else {
                    item.setMinutesLeft(left);
                }
            }

            // update UI based on new allItems (+ current filter)
            applyFilter();

            if (allItems.isEmpty()) {
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
