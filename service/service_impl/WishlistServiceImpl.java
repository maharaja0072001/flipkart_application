package com.flipkart.service.service_impl;

import com.flipkart.product.ProductImpl;
import com.flipkart.service.WishlistService;

import java.util.LinkedList;
import java.util.List;

public class WishlistServiceImpl implements WishlistService {

    private final List<ProductImpl> wishlistItems =  new LinkedList<>();

    public void addItemToWishlist(final ProductImpl item) {
        wishlistItems.add(item);
    }

    public void removeItemFromWishlist(final ProductImpl item) {
        wishlistItems.remove(item);
    }

    public List<ProductImpl> getUserWishlist() {
        return wishlistItems;
    }
}
