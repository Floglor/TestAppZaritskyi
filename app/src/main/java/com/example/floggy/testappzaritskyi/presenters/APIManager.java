package com.example.floggy.testappzaritskyi.presenters;

import com.example.floggy.testappzaritskyi.interfaces.IUserAPI;
import com.example.floggy.testappzaritskyi.models.ResponseModelCategories;
import com.example.floggy.testappzaritskyi.models.ResponseModelProducts;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIManager {
    private static Retrofit retrofit = null;
    private static final String BASE_URL = "https://openapi.etsy.com/v2/";

    private String api_key = "t5qb5wa79es9m49n1rcu538a";

    private static IUserAPI service;
    private static APIManager apiManager;


    public APIManager() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        Gson gson = new GsonBuilder().
                setLenient().
                create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build();

        service = retrofit.create(IUserAPI.class);
    }

    public static APIManager getInstance() {
        if (apiManager == null) {
            apiManager = new APIManager();
        }
        return apiManager;
    }

    void getCategoriesFromAPI(Callback<ResponseModelCategories> callback) {
        String categoriesFields = "name,meta_keywords";
        Call<ResponseModelCategories> call = service.getCategories(api_key, categoriesFields);
        call.enqueue(callback);
    }

    public void getProduct(String category, int page, Callback<ResponseModelProducts> callback) {
        String productFields = "title,price,description";
        String state_active = "active";
        String include_img = "Images(url_75x75):1:0";
        String page_string = Integer.toString(page);
        Call<ResponseModelProducts> call = service.getProduct(api_key, state_active, category, productFields, include_img, page_string);
        call.enqueue(callback);
    }

}
