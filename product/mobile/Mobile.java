package com.flipkart.product.mobile;

import com.flipkart.product.ProductImpl;

public class Mobile extends ProductImpl implements IMobile {

    private final String model;

    public String getModel() {
        return model;
    }

    public Mobile(final String brandName, final String model, final int price) {
        super("Mobile", price, brandName);
        this.model = model;
    }

    public String toString() {
        return String.format("%s : %s - Rs : %d", super.getBrandName(), model, super.getPrice());
    }
}