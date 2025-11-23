package com.example.example.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.example.R;
import com.example.example.ui.food.FoodViewModel;

public class HomeFragment extends Fragment {

    private EditText editTextTitle;
    private EditText editTextDesc;
    private NumberPicker numberPickerMinutes;
    private Button buttonDonate;

    private FoodViewModel foodViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        editTextTitle = root.findViewById(R.id.editTextTitle);      // title field
        editTextDesc  = root.findViewById(R.id.editTextText);       // description field
        numberPickerMinutes = root.findViewById(R.id.numberPickerMinutes);
        buttonDonate = root.findViewById(R.id.buttonDonate);

        // shared ViewModel with activity
        foodViewModel = new ViewModelProvider(requireActivity())
                .get(FoodViewModel.class);

        // NumberPicker setup
        numberPickerMinutes.setMinValue(5);
        numberPickerMinutes.setMaxValue(120);
        numberPickerMinutes.setValue(30);
        numberPickerMinutes.setWrapSelectorWheel(false);

        buttonDonate.setOnClickListener(v -> {
            String title = editTextTitle.getText().toString().trim();
            String desc  = editTextDesc.getText().toString().trim();
            int minutes  = numberPickerMinutes.getValue();

            if (!title.isEmpty()) {
                foodViewModel.addDonation(title, desc, minutes);
                editTextTitle.setText("");
                editTextDesc.setText("");
            }
        });

        return root;
    }
}
