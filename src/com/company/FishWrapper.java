package com.company;

public class FishWrapper {
    String[] fish;
    boolean direction;
    int startingRow;
    String[] colors;

    public FishWrapper(String[] fish ,String[] colors, boolean direction,int StartingRow){
        this.fish = fish;
        this.direction = direction;
        this.startingRow = StartingRow;
        this.colors = colors;
    }
}
