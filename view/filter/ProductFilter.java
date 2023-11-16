package com.flipkart.view.filter;

import com.flipkart.product.ProductImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface ProductFilter {

    List<ProductImpl> getFilteredItems(Map<String, ArrayList<ProductImpl>> inventory, String productType);
}
