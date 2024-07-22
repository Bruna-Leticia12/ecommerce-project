package com.ecommerce.project.ecommerce.services.exceptions;

public class EmptySaleException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public EmptySaleException(String msg) {
        super(msg);
    }
}