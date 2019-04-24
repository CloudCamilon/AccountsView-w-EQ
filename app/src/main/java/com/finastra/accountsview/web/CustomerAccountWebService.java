package com.finastra.accountsview.web;

import com.finastra.accountsview.data.CustomerAccount;
import com.finastra.accountsview.data.CustomerAccountWrapper;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface CustomerAccountWebService {
    /**
     * @GET declares an HTTP GET request
     */
    @GET("customers/0001000002001/accounts")
    Call<CustomerAccountWrapper> getCustomerAccounts();
}