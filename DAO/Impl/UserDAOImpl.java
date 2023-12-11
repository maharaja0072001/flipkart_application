package com.flipkart.dao.Impl;

import com.flipkart.custom_exceptions.DatabaseException;
import com.flipkart.dao.DBConnection;
import com.flipkart.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <p>
 * Responsible for all the user details in the database.
 * </p>
 *
 * @author Maharaja S
 * @version 1.0
 */
public class UserDAOImpl {

    private static UserDAOImpl userServiceInstance;

    private UserDAOImpl() {}

    public static UserDAOImpl getInstance() {
        if (null == userServiceInstance) {
            userServiceInstance = new UserDAOImpl();
        }

        return userServiceInstance;
    }

    public boolean createNewUser(final User user) {
        try (final PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement("insert into users(name, mobile_number, email, password) values(?, ?, ?, ?) returning id")) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getMobileNumber());
            preparedStatement.setString(3, user.getEmailId());
            preparedStatement.setString(4, user.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            final int userId = resultSet.getInt(1) ;

            user.setUserId(userId);
            return true;
        } catch (SQLException exception) {
            throw new DatabaseException(exception.getMessage());
        }
    }

    public User getExistingUser(final String mobileNumber, final String password) {
        try (final PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement("select id, name, mobile_number, email, password from users where mobile_number=? and password=?")) {

            preparedStatement.setString(1,mobileNumber);
            preparedStatement.setString(2,password);
            final ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                return null;
            }
            final User user = new User();

            user.setUserId(resultSet.getInt(1));
            user.setName(resultSet.getString(2));
            user.setMobileNumber(resultSet.getString(3));
            user.setEmailId(resultSet.getString(4));
            user.setPassword(resultSet.getString(5));

            return user;
        } catch (SQLException exception) {
            throw new DatabaseException(exception.getMessage());
        }
    }
}
