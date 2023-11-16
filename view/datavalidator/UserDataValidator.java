package com.flipkart.view.datavalidator;

import java.util.regex.Pattern;

public class UserDataValidator {

    private static UserDataValidator userDataValidatorInstance;

    private UserDataValidator() {}

    public static UserDataValidator getUserDataValidatorInstance() {
        if (userDataValidatorInstance == null) {
            userDataValidatorInstance = new UserDataValidator();
        }
        return userDataValidatorInstance;
    }

    public boolean validateName(final String name) {
        return Pattern.matches("^[A-Za-z][A-Za-z\\s]{2,20}$", name);
    }

    public boolean validatePassword(final String password) {
        return Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", password);
    }

    public boolean validateEmail(final String emailId) {
        return Pattern.matches("^[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)*@[A-Za-z0-9]{2,}([.][a-zA-Z0-9]{2,})+$", emailId);
    }

    public boolean validateMobileNumber(final String mobileNumber, final String choice) {
        final String regexForAmerica = "^[2-9][\\d]{2}[2-9][\\d]{2}[\\d]{4}$";
        final String regexForAustralia = "^(4|04)\\d{8}$";
        final String regexForGermany = "^[1][5-79]\\d{9}$";
        final String regexForChina = "^[1][3-9][0-9]{9}$";
        final String regexForIndia = "^[6789]\\d{9}$";

        switch (choice) {
            case "1":
                return Pattern.matches(regexForAmerica, mobileNumber);
            case "2":
                return Pattern.matches(regexForAustralia, mobileNumber);
            case "3":
                return Pattern.matches(regexForChina, mobileNumber);
            case "4":
                return Pattern.matches(regexForGermany, mobileNumber);
            case "5":
                return Pattern.matches(regexForIndia, mobileNumber);
        }
        return false;
    }

}
