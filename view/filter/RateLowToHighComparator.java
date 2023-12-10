package com.flipkart.view.filter;

import com.flipkart.product.Product;

import java.util.Comparator;

/**
 * <p>
 * Responsible for comparing the product from price low to high
 * </p>
 *
 * @author Maharaja S
 * @version 1.0
 */
public class RateLowToHighComparator implements Comparator<Product> {

    /**
     * <p>
     * Compares two product based on their price and return the subtracted value
     * </p>
     *
     * @return the value got by subtracting the price of two products.
     */
    @Override
    public int compare(final Product product1, final Product product2) {
        return (int) (product1.getPrice() - product2.getPrice());
    }
}
