package com.flipkart.view.filter;

import com.flipkart.product.ProductImpl;
import java.util.Comparator;

public class RateHighToLowComparator implements Comparator<ProductImpl> {

    @Override
    public int compare(final ProductImpl o1, final ProductImpl o2) {
        return o2.getPrice() - o1.getPrice();
    }
}