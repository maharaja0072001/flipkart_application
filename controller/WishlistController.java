package com.flipkart.controller;

import com.flipkart.model.User;
import com.flipkart.product.ProductImpl;
import java.util.List;

public class WishlistController {

    private static WishlistController wishlistControllerInstance;

    private WishlistController() {}

    public static synchronized WishlistController getInstance() {
        if (wishlistControllerInstance == null) {
            wishlistControllerInstance = new WishlistController();
        }
        return wishlistControllerInstance;
    }

    public List<ProductImpl> getUserWishlist(final User user) {
        return user.getWishlist().getUserWishlist();
    }

   public void removeItemFromWishlist(final ProductImpl item, final User user) {
        user.getWishlist().removeItemFromWishlist(item);
    }

    public void addItemToWishlist(final ProductImpl item, final User user) {
        user.getWishlist().addItemToWishlist(item);
    }

}
