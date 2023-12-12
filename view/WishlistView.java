package com.flipkart.view;

import com.flipkart.InputHandler;
import com.flipkart.controller.WishlistController;
import com.flipkart.model.User;
import com.flipkart.model.Wishlist;
import com.flipkart.model.product.Product;

import java.util.List;
import java.util.Scanner;

/**
 * <p>
 * Responsible for viewing wishlist of the user and adding the item to the cart
 * </p>
 *
 * @author Maharaja S
 * @version 1.0
 */
public class WishlistView{

    private static final WishlistController WISHLIST_CONTROLLER = WishlistController.getInstance();
    private static final CartView CART_VIEW = CartView.getInstance();
    private static WishlistView wishlistViewInstance;
    private final Scanner scanner = InputHandler.getScanner();

    /**
     * <p>
     * Default constructor of WishlistView class. Kept private to restrict from creating object outside this class.
     * </p>
     */
    private WishlistView() {}

    /**
     * <p>
     * Creates a single object of WishlistView class and returns it.
     * </p>
     *
     * @return the single instance of WishlistView class.
     */
    public static synchronized WishlistView getInstance() {
        if (null == wishlistViewInstance) {
            wishlistViewInstance= new WishlistView();
        }

        return wishlistViewInstance;
    }

    /**
     * <p>
     * Shows the wishlist items to the user and the user can add the items to the cart
     * </p>
     *
     * @param user Refers {@link User} to show the wishlist of that user.
     */
    public void viewWishlist(final User user) {
        final Wishlist wishlist = WISHLIST_CONTROLLER.getUserWishlist(user);

        if (null == wishlist || null == wishlist.getWishlistItems()) {
            System.out.println("Wishlist is empty");
            return;
        }
        final List<Product> wishlistItems = wishlist.getWishlistItems();

        System.out.println("Wishlist :");

        for (int i = 0; i < wishlistItems.size(); i++) {
            System.out.println(String.format("[%d : %s]", i+1, wishlistItems.get(i)));
        }
        final int productId = getProductIdFromUser();

        System.out.println("Enter '1' to add to cart and '2' to remove an item");
        String choice = scanner.nextLine().trim();

        if (!(productId > wishlistItems.size() || productId <=0)) {
                final Product product = wishlistItems.get(productId-1);

                if ("1".equals(choice)) {
                     if (CART_VIEW.addToCart(product, user)) {
                         System.out.println("Item added to cart");
                     }
                } else if ("2".equals(choice)) {
                    WISHLIST_CONTROLLER.removeItemFromCart(product, user);
                    System.out.println("Item removed");
                }
        } else {
            System.out.println("Invalid product id");
        }
    }

    /**
     * <p>
     * Adds the specific product to the wishlist
     * </p>
     *
     * @param product Refers the product to be added
     * @param user Refers {@link User} who owns the wishlist.
     */
    public boolean addToWishlist(final Product product, final User user) {
        return WISHLIST_CONTROLLER.addToWishlist(product, user);
    }

    /**
     * <p>
     * Gets the product id from the user to add to the wishlist.
     * </p>
     *
     * @return the product id
     */
    private int getProductIdFromUser() {
        int productId;
        final Scanner scanner = InputHandler.getScanner();

        while (true) {
            try {
                System.out.println("Enter the productId to add to cart or remove from wishlist:");
                productId = Integer.parseInt(scanner.nextLine().trim());
                break;
            } catch (NumberFormatException exception) {
                System.out.println("Invalid input. Enter a number.");
            }
        }

        return productId;
    }
}



