package com.ashutosh.wallpapertest.model;

public class Model {
    int id;
    String  pageURL, originalURL;

    public Model(int id, String pageURL, String originalURL) {
        this.id = id;
        this.pageURL = pageURL;
        this.originalURL = originalURL;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPageURL() {
        return pageURL;
    }

    public void setPageURL(String pageURL) {
        this.pageURL = pageURL;
    }

    public String getOriginalURL() {
        return originalURL;
    }

    public void setOriginalURL(String originalURL) {
        this.originalURL = originalURL;
    }
}
