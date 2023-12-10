package com.flipkart.service.impl2;

import com.flipkart.dao.Impl.WishlistDAOImpl;
import com.flipkart.product.Product;
import com.flipkart.service.WishlistService;

import java.util.List;

public class WishlistServiceImpl  {
    private WishlistDAOImpl wishlistDAO = WishlistDAOImpl.getInstance();
    public void addItemToWishlist(final Product item) {

    }

    public void removeItemFromWishlist(final Product item) {

    }

    public List<Product> getUserWishlist() {
        return null;
    }
}
