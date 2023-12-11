package com.flipkart.view;

import com.flipkart.custom_exceptions.DatabaseConnectionException;
import com.flipkart.dao.DBConnection;
import com.flipkart.InputHandler;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * <p>
 * Responsible for handling user creation and authentication.
 * </p>
 *
 * @author Maharaja S
 * @version 1.0
 */
public class AuthenticationView {

    private final static Scanner SCANNER = InputHandler.getScanner();
    private final static UserView USER_VIEW = UserView.getInstance();
    private static AuthenticationView authenticationViewInstance;

    /**
     * <p>
     * Default constructor of AuthenticationView class. Kept private to restrict from creating object outside this class.
     * </p>
     */
    private AuthenticationView() {}

    /**
     * <p>
     * Creates a single object of AuthenticationView class and returns it.
     * </p>
     *
     * @return the single instance of AuthenticationView class.
     */
    public static synchronized AuthenticationView getInstance() {
        if (null == authenticationViewInstance) {
            authenticationViewInstance = new AuthenticationView();
        }

        return authenticationViewInstance;
    }

    /**
     * <p>
     * Shows the authentication page for user login and signup and responsible for creating new user and authenticating the existing user.
     * </p>
     */
    public void showAuthenticationPage() {
        try {
            System.out.println("1.Signup\n2.Login\n3.Exit");
            final int choice = Integer.parseInt(SCANNER.nextLine().trim());

            switch (choice) {
                case 1:
                   USER_VIEW.createNewUser();
                    break;
                case 2:
                    USER_VIEW.validateUser();
                    break;
                case 3:
                    SCANNER.close();
                    DBConnection.getConnection().close();
                    System.exit(0);
                    return;
                default:
                    System.out.println("Enter correct choice!");
            }
            showAuthenticationPage();
        } catch (NumberFormatException exception) {
            System.out.println("Input is invalid. Enter a valid number");
            showAuthenticationPage();
        } catch (SQLException exception) {
            throw new DatabaseConnectionException(exception.getMessage());
        }
    }
}