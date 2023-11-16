package com.flipkart.product.clothes;

import com.flipkart.product.ProductImpl;
import com.flipkart.product.clothes.types.Pants;
import com.flipkart.product.clothes.types.Shirt;
import com.flipkart.product.clothes.types.Tshirt;
import com.flipkart.service.InventoryService;
import com.flipkart.service.service_impl.InventoryServiceImpl;
import java.util.ArrayList;
import java.util.List;

public class ClothesInventory {

    private static final InventoryService INVENTORY = InventoryServiceImpl.getInstance();
    private final List<ProductImpl> tshirtCollection = new ArrayList<>();
    private final List<ProductImpl> shirtCollection = new ArrayList<>();
    private final List<ProductImpl> pantsCollection = new ArrayList<>();
    private static ClothesInventory clothesInventoryInstance;

    private ClothesInventory() {}

    public static synchronized ClothesInventory getInstance() {
        if (clothesInventoryInstance == null) {
            clothesInventoryInstance = new ClothesInventory();
        }
        return clothesInventoryInstance;
    }

    public void addToInventory() {
        addAllClothes();
        INVENTORY.addItemsToInventory(tshirtCollection);
        INVENTORY.addItemsToInventory(shirtCollection);
        INVENTORY.addItemsToInventory(pantsCollection);
    }

    private void addAllClothes() {
        tshirtCollection.add(new Tshirt("Men", "M", 600, "Levi's"));
        tshirtCollection.add(new Tshirt("Men", "L", 1000, "MAX" ));
        tshirtCollection.add(new Tshirt("Women", "XXL", 2500, "Levi's"));
        tshirtCollection.add(new Tshirt("Women", "M", 9000, "MAX" ));
        tshirtCollection.add(new Tshirt("Men" , "L", 1500, "JACK & JONES"));
        tshirtCollection.add(new Tshirt("Men", "S", 800, "Levi's"));
        tshirtCollection.add(new Tshirt("Men", "L", 1000, "JACK & JONES"));
        tshirtCollection.add(new Tshirt("Women", "XXL", 2500, "Levi's"));
        tshirtCollection.add(new Tshirt("Women", "M", 2200, "JACK & JONES"));
        tshirtCollection.add(new Tshirt("Men" , "L", 1600, "Levi's"));

        shirtCollection.add(new Shirt("Men", "M", 1600,"MAX"));
        shirtCollection.add(new Shirt("Men", "L", 1000, "JACK & JONES" ));
        shirtCollection.add(new Shirt("Women", "XXL", 3500, "JACK & JONES"));
        shirtCollection.add(new Shirt("Women", "M", 800,"MAX" ));
        shirtCollection.add(new Shirt("Men" , "L", 1500, "JACK & JONES"));
        shirtCollection.add(new Shirt("Men", "M", 1600, "JACK & JONES"));
        shirtCollection.add(new Shirt("Men", "L", 7000,"MAX" ));
        shirtCollection.add(new Shirt("Women", "XXL", 2300, "Levi's"));
        shirtCollection.add(new Shirt("Women", "M", 1800, "Levi's" ));
        shirtCollection.add(new Shirt("Men" , "L", 5000, "JACK & JONES"));

        pantsCollection.add(new Pants("Men", "M", 1600, "JACK & JONES"));
        pantsCollection.add(new Pants("Men", "L", 1200,"MAX" ));
        pantsCollection.add(new Pants("Women", "XXL", 2500, "JACK & JONES"));
        pantsCollection.add(new Pants("Women", "M", 5800, "Levi's" ));
        pantsCollection.add(new Pants("Men" , "L", 1500,"MAX"));
        pantsCollection.add(new Pants("Men", "M", 2600, "JACK & JONES"));
        pantsCollection.add(new Pants("Men", "L", 4000, "Levi's" ));
        pantsCollection.add(new Pants("Women", "XXL", 3500,"MAX"));
        pantsCollection.add(new Pants("Women", "M", 800, "MAX" ));
        pantsCollection.add(new Pants("Women" , "L", 1500, "JACK & JONES"));
    }
}
