package com.flipkart.product;

public abstract class Product {
    private static int uniqueProductId = 1;
    private int productId;
    private final String productType;
    private float price;
    private final String brandName;


    public Product(final String productType, final float price, final String brandName) {
        this.productType = productType;
        this.price = price;
        this.brandName = brandName;
        productId = uniqueProductId++;
    }

    public void setProductId(final int productId) {
        this.productId = productId;
    }

    public String getProductType() {
        return productType;
    }

    public float getPrice() {
        return price;
    }

    public String getBrandName() {
        return brandName;
    }

    public void changePrice(final float price) {
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }
}