package com.flipkart.service.service_impl;

import com.flipkart.product.ProductImpl;
import com.flipkart.service.CartService;
import java.util.LinkedList;
import java.util.List;

public class CartServiceImpl implements CartService {

    private int totalAmountInCart;
    private final List<ProductImpl> cartItems =  new LinkedList<>();

    public void addItemToCart(final ProductImpl item) {
        cartItems.add(item);
        totalAmountInCart += item.getPrice();
    }

    public void removeItemFromCart(final ProductImpl item) {
            cartItems.remove(item);
            totalAmountInCart -= item.getPrice();
    }

    public List<ProductImpl> getUserCart() {
        return cartItems;
    }

    public int getTotalAmountInCart() {
        return totalAmountInCart;
    }
}
