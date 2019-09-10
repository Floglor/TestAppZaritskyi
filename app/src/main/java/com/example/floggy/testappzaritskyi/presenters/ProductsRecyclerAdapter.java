package com.example.floggy.testappzaritskyi.presenters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.floggy.testappzaritskyi.R;
import com.example.floggy.testappzaritskyi.models.ProductData;

import java.util.List;

public class ProductsRecyclerAdapter extends RecyclerView.Adapter<ProductsRecyclerViewHolder> {

    private List<ProductData> mProducts;

    private SearchResultPresenter presenter;

    public interface OnItemClickListener {
        void onItemClick(ProductData item);
    }

    public ProductsRecyclerAdapter(List<ProductData> mProducts, SearchResultPresenter presenter) {
        this.presenter = presenter;
        this.mProducts = mProducts;
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

    public void add(ProductData data) {
        mProducts.add(data);
        notifyItemInserted(mProducts.size() - 1);
    }

    public void addAll(List<ProductData> dataList) {
        if (dataList != null)
            for (ProductData data : dataList) {
                add(data);
            }
    }

    @Override
    public int getItemCount() {
        return presenter.getProductsRowsCount();
    }
}
