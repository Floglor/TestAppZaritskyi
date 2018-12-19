package com.example.floggy.testappzaritskyi.views;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class TabAdapter extends FragmentStatePagerAdapter {

     TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return new SearchFragment();
            case 1:
                return new SavedProductsFragment();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
