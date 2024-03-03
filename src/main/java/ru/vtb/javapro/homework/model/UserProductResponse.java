package ru.vtb.javapro.homework.model;

public class UserProductResponse {
    private String product;
    private String productType;
    private String name;
    private String accountNumber;
    private float balance;

    public UserProductResponse(String product, String productType, String name, String accountNumber, float balance) {
        this.product = product;
        this.productType = productType;
        this.name = name;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
}
