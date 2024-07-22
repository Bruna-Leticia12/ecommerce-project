package com.ecommerce.project.ecommerce.services.exceptions;

public class MethodNotAllowed extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public MethodNotAllowed(String msg) {
        super(msg);
    }

}

