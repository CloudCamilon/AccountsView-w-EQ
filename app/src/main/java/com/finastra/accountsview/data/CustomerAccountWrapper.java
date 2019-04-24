package com.finastra.accountsview.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CustomerAccountWrapper {
    @SerializedName("items")
    @Expose
    private List<CustomerAccount> customerAccount = null;

    public List<CustomerAccount> getCustomerAccounts() {
        return customerAccount;
    }

    public void setContacts(List<CustomerAccount> customerAccounts) {
        this.customerAccount = customerAccounts;
    }

}
