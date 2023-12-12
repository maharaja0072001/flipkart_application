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

    /**
     * <p>
     * Default constructor of the UserServiceImpl class. kept private to restrict from creating object from outside of this class.
     * </p>
     */
    private UserServiceImpl() {}

    /**
     * <p>
     * Creates a single object of UserServiceImpl Class and returns it.
     * </p>
     *
     * @return returns the single instance of UserServiceImpl Class.
     */
    public static UserServiceImpl getInstance() {
        if (null == userServiceInstance) {
            userServiceInstance = new UserServiceImpl();
        }

        return userServiceInstance;
    }

    /**
     * <p>
     * Checks if the user already exists. if not then creates a new user.
     * </p>
     *
     * @return true if the user created or false if user already exists.
     * @param user Refers the {@link User}to be created.
     */
    public boolean createNewUser(final User user) {
        if (USERS.containsKey(user.getMobileNumber())) {
            return false;
        } else {
            USERS.put(user.getMobileNumber(), user);
            return true;
        }
    }

    /**
     * <p>
     * Gets the existing user by the given credentials.
     * </p>
     *
     * @param mobileNumber Refers the mobile number of the user
     * @param password Refers the password of the user.
     * @return {@link User} if the credentials are correct and the user exists or null otherwise.
     */
    public User getExistingUser(final String mobileNumber, final String password) {
        final User user = USERS.get(mobileNumber);

        if (null != user && user.getPassword().equals(password)) {
            return user;
        }

        return null;
    }

}