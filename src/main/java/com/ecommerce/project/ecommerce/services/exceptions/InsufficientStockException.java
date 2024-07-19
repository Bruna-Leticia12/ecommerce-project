package com.ecommerce.project.ecommerce.services.exceptions;

public class InsufficientStockException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public InsufficientStockException(String msg) {
        super(msg);
    }
}
