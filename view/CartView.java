package com.flipkart.view;

import com.flipkart.InputHandler;
import com.flipkart.controller.CartController;
import com.flipkart.model.User;
import com.flipkart.product.ProductImpl;
import java.util.List;
import java.util.Scanner;

public class CartView {

    private static CartView cartViewInstance;

    private final static CartController CART_CONTROLLER = CartController.getInstance();

    private CartView() {}

    public static synchronized CartView getInstance() {
        if (cartViewInstance == null) {
            cartViewInstance = new CartView();
        }
        return cartViewInstance;
    }

    public void viewCart(final User user) {
        final List<ProductImpl> cartItems = CART_CONTROLLER.getUserCart(user);
        final int totalAmountInCart = CART_CONTROLLER.getTotalAmountInCart(user);

        if (cartItems.isEmpty()) {
            System.out.println("Cart is empty !!");
        } else {
            System.out.println("Cart :");
            this.showItems(cartItems);
            System.out.println(String.format("Total amount : Rs-%d", totalAmountInCart));
        }
    }

    public void addToCart(final ProductImpl item, final User user) {
        CART_CONTROLLER.addItemToCart(item, user) ;
    }

    public void removeItemFromCart(final User user) {
        final List<ProductImpl> cartItems = CART_CONTROLLER.getUserCart(user);

        while (true) {

            if (!cartItems.isEmpty()) {
                viewCart(user);
                final int index = this.getIndexFromUser();

                if (!(index >= cartItems.size())) {
                    final ProductImpl item = cartItems.get(index);

                    CART_CONTROLLER.removeItemFromCart(item, user);
                    System.out.println("Item removed !!");
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

