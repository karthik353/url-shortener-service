package com.url.util.exception;

public class HashException extends Exception {
    public HashException() {
        super();
    }

    public HashException(String message, String description) {
        super(String.format("%s: %s", message, description));
    }
}
