package com.ashutosh.wallpapertest.model;

public class CategoryModelList {
    String name, previewURL;

    public CategoryModelList(String name, String previewURL) {
        this.name = name;
        this.previewURL = previewURL;
    }

    public String getName() {
        return name;
    }

    public String getPreviewURL() {
        return previewURL;
    }
}