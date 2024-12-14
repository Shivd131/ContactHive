package com.contact_hive.contact_hive.helpers;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException() {
        super("Resource not found");
    }

}
