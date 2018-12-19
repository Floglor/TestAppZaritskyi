package com.example.floggy.testappzaritskyi.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResponseModelCategories {
    @SerializedName("count")
    @Expose
    private int count;
    @SerializedName("results")
    @Expose
    private ArrayList<CategoryData> categoryDataList;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<CategoryData> getCategoryDataList() {
        return categoryDataList;
    }

    public void setCategoryDataList(ArrayList<CategoryData> categoryDataList) {
        this.categoryDataList = categoryDataList;
    }
}
