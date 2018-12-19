package com.example.floggy.testappzaritskyi.presenters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.floggy.testappzaritskyi.R;
import com.example.floggy.testappzaritskyi.models.ProductData;

public class ProductsRecyclerAdapter extends RecyclerView.Adapter<ProductsRecyclerViewHolder> {

        private SearchResultPresenter presenter;

    public interface OnItemClickListener {
        void onItemClick(ProductData item);
    }

    public ProductsRecyclerAdapter(SearchResultPresenter presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ProductsRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new ProductsRecyclerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsRecyclerViewHolder productsRecyclerViewHolder, int i) {
        presenter.onBindProductRowAtPosition(productsRecyclerViewHolder, i);
    }

    @Override
    public int getItemCount() {
         return presenter.getProductsRowsCount();
    }
}
