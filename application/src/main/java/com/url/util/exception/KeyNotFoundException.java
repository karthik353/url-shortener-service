package com.url.util.exception;

public class KeyNotFoundException extends Exception {
    public KeyNotFoundException(String key) {
        super(key);
    }

    public KeyNotFoundException(String message, String description) {
        super(String.format("%s: %s", message, description));
    }
}
