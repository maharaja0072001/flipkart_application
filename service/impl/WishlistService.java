package com.flipkart.service.impl;

import com.flipkart.controller.WishlistController;
import com.flipkart.model.Cart;
import com.flipkart.model.Wishlist;


import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Provides the service for the Wishlist.
 * </p>
 *
 * @author Maharaja S
 * @version 1.0
 */
public class WishlistService {

    private static WishlistService wishlistServiceInstance;
    private static final Map<Integer, Wishlist> WISHLISTS = new HashMap<>();

    private WishlistService() {}

    public static WishlistService getInstance() {
        if (null == wishlistServiceInstance) {
            wishlistServiceInstance = new WishlistService();
        }

        return wishlistServiceInstance;
    }

    public void addUserWishlist(final int userId, final Wishlist wishlist) {
        WISHLISTS.put(userId, wishlist);
    }

    public Wishlist getUserWishlist(final int userId) {
        return WISHLISTS.get(userId);
    }

}
