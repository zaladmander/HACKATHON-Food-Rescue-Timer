package com.example.example.ui.food;
public class FoodItem {

    private final String description;
    private int minutesLeft;

    public FoodItem(String description, int minutesLeft) {
        this.description = description;
        this.minutesLeft = minutesLeft;
    }

    public String getDescription() {
        return description;
    }

    public int getMinutesLeft() {
        return minutesLeft;
    }

    public void setMinutesLeft(int minutesLeft) {
        this.minutesLeft = minutesLeft;
    }
}

