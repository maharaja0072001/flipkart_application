package com.flipkart.view;

import com.flipkart.InputHandler;
import com.flipkart.controller.CartController;
import com.flipkart.model.Cart;
import com.flipkart.model.User;
import com.flipkart.model.product.Product;

import java.util.List;
import java.util.Scanner;

/**
 * <p>
 * Responsible for viewing the user cart and placing order.
 * </p>
 *
 * @author Maharaja S
 * @version 1.0
 */
public class CartView {

    private static CartView cartViewInstance;

    private static final CartController CART_CONTROLLER = CartController.getInstance();
    private static final OrderView ORDER_VIEW = OrderView.getInstance();
    private final Scanner scanner = InputHandler.getScanner();

    /**
     * <p>
     * Default constructor of CartView class. Kept private to restrict from creating object outside this class.
     * </p>
     */
    private CartView() {}

    /**
     * <p>
     * Creates a single object of CartView class and returns it.
     * </p>
     *
     * @return the single instance of CartView class.
     */
    public static synchronized CartView getInstance() {
        if (null == cartViewInstance) {
            cartViewInstance = new CartView();
        }

        return cartViewInstance;
    }

    /**
     * <p>
     * Adds the specific product to the cart.
     * </p>
     *
     * @param user Refers the current {@link User}
     * @param product Refers the {@link Product} to be added to the cart
     * @return true if the product is added to the cart and false otherwise
     */
    public boolean addToCart(final Product product, final User user) {
        return CART_CONTROLLER.addToCart(product, user);
    }

    /**
     * <p>
     * Shows the items presented in the cart and user can place the order of the items present in the cart.
     * </p>
     *
     * @param user Refers the current {@link User}
     */
    public void viewCart(final User user) {
        final Cart cart = CART_CONTROLLER.getUserCart(user);

        if (null == cart || null == cart.getCartItems()) {
            System.out.println("Cart is empty");
            return;
        }
        final List<Product> cartItems = cart.getCartItems();
        final float totalAmountInCart = cart.getTotalAmount();
        System.out.println("Cart :");

        for (int i = 0; i < cartItems.size(); i++) {
            System.out.println(String.format("[%d : %s]", i+1, cartItems.get(i)));
        }
        System.out.println(String.format("Total amount : Rs-%.2f", totalAmountInCart));
        final int productId = getProductIdFromUser();

        if (!(productId > cartItems.size() || productId <= 0)) {
            final Product item = cartItems.get(productId-1);

            System.out.println("Enter '1' to place order or '2' to remove from cart");
            String choice = scanner.nextLine().trim();

            if ("1".equals(choice)) {
                ORDER_VIEW.placeOrder(item, user);
            } else if ("2".equals(choice)) {
                CART_CONTROLLER.removeItemFromCart(item, user);
                System.out.println("Item removed");
            }
        } else {
            System.out.println("Invalid product id");
        }
    }

    /**
     * <p>
     * Gets the product id from the user to add the product to cart.
     * </p>
     *
     * @return the product id
     */
    private int getProductIdFromUser() {
        int productId;
        final Scanner scanner = InputHandler.getScanner();

        while (true) {
            try {
                System.out.println("Enter the product id to order the item or remove from cart: ");
                productId = Integer.parseInt(scanner.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input.Enter a number.");
            }
        }

        return productId;
    }
}

