package com.example.floggy.testappzaritskyi.interfaces;

import com.example.floggy.testappzaritskyi.models.ProductData;
import com.example.floggy.testappzaritskyi.presenters.ProductsRecyclerAdapter;

public interface ISearchResultsRowView {

    void setTitle(String title);

    void setImage(String image_url);

    void setDescription(String description);

    void bind(final ProductData item, final ProductsRecyclerAdapter.OnItemClickListener listener);

}
