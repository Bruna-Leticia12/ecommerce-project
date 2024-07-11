package com.ecommerce.project.ecommerce.services.exceptions;

public class NoSuchElementCustomException extends RuntimeException {

    public NoSuchElementCustomException(String message) {
        super(message);
    }

    public NoSuchElementCustomException(String message, Throwable cause) {
        super(message, cause);
    }
}
