package com.ashutosh.wallpapertest.model;

public class CategoryModelList {
    int id;
    String pageURL, tags, respose;
    String pageLink;

    public CategoryModelList(int id, String pageURL, String tags, String respose, String pageLink) {
        this.id = id;
        this.pageURL = pageURL;
        this.tags = tags;
        this.respose = respose;
        this.pageLink = pageLink;
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

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getRespose() {
        return respose;
    }

    public void setRespose(String respose) {
        this.respose = respose;
    }

    public String getPageLink() {
        return pageLink;
    }

    public void setPageLink(String pageLink) {
        this.pageLink = pageLink;
    }
}
