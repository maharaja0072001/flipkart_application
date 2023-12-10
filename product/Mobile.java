package com.flipkart.product;

public class Mobile extends Product {

    private final String model;

    public Mobile(final String brandName, final String model, final float price) {
        super("Mobile", price, brandName);
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public String toString() {
        return String.format("%s : %s - Rs : %.2f", super.getBrandName(), model, super.getPrice());
    }
}