package com.example.floggy.testappzaritskyi.presenters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.floggy.testappzaritskyi.R;
import com.example.floggy.testappzaritskyi.interfaces.ISearchResultsRowView;
import com.example.floggy.testappzaritskyi.models.ProductData;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ProductsRecyclerViewHolder extends RecyclerView.ViewHolder implements ISearchResultsRowView {

    private final String TAG = "RecyclerViewHolder";

    @BindView(R.id.imageView)
     ImageView imageView;
    @BindView(R.id.tvTitle)
     TextView titleTextView;
    @BindView(R.id.tvDescription)
    TextView descriptionTextView;
    

    public ProductsRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
     }

    @Override
    public void setTitle(String title) {
        titleTextView.setText(title);
    }

    @Override
    public void setImage(String image_url) {
        Log.i(TAG, "setImage: image url is " + image_url);
        Glide
                .with(imageView.getContext())
                .applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.ic_launcher_foreground)
                        .error(R.drawable.ic_launcher_foreground))
                .load(image_url)
                .into(imageView);
    }

    @Override
    public void setDescription(String description) {
        descriptionTextView.setText(description);
    }

    @Override
    public void bind(final ProductData item, final ProductsRecyclerAdapter.OnItemClickListener listener) {
        setImage(item.getImgURLData());
        setTitle(item.getTitle());
        setDescription(item.getPrice() + "$");
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                listener.onItemClick(item);
            }
        });
    }

}
