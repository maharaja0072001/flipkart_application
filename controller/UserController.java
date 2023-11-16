package com.flipkart.controller;

import com.flipkart.model.User;
import com.flipkart.service.UserService;
import com.flipkart.service.service_impl.UserServiceImpl;

public class UserController {

    private static UserController userControllerInstance;
    private static final UserService USER_SERVICE = UserServiceImpl.getInstance();

    private UserController() {}

    public static synchronized UserController getInstance() {
        if (userControllerInstance == null) {
            userControllerInstance = new UserController();
        }
        return userControllerInstance;
    }

    public boolean createUser(final User user) {
        return USER_SERVICE.createNewUser(user);
    }

    public User getUser(final String mobileNumber) {
        return USER_SERVICE.getUser(mobileNumber);
    }
}
