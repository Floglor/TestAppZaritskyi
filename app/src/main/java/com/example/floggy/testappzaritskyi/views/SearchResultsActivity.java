package com.example.floggy.testappzaritskyi.views;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.floggy.testappzaritskyi.R;
import com.example.floggy.testappzaritskyi.interfaces.ISearchResultsFragment;
import com.example.floggy.testappzaritskyi.models.ProductData;
import com.example.floggy.testappzaritskyi.presenters.ProductsRecyclerAdapter;
import com.example.floggy.testappzaritskyi.presenters.SearchFragmentPresenter;
import com.example.floggy.testappzaritskyi.presenters.SearchResultPresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SearchResultsActivity extends AppCompatActivity implements ISearchResultsFragment {
    private static final String TAG = "SearchResultsActivity";
    private SearchResultPresenter presenter;
    ProductsRecyclerAdapter adapter;


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
        presenter = new SearchResultPresenter();
        String category = getIntent().getExtras().getString(SearchFragmentPresenter.CATEGORY_MESSAGE_NAME);
        ArrayList<ProductData> data = getIntent().getExtras().getParcelableArrayList(SearchFragmentPresenter.PRODUCT_ARRAY_LIST_NAME);
        presenter.attachView(this, category, data, this);

        adapter = new ProductsRecyclerAdapter(presenter);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
    }


    @Override
    public void showToast(String toastMessage) {
        Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void populateRecyclerView(ArrayList<ProductData> productList) {
        adapter = new ProductsRecyclerAdapter(presenter);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
