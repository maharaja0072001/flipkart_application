package com.flipkart;

import com.flipkart.view.AuthenticationView;

/**
 * <p>
 * Responsible for launching the flipkart application and showing menu for user login and signup.
 * </p>
 *
 * @author Maharaja S
 * @version 1.0
 */
public class ApplicationLauncher {

    /**
     * <p>
     * The main entry to the flipkart application.
     * </p>
     *
     * @param args Refers command line arguments to flipkart application
     */
    public static void main(final String[] args) {
        final AuthenticationView authenticationView = AuthenticationView.getInstance();

        authenticationView.showAuthenticationPage();
    }
}
