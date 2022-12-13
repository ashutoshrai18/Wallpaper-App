package com.ashutosh.wallpapertest.model;

public class ColorsModel {
    int id;
    String colorName;
    int color;

    public ColorsModel(int id, String colorName, int color) {
        this.id = id;
        this.colorName = colorName;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
