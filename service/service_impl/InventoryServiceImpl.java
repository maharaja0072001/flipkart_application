package com.flipkart.service.service_impl;

import com.flipkart.product.ProductImpl;
import com.flipkart.service.InventoryService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryServiceImpl implements InventoryService {

    private static final Map<String, ArrayList<ProductImpl>> INVENTORY_ITEMS = new HashMap< >();
    private static InventoryService inventoryInstance;

    private InventoryServiceImpl(){}

    public static synchronized InventoryService getInstance(){
        if (inventoryInstance == null){
            inventoryInstance = new InventoryServiceImpl();
        }
        return inventoryInstance;
    }

    public void addItemsToInventory(final ProductImpl item) {
        if (INVENTORY_ITEMS.containsKey(item.getProductType())) {
            final ArrayList<ProductImpl> list = INVENTORY_ITEMS.get(item.getProductType());

            list.add(item);
        } else {
            final ArrayList<ProductImpl> listOfNewProduct = new ArrayList<>();

            INVENTORY_ITEMS.put(item.getProductType(), listOfNewProduct);
            listOfNewProduct.add(item);
        }
    }

    public void addItemsToInventory(final List<ProductImpl> items) {
        for (final ProductImpl eachItem : items) {
            if (INVENTORY_ITEMS.containsKey(eachItem.getProductType())) {
                final ArrayList<ProductImpl> list = INVENTORY_ITEMS.get(eachItem.getProductType());

                list.add(eachItem);
            } else {
                final ArrayList<ProductImpl> listOfNewProduct = new ArrayList<>();

                INVENTORY_ITEMS.put(eachItem.getProductType(), listOfNewProduct);
                listOfNewProduct.add(eachItem);
            }
        }
    }

    public void removeItemFromInventory(final ProductImpl item) {
        if (INVENTORY_ITEMS.containsKey(item.getProductType())) {
            final ArrayList<ProductImpl> list = INVENTORY_ITEMS.get(item.getProductType());

            list.remove(item);
        }
    }

    public void removeItemFromInventory(final List<ProductImpl> items) {
        for (final ProductImpl eachItem : items) {
            if (INVENTORY_ITEMS.containsKey(eachItem.getProductType())) {
                final ArrayList<ProductImpl> list = INVENTORY_ITEMS.get(eachItem.getProductType());

                list.remove(eachItem);
            }
        }
    }

    public Map<String, ArrayList<ProductImpl>> getInventoryItems() {
        return INVENTORY_ITEMS;
    }
}