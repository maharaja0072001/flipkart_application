package com.flipkart.service.impl2;

import com.flipkart.dao.Impl.CartDAOImpl;
import com.flipkart.model.Cart;
import com.flipkart.model.User;
import com.flipkart.product.Product;

public class CartServiceImpl {

    private final CartDAOImpl cartDAO = new CartDAOImpl();

    public void addItemToCart(final Product item) {

    }


    public void removeItemFromCart(Product item) {

    }
    public void setIdForCart(final int id) {
        cartDAO.setIdForCart(id);
    }


    public Cart getUserCart(final User user) {
        return cartDAO.getUserCart(user);
    }


    public float getTotalAmountInCart() {
        return 0;
    }
}
