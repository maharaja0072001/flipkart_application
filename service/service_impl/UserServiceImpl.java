package com.flipkart.service.service_impl;

import com.flipkart.model.User;
import com.flipkart.service.UserService;
import java.util.HashMap;
import java.util.Map;

public class UserServiceImpl implements UserService {

    private static UserService userServiceInstance;
    private static final Map<String, User> USERS = new HashMap<>();

    private UserServiceImpl() {}

    public static UserService getInstance() {
        if (userServiceInstance == null) {
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

    public User getUser(final String mobileNumber) {
        return USERS.get(mobileNumber);
    }
}