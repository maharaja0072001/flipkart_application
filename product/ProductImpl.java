package com.flipkart.product;

public class ProductImpl implements Product {

    private final String productType;
    private int price;
    private final String brandName;

    public ProductImpl(final String productType, final int price, final String brandName) {
        this.productType = productType;
        this.price = price;
        this.brandName = brandName;
    }

    public String getProductType() {
        return productType;
    }

    public int getPrice() {
        return price;
    }

    public String getBrandName() {
        return brandName;
    }

    public void changePrice(final int price) {
        this.price = price;
    }
}