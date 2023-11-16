package com.flipkart.product.mobile;

import com.flipkart.service.InventoryService;
import com.flipkart.service.service_impl.InventoryServiceImpl;
import com.flipkart.product.ProductImpl;
import com.flipkart.product.mobile.brands.OppoMobile;
import com.flipkart.product.mobile.brands.RealmeMobile;
import com.flipkart.product.mobile.brands.SamsungMobile;
import java.util.ArrayList;
import java.util.List;

public class MobileInventory {

    private final static InventoryService INVENTORY = InventoryServiceImpl.getInstance();
    private final List<ProductImpl> realmeMobileCollection = new ArrayList<>();
    private final List<ProductImpl> oppoMobileCollection = new ArrayList<>();
    private final List<ProductImpl> samsungMobileCollection = new ArrayList<>();
    private final ProductImpl product = new RealmeMobile("7", 12300);
    private static MobileInventory mobileInventoryInstance;

    public static synchronized MobileInventory getInstance() {
        if (mobileInventoryInstance == null) {
            mobileInventoryInstance = new MobileInventory();
        }
        return mobileInventoryInstance;
    }

    public void addToInventory() {
        addAllMobiles();
        INVENTORY.addItemsToInventory(realmeMobileCollection);
        INVENTORY.addItemsToInventory(oppoMobileCollection);
        INVENTORY.addItemsToInventory(samsungMobileCollection);
        INVENTORY.addItemsToInventory(product);
    }

    private void addAllMobiles() {
        realmeMobileCollection.add(new RealmeMobile("7",17000));
        realmeMobileCollection.add(new RealmeMobile("8",16000));
        realmeMobileCollection.add(new RealmeMobile("8i",13000));
        realmeMobileCollection.add(new RealmeMobile("Narzo N5",14000));
        realmeMobileCollection.add(new RealmeMobile("10",11000));

        oppoMobileCollection.add(new OppoMobile("Reno 10",30000));
        oppoMobileCollection.add(new OppoMobile("A78" ,12000));
        oppoMobileCollection.add(new OppoMobile("A17" ,15000));
        oppoMobileCollection.add(new OppoMobile("F23" ,20000));
        oppoMobileCollection.add(new OppoMobile("F22" ,25000));

        samsungMobileCollection.add(new SamsungMobile("A23" ,25000));
        samsungMobileCollection.add(new SamsungMobile("F23" ,22000));
        samsungMobileCollection.add(new SamsungMobile("M14" ,17000));
        samsungMobileCollection.add(new SamsungMobile("S22 ultra" ,95000));
        samsungMobileCollection.add(new SamsungMobile("M15" ,19000));
    }
}