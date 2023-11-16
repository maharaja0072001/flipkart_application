package com.flipkart.service;

import com.flipkart.model.User;

public interface UserService {

    User getUser(final String mobileNumber);
    boolean createNewUser(final User user);

}
