package com.flipkart;

import com.flipkart.view.AuthenticationView;

public class Main {

    public static void main(String[] args) {
        AuthenticationView authenticationViewInstance = AuthenticationView.getAuthenticationViewInstance();

        authenticationViewInstance.authenticateUser();  //authenticate the user
    }
}
