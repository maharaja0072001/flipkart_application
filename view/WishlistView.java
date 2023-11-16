package com.flipkart.view;

import com.flipkart.InputHandler;
import com.flipkart.controller.WishlistController;
import com.flipkart.model.User;
import com.flipkart.product.ProductImpl;
import java.util.List;
import java.util.Scanner;

public class WishlistView{

    private static final WishlistController WISHLIST_CONTROLLER = WishlistController.getInstance();
    private static WishlistView wishlistViewInstance;

    private WishlistView() {}

    public static synchronized WishlistView getInstance() {
            if (wishlistViewInstance == null) {
                wishlistViewInstance= new WishlistView();
            }
            return wishlistViewInstance;
    }

    public void viewWishlist(final User user) {
        final List<ProductImpl> wishlist = WISHLIST_CONTROLLER.getUserWishlist(user);

        if (wishlist.isEmpty()) {
            System.out.println("No items in your wishlist !!");
        } else {
            System.out.println("Wishlist :");
            this.showItems(wishlist);
        }
    }

    public void addItemToWishlist(final ProductImpl item, final User user) {
        WISHLIST_CONTROLLER.addItemToWishlist(item, user);
    }

    public void removeItemFromWishlist(final User user) {
        final List<ProductImpl> wishlistItems = WISHLIST_CONTROLLER.getUserWishlist(user);

        while (true) {

            if (!wishlistItems.isEmpty()) {
                this.viewWishlist(user);
                final int index = this.getIndexFromUser();

                if (!(index >= wishlistItems.size())) {
                    final ProductImpl item = wishlistItems.get(index);

                    WISHLIST_CONTROLLER.removeItemFromWishlist(item, user);
                    System.out.println("Item removed");
                    break;
                } else {
                    System.out.println("Enter a valid index");
                }
            } else {
                break;
            }
        }
    }

    private void showItems(final List<ProductImpl> items) {
        for (int i = 0; i < items.size(); i++) {
            System.out.println(String.format("[%d : %s]", i, items.get(i)));
        }
    }

    private int getIndexFromUser() {
        int index;
        final Scanner scanner = InputHandler.getScanner();

        while (true) {
            try {
                System.out.println("Enter the index : ");
                index = Integer.parseInt(scanner.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input.Enter a number.");
            }
        }
        return index;
    }
}



