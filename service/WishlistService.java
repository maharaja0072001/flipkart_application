package com.flipkart.service;

import com.flipkart.product.ProductImpl;
import java.util.List;

public interface WishlistService {

    void addItemToWishlist(final ProductImpl item);
    void removeItemFromWishlist(final ProductImpl item);
    List<ProductImpl> getUserWishlist();
}
