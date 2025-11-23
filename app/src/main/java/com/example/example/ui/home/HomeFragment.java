package com.example.example.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.example.R;
import com.example.example.ui.food.FoodViewModel;

import org.jspecify.annotations.NonNull;

public class HomeFragment extends Fragment {

    EditText editTextTitle;
    EditText editTextDescription;
    private Button buttonDonate;
    private FoodViewModel foodViewModel;
    private NumberPicker numberPickerMinutes;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        editTextTitle = root.findViewById(R.id.editTextTitle);
        editTextDescription = root.findViewById(R.id.editTextText);
        buttonDonate = root.findViewById(R.id.buttonDonate);
        numberPickerMinutes = root.findViewById(R.id.numberPickerMinutes);

        // set allowed range, eg 5–120 minutes
        numberPickerMinutes.setMinValue(5);
        numberPickerMinutes.setMaxValue(120);
        // default selection
        numberPickerMinutes.setValue(30);
        numberPickerMinutes.setWrapSelectorWheel(false);


        // SHARED ViewModel: this is the important part
        foodViewModel = new ViewModelProvider(requireActivity())
                .get(FoodViewModel.class);

        buttonDonate.setOnClickListener(v -> {
            String title = editTextTitle.getText().toString().trim();
            String desc = editTextDescription.getText().toString().trim();
            int minutes = numberPickerMinutes.getValue();

            if (!title.isEmpty()) {
                foodViewModel.addDonation(title, desc, minutes);
                editTextTitle.setText("");
                editTextDescription.setText("");
            }
        });


        return root;
    }

}
