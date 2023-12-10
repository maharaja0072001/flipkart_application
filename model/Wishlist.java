package com.flipkart.model;

import com.flipkart.product.Product;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 *     Represents a wishlist for the user to add the items to the wishlist and can move them to cart for ordering.
 * </p>
 *
 * @author Maharaja S
 * @version 1.0
 */
public class Wishlist {

    private static int uniqueId = 1;
    private int id;
    private User user;
    private List<Product> wishlistItems;

    /**
     * <p>
     *     Constructor of Wishlist class.
     * </p>
     *
     * @param user refers the user who owns the Wishlist.
     */
    public Wishlist(final User user) {
        this.user = user;
        id = uniqueId++;
        user.setWishlistId(id);
    }

    public boolean addItemToWishlist(final Product item) {
        if (null == wishlistItems) {
            wishlistItems =  new LinkedList<>();
        }
        return wishlistItems.add(item);
    }

    public void removeItemFromWishlist(final Product item) {
        wishlistItems.remove(item);
    }

    public List<Product> getWishlistItems() {
        return wishlistItems;
    }

    public void setId(final int id) {
        this.id = id;
    }
}
