package com.flipkart;

import java.util.Scanner;

public class InputHandler {

    private static Scanner scannerInstance;

    private InputHandler() {}

    public static synchronized Scanner getScanner() {
        if (scannerInstance == null) {
            scannerInstance = new Scanner(System.in);
        }
        return scannerInstance;
    }
}
