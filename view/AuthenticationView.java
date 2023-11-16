package com.flipkart.view;

import com.flipkart.InputHandler;
import java.util.Scanner;

public class AuthenticationView {

    private final static Scanner SCANNER = InputHandler.getScanner();
    private final static UserView USER_VIEW = UserView.getInstance();
    private static AuthenticationView authenticationViewInstance;

    private AuthenticationView() {}

    public static synchronized AuthenticationView getAuthenticationViewInstance() {
        if (authenticationViewInstance == null) {
            authenticationViewInstance = new AuthenticationView();
        }
        return authenticationViewInstance;
    }

    public void authenticateUser() {
        while (true) {
            try {
                System.out.println("1.Signup\n2.Login\n3.Exit");
                final int input = Integer.parseInt(SCANNER.nextLine().trim());

                switch (input) {
                    case 1:
                        USER_VIEW.createNewUser();
                        break;
                    case 2:
                        USER_VIEW.authenticateUser();
                        break;
                    case 3:
                        SCANNER.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Enter correct choice ");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input is invalid. Enter a valid number");
            }
        }
    }
}