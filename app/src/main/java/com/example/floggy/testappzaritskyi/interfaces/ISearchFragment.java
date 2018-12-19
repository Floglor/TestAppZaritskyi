package com.example.floggy.testappzaritskyi.interfaces;

import com.example.floggy.testappzaritskyi.models.ProductData;

import java.util.ArrayList;
import java.util.List;

public interface ISearchFragment {
    void showToast(String toastMessage);
    void populateAutoComplete(List<String> menu);
    String getCategory();
    void changeActivityToProductView(Class class_, String message_name, String message, String product_data_name, ArrayList<ProductData> productDataArrayList);
}
