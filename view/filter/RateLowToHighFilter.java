package com.flipkart.view.filter;

import com.flipkart.product.ProductImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RateLowToHighFilter implements ProductFilter {

    private static final ArrayList<ProductImpl> FILTERED_ITEMS = new ArrayList<>();
    private static RateLowToHighFilter rateLowToHighFilterInstance;

    private RateLowToHighFilter() {}

    public static synchronized RateLowToHighFilter getInstance() {
        if (rateLowToHighFilterInstance == null) {
            rateLowToHighFilterInstance = new RateLowToHighFilter();
        }
        return rateLowToHighFilterInstance;
    }

    public List<ProductImpl> getFilteredItems(final Map<String, ArrayList<ProductImpl>> inventory, final String productType) {
        FILTERED_ITEMS.clear();
        FILTERED_ITEMS.addAll(inventory.get(productType));
        FILTERED_ITEMS.sort(new RateLowToHighComparator());
        return FILTERED_ITEMS;
    }
}