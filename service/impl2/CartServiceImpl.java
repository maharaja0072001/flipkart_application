package com.flipkart.service.impl2;

import com.flipkart.dao.Impl.CartDAOImpl;
import com.flipkart.model.Cart;
import com.flipkart.model.User;
import com.flipkart.model.product.Product;
import com.flipkart.service.CartService;

public class CartServiceImpl implements CartService {

    private static final CartDAOImpl CART_DAO = CartDAOImpl.getInstance();
    private static CartServiceImpl cartServiceInstance ;

    private CartServiceImpl() {}

    public static synchronized CartServiceImpl getInstance() {
        if (null == cartServiceInstance) {
            cartServiceInstance = new CartServiceImpl();
        }

        return cartServiceInstance;
    }

    public boolean addItemToCart(final Product product, final User user) {
        return CART_DAO.addItemToCart(product, user);
    }

    public void removeItemFromCart(final Product product, final User user) {
        CART_DAO.removeItemFromCart(product, user);
    }

    public Cart getUserCart(final User user) {
        return CART_DAO.getUserCart(user);
    }
}
