package com.flipkart.view;

import com.flipkart.InputHandler;
import com.flipkart.model.User;
import com.flipkart.product.ProductImpl;
import com.flipkart.service.InventoryService;
import com.flipkart.service.service_impl.InventoryServiceImpl;
import com.flipkart.view.filter.PriceFilter;
import com.flipkart.view.filter.RateHighToLowFilter;
import com.flipkart.view.filter.RateLowToHighFilter;
import java.util.List;
import java.util.Scanner;

public class FilterMenuView {

    private static final Scanner SCANNER = InputHandler.getScanner();
    private static final CartView CART_VIEW = CartView.getInstance();
    private static final WishlistView WISHLIST_VIEW = WishlistView.getInstance();
    private static final InventoryService INVENTORY = InventoryServiceImpl.getInstance();
    private static FilterMenuView filterMenuViewInstance;

    private FilterMenuView() {}

    public static synchronized FilterMenuView getFilterMenuViewInstance() {
        if (filterMenuViewInstance == null) {
            filterMenuViewInstance = new FilterMenuView();
        }
        return filterMenuViewInstance;
    }

    public void showFilter(final User user, final String productType) {
        boolean exit = false;
        int input;

        outerLoop:
        while (!exit) {
            try {
                ProductImpl selectedItem;

                System.out.println("Filter By:\n1.Rate Low to High\n2.Rate High to Low\n3.Price\n4.Back");
                input = Integer.parseInt(SCANNER.nextLine().trim());

                switch (input) {
                    case 1:
                        final List<ProductImpl> itemsFilteredByLowToHigh = RateLowToHighFilter.getInstance().getFilteredItems(INVENTORY.getInventoryItems(), productType);

                        while (true) {
                            selectedItem = getSelectedItem(itemsFilteredByLowToHigh);

                            if (selectedItem == null) {
                               continue outerLoop;
                            }
                            this.addItemToCartOrWishlist(selectedItem, user);
                        }
                    case 2:
                        final List<ProductImpl> itemsFilteredByHighToLow = RateHighToLowFilter.getInstance().getFilteredItems(INVENTORY.getInventoryItems(), productType);

                        while (true) {
                            selectedItem = getSelectedItem(itemsFilteredByHighToLow);

                            if (selectedItem == null) {
                                continue outerLoop;
                            }
                            this.addItemToCartOrWishlist(selectedItem, user);
                        }
                    case 3:
                        final List<ProductImpl> itemsFilteredByPrice = PriceFilter.getInstance().getFilteredItems(INVENTORY.getInventoryItems(), productType);

                        while (true) {
                            selectedItem = getSelectedItem(itemsFilteredByPrice);

                            if (selectedItem == null) {
                                continue outerLoop;
                            }
                            this.addItemToCartOrWishlist(selectedItem, user);
                        }
                    case 4:
                        exit = true;
                        break;
                    default:
                        System.out.println("Enter a valid choice! ");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input is invalid. Enter a number");
            }
        }
    }

    private void addItemToCartOrWishlist(final ProductImpl item, final User user) {
        System.out.println("Enter '1' to add to cart or '2' to add to wishlist");
        final int choice = Integer.parseInt(SCANNER.nextLine().trim());

        switch (choice) {
            case 1:
                CART_VIEW.addToCart(item, user);
                break;
            case 2:
                WISHLIST_VIEW.addItemToWishlist(item, user);
                break;
            default:
                System.out.println("Enter a valid choice !");
                break;
        }
    }

    private ProductImpl getSelectedItem(final List<ProductImpl> items) {
        int index;
        final Scanner scanner = InputHandler.getScanner();

        if (items == null || items.isEmpty()) {
            return null;
        }
        this.showItems(items);

        while (true) {
            try {
                System.out.println("Enter the index : [Press '%' to go back]");
                String input = scanner.nextLine().trim();

                if (input.equals("%")) {
                    return null;
                } else {
                    index = Integer.parseInt(input);

                    if (index >= items.size()) {
                        System.out.println("Enter a valid index");
                        continue;
                    }
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a number.");
            }
        }
        return items.get(index);
    }

    private void showItems(final List<ProductImpl> items) {
        for (int i = 0; i < items.size(); i++) {
            System.out.println(String.format("[%d : %s]", i, items.get(i)));
        }
    }
}