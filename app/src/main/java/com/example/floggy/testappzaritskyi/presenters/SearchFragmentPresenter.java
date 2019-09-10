package com.example.floggy.testappzaritskyi.presenters;

import android.content.Context;
import android.util.Log;

import com.example.floggy.testappzaritskyi.MainApplication;
import com.example.floggy.testappzaritskyi.interfaces.ISearchFragment;
import com.example.floggy.testappzaritskyi.models.CategoryData;
import com.example.floggy.testappzaritskyi.models.ProductData;
import com.example.floggy.testappzaritskyi.models.ProductORM;
import com.example.floggy.testappzaritskyi.models.ResponseModelCategories;
import com.example.floggy.testappzaritskyi.models.ResponseModelProducts;
import com.example.floggy.testappzaritskyi.models.TextWorker;
import com.example.floggy.testappzaritskyi.views.SearchResultsActivity;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragmentPresenter {

    private Context context;
    private ISearchFragment view;
    private String TAG = "SearchFragmentPresenter";

    private ArrayList<ProductData> productDataArrayList;

    private CategoryData data;

    public static final String CATEGORY_MESSAGE_NAME = "category";
    public static final String PRODUCT_ARRAY_LIST_NAME = "product_data";


    public void attachView(ISearchFragment view, Context context) {
        this.view = view;
        this.context = context;
        loadCategories();
    }

    public void detachView() {
        view = null;
    }

    public void getProductData(JSONObject response, int pageIndex) {
        //TODO
    }

    private void loadCategories() {
        Log.i(TAG, "loadCategories: start");
        MainApplication.apiManager = new APIManager();
        MainApplication.apiManager.getCategoriesFromAPI(new Callback<ResponseModelCategories>() {
            @Override
            public void onResponse(Call<ResponseModelCategories> call, Response<ResponseModelCategories> response) {
                Log.i(TAG, "onResponse: start");
                ResponseModelCategories responseData = response.body();
                ArrayList<CategoryData> categories = responseData.getCategoryDataList();
                ArrayList<String> titles = new ArrayList<>();
                assert categories != null;
                for (CategoryData x :
                        categories) {
                    titles.add(x.getName());
                    Log.i(TAG, "onResponse: name = " + x.getName());
                }
                saveCategoriesToDB(categories);
                setCategories(titles);
            }

            @Override
            public void onFailure(Call<ResponseModelCategories> call, Throwable t) {
                view.showToast("Something with your internet connection");
                Log.e(TAG, "onFailure: FAILURE");
            }
        });
    }

    private void setCategories(List<String> categories) {
        categories = TextWorker.capitalizeResponseText(categories);
        view.populateAutoComplete(categories);
    }

    private void saveCategoriesToDB(ArrayList<CategoryData> categories) {
        //TODO: save to DataBase
                ProductORM.insertCategories(context, categories);
    }

    public void showProductList() {
        Log.i(TAG, "showProductList: start");
        loadProductsFirstPage();
        //TODO: Add loading animation
        String category = TextWorker.decapitalizeText(view.getCategory());
    }

    private void loadProductsFirstPage() {
        Log.i(TAG, "loadProductsFirstPage: start");
        MainApplication.apiManager = new APIManager();
        final String categoryName =  TextWorker.decapitalizeText(view.getCategory());
        MainApplication.apiManager.getProduct(categoryName, 2, new Callback<ResponseModelProducts>() {
            @Override
            public void onResponse(Call<ResponseModelProducts> call, Response<ResponseModelProducts> response) {
                ResponseModelProducts products = null;
                try {
                    products = response.body();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                assert products != null;
                productDataArrayList = products.getProductDataArrayList();
                Log.i(TAG, "onResponse: success");
                view.changeActivityToProductView(SearchResultsActivity.class, CATEGORY_MESSAGE_NAME, categoryName, PRODUCT_ARRAY_LIST_NAME, productDataArrayList);
            }

            @Override
            public void onFailure(Call<ResponseModelProducts> call, Throwable t) {
                Log.e(TAG, "onFailure: fail");
            }
        });
    }

    private void saveProductstoDB(ArrayList<ProductData> products) {
     //   ProductORM.insertProducts(products);
    }


}
