package com.flipkart.view;

import com.flipkart.controller.UserController;
import com.flipkart.model.User;
import com.flipkart.InputHandler;
import com.flipkart.view.datavalidator.UserDataValidator;
import java.util.Scanner;
import java.util.regex.Pattern;

public class UserView {

    private static final Scanner SCANNER = InputHandler.getScanner();
    private static final UserController USER_CONTROLLER = UserController.getInstance();
    private static final HomePageView HOME_PAGE_VIEW = HomePageView.getHomePageViewInstance();
    private static final AuthenticationView AUTHENTICATION_VIEW = AuthenticationView.getAuthenticationViewInstance();
    private static final UserDataValidator USER_DATA_VALIDATOR = UserDataValidator.getUserDataValidatorInstance();
    private static UserView userViewInstance;

    private UserView() {}

    public static synchronized UserView getInstance() {
        if (userViewInstance == null) {
            userViewInstance = new UserView();
        }
        return userViewInstance;
    }

    public void createNewUser() {
        final User user = new User();

        this.setUserDetails(user);

        if (USER_CONTROLLER.createUser(user)) {
            System.out.println("User created successfully!!");
        } else {
            System.out.println("User already exists. Please login !!");
            this.authenticateUser();
        }
    }

    private void setUserDetails(final User user) {
        user.setName(this.getName());
        user.setMobileNumber(this.getMobileNumber());
        user.setEmailId(this.getEmailId());
        user.setPassword(this.getPassword());
    }

    public void authenticateUser() {
        final String mobileNumber = this.getMobileNumber();

        System.out.println("Enter the Password : [Enter '%' to go back]");
        final String password = SCANNER.nextLine();

        if (password.equals("%")) {
            AUTHENTICATION_VIEW.authenticateUser();
        }
        final User user = this.getUser(mobileNumber, password);

        if (user != null) {
            System.out.println("Login successful");
            HOME_PAGE_VIEW.showHomePage(user);
        }
    }

    private User getUser(final String mobileNumber, final String password) {
        final User user = USER_CONTROLLER.getUser(mobileNumber);

        if (user == null) {
            System.out.println("User not found");
            return null;
        }

        if (user.getPassword().equals(password)) {
            return user;
        } else {
            System.out.println("Entered credentials are wrong !!");
        }
        return null;
    }

    private String getName() {
        System.out.println("Enter your name : [Press '%' to go back]");
        final String name = SCANNER.nextLine().trim();

        if (name.equals("%")) {
            AUTHENTICATION_VIEW.authenticateUser();
        }

        if (USER_DATA_VALIDATOR.validateName(name)) {
            return name;
        } else {
            System.out.println("Entered Name is invalid");
        }
        return getName();
    }

    private String getPassword() {
        System.out.println(String.join(" ", "Create a password:[Password should contain",
                "an uppercase, a lowercase, a special character and a digit. Minimum length is 8]",
                "or Enter '%' to go back"));
        final String password = SCANNER.nextLine().trim();

        if (password.equals("%")) {
            AUTHENTICATION_VIEW.authenticateUser();
        }

        if (USER_DATA_VALIDATOR.validatePassword(password)) {
            return password;
        } else {
            System.out.println("Entered password is invalid");
        }
        return getPassword();
    }

    private String getEmailId() {
        System.out.println("Enter your email id:[Enter '%' to go back]");
        final String emailId = SCANNER.nextLine().trim();

        if (emailId.equals("%")) {
            AUTHENTICATION_VIEW.authenticateUser();
        }

        if (USER_DATA_VALIDATOR.validateEmail(emailId)) {
            return emailId;
        } else {
            System.out.println("Entered email id is invalid. Try again!");
        }
        return getEmailId();
    }

    private String getMobileNumber() {
        System.out.println("Choose your country code :[Enter '%' to go back]\n1.America\n2.Australia\n3.China\n4.Germany\n5.India");
        final String choice = SCANNER.nextLine().trim();

        if (choice.equals("%")) {
            AUTHENTICATION_VIEW.authenticateUser();
        }

        if (!Pattern.matches("^[1-5]$", choice)) {
            System.out.println("Invalid choice");
            return getMobileNumber();
        }
        System.out.println("Enter your mobile number without country code: [Press '%' to go back] ");
        final String mobileNumber = SCANNER.nextLine().trim();

        if (mobileNumber.equals("%")) {
            AUTHENTICATION_VIEW.authenticateUser();
        }

        if (USER_DATA_VALIDATOR.validateMobileNumber(mobileNumber, choice)) {
            return mobileNumber;
        } else {
            System.out.println("Entered mobile number is invalid. Try again!");
        }
        return getMobileNumber();
    }
}
