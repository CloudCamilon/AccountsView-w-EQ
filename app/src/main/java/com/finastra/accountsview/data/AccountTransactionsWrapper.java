package com.finastra.accountsview.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AccountTransactionsWrapper {
    @SerializedName("items")
    @Expose
    private List<AccountTransaction> accountTransaction = null;

    public List<AccountTransaction> getAccountTransactions() {
        return accountTransaction;
    }

    public void setAccountTransactions(List<AccountTransaction> accountTransaction) {
        this.accountTransaction = accountTransaction;
    }

}
