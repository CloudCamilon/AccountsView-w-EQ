package com.finastra.accountsview.data;

/**
 *      {
 *         "productId": "300",
 *         "productName": "Business CDs < $100M",
 *         "productAccountType": "CertficateOfDeposit",
 *         "productAccountSubType": "COD"
 *      }
 */
public class Product {
    private String productId;
    private String productName;
    private String productAccountType;
    private String productAccountSubType;

    public Product(String productId, String productName, String productAccountType, String productAccountSubType) {
        this.productId = productId;
        this.productName = productName;
        this.productAccountType = productAccountType;
        this.productAccountSubType = productAccountSubType;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductAccountType() {
        return productAccountType;
    }

    public void setProductAccountType(String productAccountType) {
        this.productAccountType = productAccountType;
    }

    public String getProductAccountSubType() {
        return productAccountSubType;
    }

    public void setProductAccountSubType(String productAccountSubType) {
        this.productAccountSubType = productAccountSubType;
    }
}
