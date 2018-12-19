package com.example.floggy.testappzaritskyi.interfaces;

import com.example.floggy.testappzaritskyi.models.ResponseModelCategories;
import com.example.floggy.testappzaritskyi.models.ResponseModelProducts;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IUserAPI {

    @GET("taxonomy/categories")
    Call<ResponseModelCategories> getCategories(@Query("api_key") String api_key,
                                                @Query("fields") String fields);

    @GET("listings/active")
    Call<ResponseModelProducts> getProduct(@Query("api_key") String api_key,
                                           @Query("state") String state,
                                           @Query("category") String category,
                                           @Query("fields") String fields,
                                           @Query("includes") String include,
                                           @Query("page") String page);
}
