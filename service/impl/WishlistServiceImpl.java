package com.flipkart.service.impl;

import com.flipkart.model.User;
import com.flipkart.model.Wishlist;
import com.flipkart.product.Product;
import com.flipkart.service.WishlistService;

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
public class WishlistServiceImpl implements WishlistService {

    private static WishlistServiceImpl wishlistServiceInstance;
    private static final Map<Integer, Wishlist> WISHLISTS = new HashMap<>();

    /**
     *<p>
     *Default constructor of the WishlistServiceImpl class. kept private to restrict from creating object from outside of this class.
     * </p>
     */
    private WishlistServiceImpl() {}

    /**
     * <p>
     * Creates a single object of WishlistServiceImpl Class and returns it.
     * </p>
     *
     * @return returns the single instance of WishlistServiceImpl Class.
     */
    public static WishlistServiceImpl getInstance() {
        if (null == wishlistServiceInstance) {
            wishlistServiceInstance = new WishlistServiceImpl();
        }

        return wishlistServiceInstance;
    }

    /**
     * <p>
     * Adds the product to the cart of the specified user.
     * * </p>
     *
     * @param product Refers the product to be added
     * @param user Refers the user
     */
    public boolean addToWishlist(final Product product, final User user) {
        if (!WISHLISTS.containsKey(user.getUserId())) {
            WISHLISTS.put(user.getUserId(), new Wishlist(user));
        }
        final Wishlist wishlist = WISHLISTS.get(user.getUserId());

         return wishlist.addItemToWishlist(product);
    }
    /**
     * <p>
     * Adds the product to the cart of the specified user.
     * * </p>
     *
     * @param product Refers the product to be added
     * @param user Refers the user
     */
    public void removeFromWishlist(final Product product, final User user) {
        final Wishlist wishlist = WISHLISTS.get(user.getUserId());

        wishlist.removeItemFromWishlist(product);
    }

    /**
     * <p>
     * Gets the wishlist of the specified user id and returns it.
     * </p>
     *
     * @param userId Refers the user id of the user.
     * @return the wishlist of the user.
     */
    public Wishlist getUserWishlist(final int userId) {
        return WISHLISTS.get(userId);
    }

}
