package com.flipkart.service;

import com.flipkart.product.ProductImpl;
import java.util.List;

public interface CartService {

    void addItemToCart(final ProductImpl item);
    void removeItemFromCart(final ProductImpl item);
    List<ProductImpl> getUserCart();
    int getTotalAmountInCart();

}
