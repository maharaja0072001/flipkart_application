package com.flipkart.service;

import com.flipkart.model.User;
import com.flipkart.model.Wishlist;
import com.flipkart.model.product.Product;

/**
 * <p>
 * Provides service for the Wishlist.
 * </p>
 *
 * @author Maharaja S
 * @version 1.0
 */
public interface WishlistService {

    /**
     * <p>
     * Adds the specific product to the wishlist
     * </p>
     *
     * @param product Refers {@link Product} the product to be added.
     */
    boolean addToWishlist(final Product product, final User user);

    /**
     * <p>
     * Removes the specific product to the wishlist
     * </p>
     *
     * @param product Refers {@link Product} the product to be removed.
     */
    void removeFromWishlist(final Product product, final User user);

    /**
     * <p>
     * Gets all the items in the wishlist of a user.
     * </p>
     *
     * @return the items in the wishlist.
     */
    Wishlist getUserWishlist(final User user);
}
