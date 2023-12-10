package com.flipkart.product.clothes.types;

import com.flipkart.product.clothes.Clothes;

public class Pants extends Clothes {

    public Pants(final String gender, final String size, final float price, final String brand) {
        super("Pants", gender, size, price, brand);
    }
}
