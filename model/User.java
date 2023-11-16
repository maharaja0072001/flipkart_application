package com.flipkart.model;

import com.flipkart.service.CartService;
import com.flipkart.service.service_impl.CartServiceImpl;
import com.flipkart.service.WishlistService;
import com.flipkart.service.service_impl.WishlistServiceImpl;
import java.util.Objects;

public class User {

    private String name;
    private String emailId;
    private String mobileNumber;
    private String password;
    private final CartService cartService;
    private final WishlistService wishlistService;

    public User() {
        this.cartService = new CartServiceImpl();
        this.wishlistService = new WishlistServiceImpl();
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setEmailId(final String emailId) {
        this.emailId = emailId;
    }

    public void setMobileNumber(final String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getPassword() {
        return password;
    }

    public CartService getCartService() {
        return cartService;
    }

    public WishlistService getWishlist(){
        return wishlistService;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }
        final User user = (User) obj;

        return emailId.equals(user.emailId)
                && mobileNumber.equals(user.mobileNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailId, mobileNumber);
    }
}