package com.example.floggy.testappzaritskyi.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.floggy.testappzaritskyi.R;
import com.example.floggy.testappzaritskyi.interfaces.ISearchFragment;
import com.example.floggy.testappzaritskyi.models.ProductData;
import com.example.floggy.testappzaritskyi.presenters.SearchFragmentPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class SearchFragment extends Fragment implements ISearchFragment {
    private Unbinder unbinder;

    @BindView(R.id.autoCompleteTV)
    AutoCompleteTextView autoCompleteTextView;
    @BindView(R.id.submitBT)
    Button submitButton;

    private SearchFragmentPresenter presenter;

    private final String TAG = "SearchFragment";


    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: start");
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_search, container, false);
        unbinder = ButterKnife.bind(this, view);
        presenter = new SearchFragmentPresenter();
        presenter.attachView(this, getContext());
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        presenter.detachView();
    }


    @Override
    public void showToast(String toastMessage) {
        Toast.makeText(getContext(), toastMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void populateAutoComplete(List<String> menu) {
        Log.i(TAG, "populateAutoComplete: menu = " + menu);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.auto_colpete_adapter_layout, R.id.autoCompleteTemplate, menu);
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setAdapter(adapter);
    }

    @Override
    public String getCategory() {
        return autoCompleteTextView.getText().toString();
    }

    @Override
    public void changeActivityToProductView(Class class_, String message_name, String message, String product_data_name, ArrayList<ProductData> productDataArrayList) {
        Intent intent = new Intent(getActivity(), class_);
        intent.putExtra(message_name, message);
        intent.putParcelableArrayListExtra(product_data_name, productDataArrayList);
        startActivity(intent);
    }


    @OnClick(R.id.submitBT)
    void setSubmitButton(View view) {
        presenter.showProductList();
    }

}
