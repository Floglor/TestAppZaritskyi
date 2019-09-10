package com.example.floggy.testappzaritskyi.interfaces;

import com.example.floggy.testappzaritskyi.models.ProductData;

import java.util.ArrayList;

public interface ISearchResultsFragment {
    void showToast(String toastMessage);
    void populateRecyclerView(ArrayList<ProductData> productList);
    void changeActivityToItemView(String message_name, ProductData message);
}
