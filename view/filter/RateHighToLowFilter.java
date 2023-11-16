package com.flipkart.view.filter;

import com.flipkart.product.ProductImpl;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

public class RateHighToLowFilter implements ProductFilter {

    private static final List<ProductImpl> FILTERED_ITEMS = new ArrayList<>();
    private static RateHighToLowFilter rateHighToLowFilterInstance;

    private RateHighToLowFilter() {}

    public static synchronized RateHighToLowFilter getInstance() {
        if (rateHighToLowFilterInstance == null) {
            rateHighToLowFilterInstance = new RateHighToLowFilter();
        }
        return rateHighToLowFilterInstance;
    }

    public List<ProductImpl> getFilteredItems(final Map<String, ArrayList<ProductImpl>> inventory, final String productType) {
        FILTERED_ITEMS.clear();
        FILTERED_ITEMS.addAll(inventory.get(productType));
        FILTERED_ITEMS.sort(new RateHighToLowComparator());
        return FILTERED_ITEMS;
    }
}