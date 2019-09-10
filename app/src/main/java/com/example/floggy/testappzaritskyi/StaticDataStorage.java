package com.example.floggy.testappzaritskyi;

import com.example.floggy.testappzaritskyi.models.ProductData;

import java.util.ArrayList;

public class StaticDataStorage {
    private static ArrayList<ProductData> lastSearchData;
    private static String lastSearchCategory;
    private static int lastSearchPage;

    public static ArrayList<ProductData> getLastSearchData() {
        return lastSearchData;
    }

    public static void setLastSearchData(ArrayList<ProductData> lastSearchData) {
        StaticDataStorage.lastSearchData = lastSearchData;
    }

    public static String getLastSearchCategory() {
        return lastSearchCategory;
    }

    public static void setLastSearchCategory(String lastSearchCategory) {
        StaticDataStorage.lastSearchCategory = lastSearchCategory;
    }

    public static int getLastSearchPage() {
        return lastSearchPage;
    }

    public static void setLastSearchPage(int lastSearchPage) {
        StaticDataStorage.lastSearchPage = lastSearchPage;
    }
}
