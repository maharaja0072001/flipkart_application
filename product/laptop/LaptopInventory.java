package com.flipkart.product.laptop;

import com.flipkart.service.InventoryService;
import com.flipkart.service.service_impl.InventoryServiceImpl;
import com.flipkart.product.ProductImpl;
import com.flipkart.product.laptop.brands.AcerLaptop;
import com.flipkart.product.laptop.brands.HpLaptop;
import com.flipkart.product.laptop.brands.DellLaptop;
import java.util.ArrayList;

public class LaptopInventory {

    private static final InventoryService INVENTORY = InventoryServiceImpl.getInstance();
    private final ArrayList<ProductImpl> hpLaptopCollection = new ArrayList<>();
    private final ArrayList<ProductImpl> acerLaptopCollection = new ArrayList<>();
    private final ArrayList<ProductImpl> dellLaptopCollection = new ArrayList<>();
    private final ProductImpl product = new HpLaptop("Chromebook 13", 20300);
    private static LaptopInventory laptopInventoryInstance;

    private LaptopInventory() {}

    public static synchronized LaptopInventory getInstance() {
        if (laptopInventoryInstance == null) {
        laptopInventoryInstance = new LaptopInventory();
        }
        return laptopInventoryInstance;
    }

    public void addToInventory() {
        addAllLaptops();
        INVENTORY.addItemsToInventory(dellLaptopCollection);
        INVENTORY.addItemsToInventory(acerLaptopCollection);
        INVENTORY.addItemsToInventory(hpLaptopCollection);
        INVENTORY.addItemsToInventory(product);
    }

    private void addAllLaptops() {
        dellLaptopCollection.add(new DellLaptop("14",46000));
        dellLaptopCollection.add(new DellLaptop("15",38000));
        dellLaptopCollection.add(new DellLaptop("Inspiron 3525",43000));
        dellLaptopCollection.add(new DellLaptop("Inspiron 5620",80000));
        dellLaptopCollection.add(new DellLaptop("Latitude",23000));

        acerLaptopCollection.add(new AcerLaptop("Aspire Lite",30000));
        acerLaptopCollection.add(new AcerLaptop("Aspire 3" ,42000));
        acerLaptopCollection.add(new AcerLaptop("one 14" ,23000));
        acerLaptopCollection.add(new AcerLaptop("Extensa" ,27500));
        acerLaptopCollection.add(new AcerLaptop("Aspire 5" ,51000));

        hpLaptopCollection.add(new HpLaptop("15s" ,35000));
        hpLaptopCollection.add(new HpLaptop("15" ,50000));
        hpLaptopCollection.add(new HpLaptop("14" ,37000));
        hpLaptopCollection.add(new HpLaptop("Pavilion 15" ,77000));
        hpLaptopCollection.add(new HpLaptop("14s" ,39000));
    }
}

