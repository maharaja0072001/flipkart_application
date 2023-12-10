package com.flipkart.product.clothes;

import com.flipkart.product.Product;

public abstract class Clothes extends Product {

    private final String gender;
    private final String size;
    private final String clothesType;

    public Clothes(final String clothesType, final String gender,
                   final String size, final float price, final String brandName) {
        super("Clothes", price, brandName);
        this.gender = gender;
        this.size = size;
        this.clothesType = clothesType;
    }

    public String getClothesType() {
        return clothesType;
    }

    public String getGender() {
        return gender;
    }

    public String getSize() {
        return size;
    }

    @Override
    public String toString() {
        return String.format("%s - %s : %s : %s : %.2f", clothesType,
                super.getBrandName(), gender, size, super.getPrice());
    }
}
