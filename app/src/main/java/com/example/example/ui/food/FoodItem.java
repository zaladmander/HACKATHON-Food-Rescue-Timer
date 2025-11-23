package com.example.example.ui.food;
public class FoodItem {

    private final String title;
    private final String description;
    private int minutesLeft;

    public FoodItem(String title, String description, int minutesLeft) {
        this.title = title;
        this.description = description;
        this.minutesLeft = minutesLeft;
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public int getMinutesLeft() { return minutesLeft; }
    public void setMinutesLeft(int minutesLeft) { this.minutesLeft = minutesLeft; }
}
