package com.example.floggy.testappzaritskyi.views;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.example.floggy.testappzaritskyi.R;
import com.example.floggy.testappzaritskyi.presenters.SearchFragmentPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private SearchFragmentPresenter presenter;

    @BindView(R.id.viewPager)
    protected ViewPager viewPager;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        adapterInit();



    }

    public void adapterInit() {
        TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager());

        viewPager.setAdapter(tabAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.setOffscreenPageLimit(0);

        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_search));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_saved));


        View root = tabLayout.getChildAt(0);
        if (root instanceof LinearLayout) {
            ((LinearLayout) root).setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
            GradientDrawable drawable = new GradientDrawable();
            drawable.setColor(getResources().getColor(android.R.color.black));
            drawable.setSize(5, 0);
            ((LinearLayout) root).setDividerDrawable(drawable);

        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

             //  if (tabLayout.getSelectedTabPosition() == 0) {

             //  } else {

             //  }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }





}
