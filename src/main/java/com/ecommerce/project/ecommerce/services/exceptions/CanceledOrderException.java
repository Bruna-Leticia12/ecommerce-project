package com.ecommerce.project.ecommerce.services.exceptions;

public class CanceledOrderException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CanceledOrderException(Object id) {
        super("Sale is now canceled, id: " + id);
    }

}