package com.flipkart.service;

import com.flipkart.model.product.Product;

import java.util.List;

/**
 * <p>
 * Provides service for the Inventory.
 * </p>
 *
 * @author Maharaja S
 * @version 1.0
 */
public interface InventoryService {

    void addItemToInventory(final List<Product> products);
    void removeItemFromInventory(final Product product);
    List<Product> getMobileItems();
    List<Product> getLaptopItems();
    List<Product> getClothesItems();
}
