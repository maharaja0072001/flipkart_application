package com.flipkart.controller;

import com.flipkart.model.User;
import com.flipkart.service.impl2.UserServiceImpl;

/**
 * <p>
 * Interacts between UserView and UserService for creating new user and getting existing user for login.
 * </p>
 *
 * @author Maharaja S
 * @version 1.0
 */
public class UserController {

    private static UserController userControllerInstance;
//    private static final UserService USER_SERVICE = UserServiceImpl.getInstance();
    private static final UserServiceImpl USER_SERVICE = UserServiceImpl.getInstance();


    private UserController() {}

    public static synchronized UserController getInstance() {
        if (null == userControllerInstance) {
            userControllerInstance = new UserController();
        }

        return userControllerInstance;
    }

    public boolean createUser(final User user) {
        return USER_SERVICE.createNewUser(user);
    }

    public User getExistingUser(final String mobileNumber, final String password) {
        return USER_SERVICE.getExistingUser(mobileNumber, password);
    }
}
