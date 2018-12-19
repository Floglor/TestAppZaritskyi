package com.example.floggy.testappzaritskyi.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryData {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("meta_keywords")
    @Expose
    private String keywords;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    @Override
    public String toString(){
        return name + " " + keywords;
    }
}
