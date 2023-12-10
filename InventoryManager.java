package com.flipkart;

import com.flipkart.controller.InventoryController;
import com.flipkart.product.Clothes;
import com.flipkart.product.Laptop;
import com.flipkart.product.Mobile;
import com.flipkart.product.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *     Responsible for creating all products and adding them to the inventory
 * </p>
 *
 * @author Maharaja S
 * @version 1.0
 */
public class InventoryManager {

    private static final InventoryController INVENTORY_CONTROLLER = InventoryController.getInstance();
    private static InventoryManager inventoryManagerInstance;

    /**
     * <p>
     *     Default constructor of InventoryManager class. Kept private to restrict from
     *     creating object outside this class.
     * </p>
     */
    private InventoryManager() {}

    /**
     * <p>
     * Creates a single object of InventoryManager class and returns it.
     * </p>
     *
     * @return the single instance of InventoryManager class.
     */
    public static InventoryManager getInstance() {
        if (null == inventoryManagerInstance) {
            inventoryManagerInstance = new InventoryManager();
        }

        return inventoryManagerInstance;
    }
    /**
     * <p>
     * Creates all the products and add them to the inventory.
     * </p>
     */
    public void allAllItemsToInventory() {
        final List<Product> allProducts = new ArrayList<>();

        allProducts.add(new Mobile("Realme", "7", 17000));
        allProducts.add(new Mobile("Realme", "8", 16000));
        allProducts.add(new Mobile("Realme", "8i", 13000));
        allProducts.add(new Mobile("Realme", "Narzo N5", 14000));
        allProducts.add(new Mobile("Realme", "10", 11000));
        allProducts.add(new Mobile("Oppo", "Reno 10", 30000));
        allProducts.add(new Mobile("Oppo", "A78", 12000));
        allProducts.add(new Mobile("Oppo", "A17", 15000));
        allProducts.add(new Mobile("Oppo", "F23", 20000));
        allProducts.add(new Mobile("Oppo", "F22", 25000));
        allProducts.add(new Mobile("Samsung", "A23", 25000));
        allProducts.add(new Mobile("Samsung", "F23", 22000));
        allProducts.add(new Mobile("Samsung", "M14", 17000));
        allProducts.add(new Mobile("Samsung", "S22 ultra", 95000));
        allProducts.add(new Mobile("Samsung", "M15", 19000));

        allProducts.add(new Laptop("Dell", "14", 46000));
        allProducts.add(new Laptop("Dell", "15", 38000));
        allProducts.add(new Laptop("Dell", "Inspiron 3525", 43000));
        allProducts.add(new Laptop("Dell", "Inspiron 5620", 80000));
        allProducts.add(new Laptop("Dell", "Latitude", 23000));
        allProducts.add(new Laptop("Acer", "Aspire Lite", 30000));
        allProducts.add(new Laptop("Acer", "Aspire Lite", 30000));
        allProducts.add(new Laptop("Acer", "Aspire 3", 42000));
        allProducts.add(new Laptop("Acer", "one 14", 23000));
        allProducts.add(new Laptop("Acer", "Extensa", 27500));
        allProducts.add(new Laptop("Acer", "Aspire 5", 51000));
        allProducts.add(new Laptop("Hp", "15s", 35000));
        allProducts.add(new Laptop("Hp", "15", 50000));
        allProducts.add(new Laptop("Hp", "14", 37000));
        allProducts.add(new Laptop("Hp", "Pavilion 15", 77000));
        allProducts.add(new Laptop("Hp", "14s", 39000));

        allProducts.add(new Clothes("Tshirt", "Men", "M", 600, "Levi's"));
        allProducts.add(new Clothes("Tshirt", "Men", "L", 1000, "MAX"));
        allProducts.add(new Clothes("Tshirt", "Women", "XXL", 2500, "Levi's"));
        allProducts.add(new Clothes("Tshirt", "Women", "M", 9000, "MAX"));
        allProducts.add(new Clothes("Tshirt", "Men" , "L", 1500, "JACK & JONES"));
        allProducts.add(new Clothes("Tshirt", "Men", "S", 800, "Levi's"));
        allProducts.add(new Clothes("Tshirt", "Men", "L", 1000, "JACK & JONES"));
        allProducts.add(new Clothes("Tshirt", "Women", "XXL", 2500, "Levi's"));
        allProducts.add(new Clothes("Tshirt", "Women", "M", 2200, "JACK & JONES"));
        allProducts.add(new Clothes("Tshirt", "Men" , "L", 1600, "Levi's"));
        allProducts.add(new Clothes("Shirt", "Men", "M", 1600,"MAX"));
        allProducts.add(new Clothes("Shirt", "Men", "L", 1000, "JACK & JONES"));
        allProducts.add(new Clothes("Shirt", "Women", "XXL", 3500, "JACK & JONES"));
        allProducts.add(new Clothes("Shirt", "Women", "M", 800, "MAX"));
        allProducts.add(new Clothes("Shirt", "Men" , "L", 1500, "JACK & JONES"));
        allProducts.add(new Clothes("Shirt", "Men", "M", 1600, "JACK & JONES"));
        allProducts.add(new Clothes("Shirt", "Men", "L", 7000, "MAX"));
        allProducts.add(new Clothes("Shirt", "Women", "XXL", 2300, "Levi's"));
        allProducts.add(new Clothes("Shirt", "Women", "M", 1800, "Levi's"));
        allProducts.add(new Clothes("Shirt", "Men" , "L", 5000, "JACK & JONES"));
        allProducts.add(new Clothes("Pants", "Men", "M", 1600, "JACK & JONES"));
        allProducts.add(new Clothes("Pants", "Men", "L", 1200, "MAX"));
        allProducts.add(new Clothes("Pants", "Women", "XXL", 2500, "JACK & JONES"));
        allProducts.add(new Clothes("Pants", "Women", "M", 5800, "Levi's"));
        allProducts.add(new Clothes("Pants", "Men" , "L", 1500, "MAX"));
        allProducts.add(new Clothes("Pants", "Men", "M", 2600, "JACK & JONES"));
        allProducts.add(new Clothes("Pants", "Men", "L", 4000, "Levi's"));
        allProducts.add(new Clothes("Pants", "Women", "XXL", 3500, "MAX"));
        allProducts.add(new Clothes("Pants", "Women", "M", 800, "MAX"));
        allProducts.add(new Clothes("Pants", "Women" , "L", 1500, "JACK & JONES"));
        INVENTORY_CONTROLLER.addItemToInventory(allProducts);
    }
}
