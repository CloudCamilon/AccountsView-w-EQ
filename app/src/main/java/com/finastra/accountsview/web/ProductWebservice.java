package com.finastra.accountsview.web;

import com.finastra.accountsview.data.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ProductWebservice {
    /**
     * @GET declares an HTTP GET request
     */
    @GET("account-products")
    Call<List<Product>> getList();
}