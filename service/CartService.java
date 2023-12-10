package com.flipkart.service;

import com.flipkart.model.Cart;
import com.flipkart.model.User;
import com.flipkart.product.Product;

/**
 * <p>
 * Provides service for the Cart.
 * </p>
 *
 * @author Maharaja S
 * @version 1.0
 */
public interface CartService {

    Cart getUserCart(final int userId);

    boolean addItemToCart(final Product product, final User user);

    void removeItemFromCart(final Product product, final User user);

}
