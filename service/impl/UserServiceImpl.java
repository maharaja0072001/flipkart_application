package com.flipkart.service.impl;

import com.flipkart.model.User;
import com.flipkart.service.UserService;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Provides the service for the User.
 * </p>
 *
 * @author Maharaja S
 * @version 1.0
 */
public class UserServiceImpl implements UserService {

    private static UserServiceImpl userServiceInstance;
    private static final Map<String, User> USERS = new HashMap<>();

    private UserServiceImpl() {}

    public static UserServiceImpl getInstance() {
        if (null == userServiceInstance) {
            userServiceInstance = new UserServiceImpl();
        }

        return userServiceInstance;
    }

    public boolean createNewUser(final User user) {
        if (USERS.containsKey(user.getMobileNumber())) {
            return false;
        } else {
            USERS.put(user.getMobileNumber(), user);
            return true;
        }
    }

    public User getExistingUser(final String mobileNumber, final String password) {
        final User user = USERS.get(mobileNumber);

        if (null != user && user.getPassword().equals(password)) {
            return user;
        }

        return null;
    }

}