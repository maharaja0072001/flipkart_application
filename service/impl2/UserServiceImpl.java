package com.flipkart.service.impl2;

import com.flipkart.dao.Impl.UserDAOImpl;
import com.flipkart.service.UserService;
import com.flipkart.model.User;

public class UserServiceImpl implements UserService {

 private static final UserDAOImpl USER_DAO = UserDAOImpl.getInstance();

    private static UserServiceImpl userService;

    private UserServiceImpl() {}

    public static UserServiceImpl getInstance() {
        if (null == userService) {
            userService = new UserServiceImpl();
        }

        return userService;
    }

    @Override
    public boolean createNewUser(final User user) {
        return USER_DAO.createNewUser(user);
    }

    @Override
    public User getExistingUser(final String mobileNumber, final String password) {
        return USER_DAO.getExistingUser(mobileNumber, password);
    }
}
