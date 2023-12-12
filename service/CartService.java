package com.flipkart.service;

import com.flipkart.model.Cart;
import com.flipkart.model.User;
import com.flipkart.model.product.Product;

/**
 * <p>
 * Provides service for the Cart.
 * </p>
 *
 * @author Maharaja S
 * @version 1.0
 */
public interface CartService {

    /**
     * <p>
     * Gets the cart of the current user and returns it.
     * </p>
     *
     * @param user Refers the current {@link User}
     * @return the {@link Cart} of the user.
     */
    Cart getUserCart(final User user);

    /**
     * <p>
     * Adds the specific product to the cart.
     * </p>
     *
     * @param user Refers the current {@link User}
     * @param product Refers the {@link Product} to be added to the cart.
     * @return true if the product is added.
     */
    boolean addItemToCart(final Product product, final User user);

    /**
     * <p>
     * Removes the specific product from the cart.
     * </p>
     *
     * @param user Refers the current {@link User}
     * @param product Refers the {@link Product} to be removed from the cart.
     */
    void removeItemFromCart(final Product product, final User user);

}
