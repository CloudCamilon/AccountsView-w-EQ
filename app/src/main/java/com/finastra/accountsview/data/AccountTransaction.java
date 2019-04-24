package com.finastra.accountsview.data;

/**
 *      {
 *         "productId": "0001000100001",
 *         "productName": "Fusion O",
 *         "productAccountType": "Current Account",
 *      }
 */
public class AccountTransaction {
    private String valueDate;
    private String description;
    private String creditDebitIndicator;

    public AccountTransaction(String valueDate, String description, String creditDebitIndicator) {
        this.valueDate = valueDate;
        this.description = description;
        this.creditDebitIndicator = creditDebitIndicator;
    }

    public String getValueDate() {
        return valueDate;
    }

    public void setValueDate(String valueDate) {
        this.valueDate = valueDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreditDebitIndicator() {
        return creditDebitIndicator;
    }

    public void setCreditDebitIndicator(String creditDebitIndicator) {
        this.creditDebitIndicator = creditDebitIndicator;
    }


}


