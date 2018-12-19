package com.example.floggy.testappzaritskyi.presenters;

import android.content.Context;
import android.util.Log;

import com.example.floggy.testappzaritskyi.MainApplication;
import com.example.floggy.testappzaritskyi.interfaces.ISearchResultsFragment;
import com.example.floggy.testappzaritskyi.models.CategoryData;
import com.example.floggy.testappzaritskyi.models.ProductData;
import com.example.floggy.testappzaritskyi.models.ProductORM;
import com.example.floggy.testappzaritskyi.models.ResponseModelProducts;
import com.example.floggy.testappzaritskyi.models.TextWorker;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchResultPresenter {
    private ISearchResultsFragment view;
    private CategoryData data;
    private Context context;
    final String TAG = "SearchResultPresenter";

    private ArrayList<ProductData> productDataArrayList;

    public void attachView(ISearchResultsFragment view, String categoryName, ArrayList<ProductData> dataArrayList, Context context) {
        this.view = view;
        this.productDataArrayList = dataArrayList;
        Log.i(TAG, "attachView: product data arrayList =" + productDataArrayList.toString());
        categoryName = TextWorker.decapitalizeText(categoryName);
        List<CategoryData> categories = ProductORM.getCategories(context);
        for (CategoryData i:
             categories) {
            if (i.getName().equals(categoryName)) {
                data = i;
            }
        }

    }

    private void loadProducts() {
        Log.i(TAG, "loadProducts: start");
        MainApplication.apiManager = new APIManager();
        MainApplication.apiManager.getProduct(data.getName(), "2", new Callback<ResponseModelProducts>() {
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
            }

            @Override
            public void onFailure(Call<ResponseModelProducts> call, Throwable t) {
                Log.e(TAG, "onFailure: fail");
            }
        });
    }

    void onBindProductRowAtPosition(ProductsRecyclerViewHolder holder, int position) {
        ProductData productData = productDataArrayList.get(position);
        holder.bind(productData, new ProductsRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ProductData item) {
                //TODO: add item view
                view.showToast("placeholder for item click");
            }
        });
    }

    public int getProductsRowsCount(){
        return productDataArrayList.size();
    }


    public void detachView() {
        view = null;
    }
}
