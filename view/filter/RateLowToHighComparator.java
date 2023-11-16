package com.flipkart.view.filter;

import com.flipkart.product.ProductImpl;
import java.util.Comparator;

public class RateLowToHighComparator implements Comparator<ProductImpl> {

    @Override
    public int compare(final ProductImpl o1, final ProductImpl o2) {
        return o1.getPrice() - o2.getPrice();
    }
}
