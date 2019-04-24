package com.finastra.accountsview.web;

import com.finastra.accountsview.data.AccountTransactionsWrapper;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface AccountTransactionsWebService {
    /**
     * @GET declares an HTTP GET request
     */
    @GET("accounts/0001000100001/transactions")
    Call<AccountTransactionsWrapper> getAccountTransactions();
}