package com.flipkart.controller;

import com.flipkart.model.User;
import com.flipkart.product.ProductImpl;
import java.util.List;

public class CartController {

    private static CartController cartControllerInstance;

    private CartController() {}

    public static synchronized CartController getInstance() {
        if (cartControllerInstance == null) {
            cartControllerInstance = new CartController();
        }
        return cartControllerInstance;
    }

    public List<ProductImpl> getUserCart(final User user) {
        return user.getCartService().getUserCart();
    }

    public int getTotalAmountInCart(final User user) {
        return user.getCartService().getTotalAmountInCart();
    }

    public void removeItemFromCart(final ProductImpl item, final User user) {
        user.getCartService().removeItemFromCart(item);
    }

    public void addItemToCart(final ProductImpl item, final User user) {
        user.getCartService().addItemToCart(item);
    }
}



