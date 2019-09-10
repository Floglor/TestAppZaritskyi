package com.example.floggy.testappzaritskyi.views;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.floggy.testappzaritskyi.MainApplication;
import com.example.floggy.testappzaritskyi.R;
import com.example.floggy.testappzaritskyi.StaticDataStorage;
import com.example.floggy.testappzaritskyi.interfaces.ISearchResultsFragment;
import com.example.floggy.testappzaritskyi.item_screen;
import com.example.floggy.testappzaritskyi.models.ProductData;
import com.example.floggy.testappzaritskyi.models.ResponseModelProducts;
import com.example.floggy.testappzaritskyi.presenters.APIManager;
import com.example.floggy.testappzaritskyi.presenters.PaginationScrollListener;
import com.example.floggy.testappzaritskyi.presenters.ProductsRecyclerAdapter;
import com.example.floggy.testappzaritskyi.presenters.SearchFragmentPresenter;
import com.example.floggy.testappzaritskyi.presenters.SearchResultPresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchResultsActivity extends AppCompatActivity implements ISearchResultsFragment {
    private static final String TAG = "SearchResultsActivity";
    private SearchResultPresenter presenter;
    ProductsRecyclerAdapter adapter;

    private static final int PAGE_START = 1;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private static final int TOTAL_PAGES = 3;
    private int currentPage = PAGE_START;


    private int lastPage = 0;
    String category;
    ArrayList<ProductData> productDataArrayList;


    @BindView(R.id.productsRV)
    RecyclerView recyclerView;

    public SearchResultsActivity() {
        // Required empty public constructor
    }


    public void onCreate(Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_products);
        ButterKnife.bind(this);
        Log.i(TAG, "onCreate: start");
        ArrayList<ProductData> data;
        productDataArrayList = new ArrayList<>();
        presenter = new SearchResultPresenter();
        if (getIntent().getExtras() != null) {
            category = getIntent().getExtras().getString(SearchFragmentPresenter.CATEGORY_MESSAGE_NAME);
            data = getIntent().getExtras().getParcelableArrayList(SearchFragmentPresenter.PRODUCT_ARRAY_LIST_NAME);
            StaticDataStorage.setLastSearchCategory(category);
            StaticDataStorage.setLastSearchData(data);
        }
        else {
            category = StaticDataStorage.getLastSearchCategory();
            data = StaticDataStorage.getLastSearchData();
        }

        presenter.attachView(this, category, data, this);
        adapter = new ProductsRecyclerAdapter(data, presenter);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new PaginationScrollListener(layoutManager) {
            @Override
            protected void loadMoreItems() {
                Log.i(TAG, "loadMoreItems: SCROLL. isLoading = " + isLoading + ", currentPate =" + currentPage + ", TOTAL_PAGES = " + TOTAL_PAGES);
                if (!isLoading && currentPage < TOTAL_PAGES) {
                    isLoading = true;
                    //Increment page index to load the next one
                    Handler h = new Handler();
                    //topProgressBar.setVisibility(View.VISIBLE);
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            loadNextPage();
                        }
                    }, 0);
                }
            }

            @Override
            public int getTotalPageCount() {
                return 0;
            }

            @Override
            public boolean isLastPage() {
                return false;
            }

            @Override
            public boolean isLoading() {
                return false;
            }
        });

        loadNextPage();
    }

    private void loadNextPage() {
        if (currentPage == TOTAL_PAGES) {
            isLastPage = true;
            return;
        } else {
            isLoading = true;
            Log.i(TAG, "loadNextPage: current page +1");

            Log.i(TAG, "loadNextPage: start");
            //Log.d(TAG, "loadFirstPage: ");
            MainApplication.apiManager = new APIManager();
            MainApplication.apiManager.getProduct(category, currentPage, new Callback<ResponseModelProducts>() {
                @Override
                public void onResponse(Call<ResponseModelProducts> call, Response<ResponseModelProducts> response) {
                    ResponseModelProducts responseModelProducts = response.body();
                    assert responseModelProducts != null;
                    Log.i(TAG, "onResponse: response = " + responseModelProducts.toString());
                    ArrayList<ProductData> results = responseModelProducts.getProductDataArrayList();

                    if (results != null) {
                        Log.e(TAG, "onResponse: isNull = " + results);
                        if (currentPage == PAGE_START || adapter == null) {
                            if (adapter == null)
                            populateRecyclerView(results);
                            saveProductData(results);
                            Log.e(TAG, "onResponse: INITIALIZE");
                            currentPage +=1;
                            isLoading = false;
                            if (results.size() < 9)
                                isLastPage = true;
                        }
                        else {
                            adapter.addAll(results);
                            saveProductData(results);
                            Log.e(TAG, "onResponse: ADD");
                            currentPage +=1;
                            isLoading = false;
                            if (results.size() < 9)
                                isLastPage = true;
                        }
                    }
                }

                private void saveProductData (ArrayList<ProductData> data) {
                    productDataArrayList.addAll(data);
                    StaticDataStorage.setLastSearchData(productDataArrayList);
                }

                @Override
                public void onFailure(Call<ResponseModelProducts> call, Throwable t) {
                t.printStackTrace();
                }
            });

        }
    }

    public void changeActivityToItemView(String message_name, ProductData message) {
        Intent intent = new Intent(getApplicationContext(), item_screen.class);
        intent.putExtra(message_name, message);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        productDataArrayList.clear();
    }

    @Override
    public void showToast(String toastMessage) {
        Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void populateRecyclerView(ArrayList<ProductData> productList) {
        adapter = new ProductsRecyclerAdapter(productList, presenter);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
