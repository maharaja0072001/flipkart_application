package com.flipkart.product.clothes.types;

import com.flipkart.product.clothes.Clothes;

public class Shirt extends Clothes {

    public Shirt(final String gender, final String size, final float price, final String brand) {
        super("Tshirt", gender, size, price, brand);
    }
}
