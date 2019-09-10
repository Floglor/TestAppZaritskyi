package com.example.floggy.testappzaritskyi;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.floggy.testappzaritskyi.models.ProductData;
import com.example.floggy.testappzaritskyi.presenters.SearchFragmentPresenter;
import com.example.floggy.testappzaritskyi.presenters.SearchResultPresenter;

import org.apache.commons.text.StringEscapeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class item_screen extends AppCompatActivity {
    @BindView(R.id.productViewMainIV)
    ImageView imageViewMain;
    @BindView(R.id.productViewTVDescr)
    TextView textViewDescription;
    @BindView(R.id.productViewTVName)
    TextView textViewName;
    @BindView(R.id.productViewTVPrice)
    TextView textViewPrice;


    ProductData product;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_screen);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        product = getIntent().getExtras().getParcelable(SearchResultPresenter.ITEM_MESSAGE);


        Glide
                .with(imageViewMain.getContext())
                .applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.ic_launcher_foreground)
                        .error(R.drawable.ic_launcher_foreground))
                .load(product.getImgURLData())
                .into(imageViewMain);
        textViewName.setText(StringEscapeUtils.unescapeHtml4(product.getTitle()));
        textViewPrice.setText(StringEscapeUtils.unescapeHtml4(product.getPrice()));
        textViewDescription.setText(StringEscapeUtils.unescapeHtml4(product.getDescription()));


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
