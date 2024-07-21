package com.ecommerce.project.ecommerce.services.exceptions;

public class ConfirmSaleException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ConfirmSaleException(String msg) {
        super(msg);
    }

}