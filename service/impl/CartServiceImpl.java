package com.flipkart.service.impl;

import com.flipkart.model.Cart;
import com.flipkart.model.User;
import com.flipkart.product.Product;
import com.flipkart.service.CartService;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Provides the service for the Cart of the user.
 * </p>
 *
 * @author Maharaja S
 * @version 1.0
 */
public class CartServiceImpl implements CartService {

    private static CartServiceImpl cartServiceInstance;
    private static final Map<Integer, Cart> CARTS = new HashMap<>();

    /**
     *<p>
     *Default constructor of the CartServiceImpl class. kept private to restrict from creating object from outside of this class.
     * </p>
     */
    private CartServiceImpl() {}

    /**
     * <p>
     * Creates a single object of CartServiceImpl Class and returns it.
     * </p>
     *
     * @return returns the single instance of CartServiceImpl Class.
     */
    public static CartServiceImpl getInstance() {
        if (null == cartServiceInstance) {
            cartServiceInstance = new CartServiceImpl();
        }

        return cartServiceInstance;
    }

    /**
     * <p>
     * Adds the product to the cart of the specified user.
     * </p>
     *
     * @param product Refers the product to be added
     * @param user Refers the user
     */
    public boolean addItemToCart(final Product product, final User user) {
        if (!CARTS.containsKey(user.getUserId())) {
             CARTS.put(user.getUserId(), new Cart(user));
        }
        final Cart cart = CARTS.get(user.getUserId());

        return cart.addToCart(product);
    }

    /**
     * <p>
     * Removes the product to the cart of the specified user.
     * * </p>
     *
     * @param product Refers the product to be added
     * @param user Refers the user
     */
    public void removeItemFromCart(final Product product, final User user) {
        final Cart cart = CARTS.get(user.getUserId());

        cart.removeFromCart(product);
    }

    /**
     * <p>
     * Gets the cart of the specified user id and returns it.
     * </p>
     *
     * @param userId Refers the user id of the user.
     * @return the cart of the user.
     */
    public Cart getUserCart(final int userId) {
        return CARTS.get(userId);
    }
}
