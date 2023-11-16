package com.flipkart.service;

import com.flipkart.product.ProductImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface InventoryService {

    void addItemsToInventory(final ProductImpl item);
    void addItemsToInventory(final List<ProductImpl> items);
    void removeItemFromInventory(final ProductImpl item);
    void removeItemFromInventory(final List <ProductImpl> items);
    Map<String, ArrayList<ProductImpl>> getInventoryItems();
}
