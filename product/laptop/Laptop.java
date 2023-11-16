package com.flipkart.product.laptop;

import com.flipkart.product.ProductImpl;

public class Laptop extends ProductImpl {

    private final String model;

    public String getModel() {
            return model;
    }

    public Laptop(final String brandName, final String model, final int price) {
            super("Laptop", price, brandName);
            this.model = model;
    }

    public String toString() {
        return String.format("%s : %s - Rs : %d", super.getBrandName(), model, super.getPrice());
    }
}

