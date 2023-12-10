package com.flipkart.service.impl2;

import com.flipkart.dao.Impl.InventoryDAOImpl;
import com.flipkart.product.Product;
import com.flipkart.service.InventoryService;

import java.util.List;

public class InventoryServiceImpl implements InventoryService {

    private static final InventoryDAOImpl INVENTORY_DAO = InventoryDAOImpl.getInstance();
    private static InventoryService inventoryInstance;

    private InventoryServiceImpl() {
    }

    public static synchronized InventoryService getInstance() {
        if (null == inventoryInstance) {
            inventoryInstance = new InventoryServiceImpl();
        }

        return inventoryInstance;
    }

    @Override
    public void addItemToInventory(final List<Product> products) {
        INVENTORY_DAO.addItemToInventory(products);
    }

    @Override
    public void removeItemFromInventory(final Product product) {
        INVENTORY_DAO.removeItemFromInventory(product);
    }

    @Override
    public List<Product> getMobileItems() {
        return null;
    }

    @Override
    public List<Product> getLaptopItems() {
        return null;
    }

    @Override
    public List<Product> getClothesItems() {
        return null;
    }
}