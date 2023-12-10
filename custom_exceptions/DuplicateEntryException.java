package com.flipkart.custom_exceptions;

public class DuplicateEntryException extends RuntimeException{

    public DuplicateEntryException(final String message) {
        super(message);
    }
}
