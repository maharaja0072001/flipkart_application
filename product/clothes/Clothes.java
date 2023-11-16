package com.flipkart.product.clothes;

import com.flipkart.product.ProductImpl;

public class Clothes extends ProductImpl {

    private final String gender;
    private final String size;
    private final String clothesType;

    public Clothes(final String clothesType, final String gender,
                   final String size, final int price, final String brandName) {
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

    @Override
    public String toString() {
        return String.format("%s - %s : %s : %s : %d", clothesType,
                super.getBrandName(), gender, size, super.getPrice());
    }
}
