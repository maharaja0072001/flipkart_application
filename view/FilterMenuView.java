package com.flipkart.view;

import com.flipkart.InputHandler;
import com.flipkart.model.User;
import com.flipkart.model.product.Product;
import com.flipkart.view.datavalidation.UserDataValidator;
import com.flipkart.view.filter.PriceFilter;
import com.flipkart.view.filter.RateHighToLowFilter;
import com.flipkart.view.filter.RateLowToHighFilter;

import java.util.List;
import java.util.Scanner;

/**
 * <p>
 * Responsible for showing the filter menu which is useful to get filtered items.
 * </p>
 *
 * @author Maharaja S
 * @version 1.0
 */
public class FilterMenuView {

    private static final Scanner SCANNER = InputHandler.getScanner();
    private static final CartView CART_VIEW = CartView.getInstance();
    private static final WishlistView WISHLIST_VIEW = WishlistView.getInstance();
    private static final UserDataValidator USER_DATA_VALIDATOR = UserDataValidator.getInstance();
    private static FilterMenuView filterMenuViewInstance;

    /**
     * <p>
     * Default constructor of FilterMenuView class. Kept private to restrict from creating object outside this class.
     * </p>
     */
    private FilterMenuView() {}

    /**
     * <p>
     * Creates a single object of FilterMenuView class and returns it.
     * </p>
     *
     * @return the single instance of FilterMenuView class.
     */
    public static synchronized FilterMenuView getInstance() {
        if (null == filterMenuViewInstance) {
            filterMenuViewInstance = new FilterMenuView();
        }

        return filterMenuViewInstance;
    }

    /**
     * <p>
     * Shows the filter menu to the user to filter the items presented in the inventory.
     * </p>
     *
     * @param user Refers the current {@link User}.
     * @param inventoryItems Refers all the {@link Product} in the inventory.
     */
    public void showFilterMenu(final User user, final List<Product> inventoryItems) {
        boolean exit = false;
        int input;
        Product selectedItem;

        outerLoop:
        while (!exit) {
            try {
                System.out.println("Filter By:\n1.Rate Low to High\n2.Rate High to Low\n3.Price\n4.Back");
                input = Integer.parseInt(SCANNER.nextLine().trim());

                switch (input) {
                    case 1:
                        final List<Product> itemsFilteredByLowToHigh = RateLowToHighFilter.getInstance().getFilteredItems(inventoryItems);
                        showItems(itemsFilteredByLowToHigh);

                        while (true) {
                            selectedItem = getSelectedItem(itemsFilteredByLowToHigh);

                            if (null == selectedItem) {
                               continue outerLoop;
                            }
                            addItemToCartOrWishlist(selectedItem, user);
                        }
                    case 2:
                        final List<Product> itemsFilteredByHighToLow = RateHighToLowFilter.getInstance().getFilteredItems(inventoryItems);
                        showItems(itemsFilteredByHighToLow);

                        while (true) {
                            selectedItem = getSelectedItem(itemsFilteredByHighToLow);

                            if (null == selectedItem) {
                                continue outerLoop;
                            }
                            addItemToCartOrWishlist(selectedItem, user);
                        }
                    case 3:
                        final List<Product> itemsFilteredByPrice = PriceFilter.getInstance().getFilteredItems(inventoryItems);
                        showItems(itemsFilteredByPrice);

                        while (true) {
                            selectedItem = getSelectedItem(itemsFilteredByPrice);

                            if (null == selectedItem) {
                                continue outerLoop;
                            }
                            this.addItemToCartOrWishlist(selectedItem, user);
                        }
                    case 4:
                        exit = true;
                        break;
                    default:
                        System.out.println("Enter a valid choice ");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input is invalid. Enter a number");
            }
        }
    }

    /**
     * <p>
     * Gets the choice from the user to add the product to the cart or wishlist.
     * </p>
     *
     * @param product Refers the {@link Product} to be added to cart or wishlist
     * @param user Refers the current {@link User}.
     */
    public void addItemToCartOrWishlist(final Product product, final User user) {
        int choice;

        System.out.println("Enter '1' to add to cart or '2' to add to wishlist. Press any key to go back");
        try {
            choice = Integer.parseInt(SCANNER.nextLine().trim());
        } catch (NumberFormatException exception) {
            return;
        }

        switch (choice) {
            case 1:
                if (CART_VIEW.addToCart(product, user)) {
                    System.out.println("Item added to cart");
                } else {
                    System.out.println("Item not added");
                };
                break;
            case 2:
                if (WISHLIST_VIEW.addToWishlist(product, user)) {
                    System.out.println("Item added to wishlist");
                } else {
                    System.out.println("Item not added");
                };
                break;
            default:
                break;
        }
    }

    /**
     * <p>
     * Gets the specific item from the inventory which was selected by the user and return it.
     * </p>
     *
     * @return the {@link Product} selected by the user.
     * @param products Refers all the {@link Product} in the inventory.
     */
    public Product getSelectedItem(final List<Product> products) {
        int index;
        final Scanner scanner = InputHandler.getScanner();

        if (null == products || products.isEmpty()) {
            return null;
        }

        while (true) {
            try {
                System.out.println("Enter the product id : [Press '$' to go back]");
                String input = scanner.nextLine().trim();

                if (USER_DATA_VALIDATOR.containsToNavigateBack(input)) {
                    return null;
                } else {
                    index = Integer.parseInt(input);

                    if (index > products.size() || index <= 0) {
                        System.out.println("Enter a valid product id");
                        continue;
                    }
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a number.");
            }
        }

        return products.get(--index);
    }

    /**
     * <p>
     * Shows the list of products to the user.
     * </p>
     *
     * @param products Refers all the {@link Product} in the inventory.
     */
    public void showItems(final List<Product> products) {
        for (int i = 0; i < products.size(); i++) {
            System.out.println(String.format("[%d : %s]", i+1, products.get(i)));
        }
    }
}