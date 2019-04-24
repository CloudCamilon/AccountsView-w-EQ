package com.finastra.accountsview.data;

/**
 *      {
 *         "productId": "0001000100001",
 *         "productName": "Fusion O",
 *         "productAccountType": "Current Account",
 *      }
 */
public class CustomerAccount {
    private String accountId;
    private String accountName;
    private String accountType;

    public CustomerAccount(String accountId, String accountName, String accountType) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.accountType = accountType;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getAccountType() {
        return accountType;
    }


}

