package com.flipkart.service;

import com.flipkart.model.User;
/**
 * <p>
 * Provides service for the User.
 * </p>
 *
 * @author Maharaja S
 * @version 1.0
 */
public interface UserService {

    /**
     * <p>
     * Checks if the user already exists. if not then creates a new user.
     * </p>
     *
     * @return true if the user created or false if user already exists.
     * @param user Refers {@link User} the user to be created.
     */
    boolean createNewUser(final User user);

    /**
     * <p>
     * Gets the existing user by the given credentials.
     * </p>
     *
     * @return User if the credentials are correct and user exists or null otherwise.
     * @param mobileNumber Refers the mobile number of the user
     * @param password Refers the password of the user.
     */
    User getExistingUser(final String mobileNumber, final String password);

}
