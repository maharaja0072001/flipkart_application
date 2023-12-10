package com.flipkart.view;

import com.flipkart.InputHandler;
import com.flipkart.controller.InventoryController;
import com.flipkart.model.User;
import com.flipkart.product.Product;
import com.flipkart.InventoryManager;
import com.flipkart.view.datavalidation.UserDataValidator;

import java.util.List;
import java.util.Scanner;

/**
 * <p>
 * Responsible for viewing the homepage of the flipkart application to the user to shop products.
 * </p>
 *
 * @author Maharaja S
 * @version 1.0
 */
public class HomePageView {

    private static final Scanner SCANNER = InputHandler.getScanner();
    private static final CartView CART_VIEW = CartView.getInstance();
    private static final WishlistView WISHLIST_VIEW = WishlistView.getInstance();
    private static final OrderView ORDER_VIEW = OrderView.getInstance();
    private static final FilterMenuView FILTER_MENU_VIEW = FilterMenuView.getInstance();
    private static final UserView USER_VIEW = UserView.getInstance();
    private static final InventoryManager INVENTORY_MANAGER = InventoryManager.getInstance();
    private static final InventoryController INVENTORY_CONTROLLER = InventoryController.getInstance();
    private final List<Product> mobiles = INVENTORY_CONTROLLER.getMobileItems();
    private final List<Product> laptops = INVENTORY_CONTROLLER.getLaptopItems();
    private final List<Product> clothes = INVENTORY_CONTROLLER.getClothesItems();
    private static final UserDataValidator USER_DATA_VALIDATOR = UserDataValidator.getInstance();
    private static HomePageView homePageViewInstance ;

    static {
        INVENTORY_MANAGER.allAllItemsToInventory();
    }

    /**
     * <p>
     *     Default constructor of HomePageView class. Kept private to restrict from
     *     creating object outside this class.
     * </p>
     */
    private HomePageView() {}

    /**
     * <p>
     * Creates a single object of HomePageView class and returns it.
     * </p>
     *
     * @return the single instance of HomePageView class.
     */
    public static synchronized HomePageView getInstance() {
        if (null == homePageViewInstance) {
            homePageViewInstance = new HomePageView();
        }

        return homePageViewInstance;
    }

    /**
     * <p>
     * Shows the home page of the flipkart application to the user for shopping the products..
     * </p>
     */
    public void showHomePage(final User user) {
        while (true) {
            System.out.println("1.Mobiles\n2.Laptops\n3.Clothes\n4.Cart\n5.Wishlist\n6.My Orders\n7.Profile\n8.Logout");

            try {
                final int input = Integer.parseInt(SCANNER.nextLine().trim());

                switch (input) {
                    case 1:
                        addToCartOrWishlist(user, mobiles);
                        break;
                    case 2:
                        addToCartOrWishlist(user, laptops);
                        break;
                    case 3:
                        addToCartOrWishlist(user, clothes);
                        break;
                    case 4:
                        CART_VIEW.viewCart(user);
                        break;
                    case 5:
                        WISHLIST_VIEW.viewWishlist(user);
                        break;
                    case 6:
                        ORDER_VIEW.viewMyOrders(user);
                        break;
                    case 7:
                        USER_VIEW.viewAndEditProfile(user);
                        break;
                    case 8:
                        System.out.println("Logged out successfully");
                        return;
                    default:
                        System.out.println("Enter a valid choice");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input is invalid.Enter a number");
            }
        }
    }

    /**
     * <p>
     * Gets choice from the user to add to cart or wishlist or show filtered items to the user
     * </p>
     */
    private void addToCartOrWishlist(final User user, final List<Product> items) {
                int index;

        FILTER_MENU_VIEW.showItems(items);

        while (true) {
            System.out.println("Enter the product id to add to cart or wishlist or '#' to show filter menu or press '$' to go back");
            final String choice = SCANNER.nextLine().trim();

            if (USER_DATA_VALIDATOR.containsToFilterMenu(choice)) {
                FILTER_MENU_VIEW.showFilterMenu(user, items);
                return;
            }

            if (USER_DATA_VALIDATOR.containsToNavigateBack(choice)) {
                return;
            }

            try {
                index = Integer.parseInt(choice);
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid product id");
                continue;
            }

            if (index > items.size() || index <= 0) {
                System.out.println("Enter a valid product id");
                continue;
            }

            final Product selectedItem = items.get(--index);
            FILTER_MENU_VIEW.addItemToCartOrWishlist(selectedItem, user);
        }
    }
}
