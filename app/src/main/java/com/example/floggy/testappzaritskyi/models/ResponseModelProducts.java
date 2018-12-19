package com.example.floggy.testappzaritskyi.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResponseModelProducts {
    @SerializedName("count")
    @Expose
    private int count;
    @SerializedName("results")
    @Expose
    private ArrayList<ProductData> productDataArrayList;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<ProductData> getProductDataArrayList() {
        return productDataArrayList;
    }

    public void setProductDataArrayList(ArrayList<ProductData> productDataArrayList) {
        this.productDataArrayList = productDataArrayList;
    }
}
