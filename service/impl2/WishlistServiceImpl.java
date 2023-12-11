package com.flipkart.service.impl2;

import com.flipkart.dao.Impl.WishlistDAOImpl;
import com.flipkart.model.User;
import com.flipkart.model.Wishlist;
import com.flipkart.model.product.Product;
import com.flipkart.service.WishlistService;

public class WishlistServiceImpl implements WishlistService {

    private static final WishlistDAOImpl WISHLIST_DAO = WishlistDAOImpl.getInstance();
    private static WishlistServiceImpl wishlistServiceInstance;

    private WishlistServiceImpl() {}

    public static synchronized WishlistServiceImpl getInstance() {
        if (null == wishlistServiceInstance) {
            wishlistServiceInstance = new WishlistServiceImpl();
        }

        return wishlistServiceInstance;
    }

    public boolean addToWishlist(final Product product, final User user) {
        return WISHLIST_DAO.addToWishlist(product, user);
    }

    public void removeFromWishlist(final Product product, final User user) {
         WISHLIST_DAO.removeFromWishlist(product, user);
    }

    public Wishlist getUserWishlist(final User user) {
        return WISHLIST_DAO.getUserWishlist(user);
    }
}
