package com.ecommerce.project.ecommerce.controllers.exceptions;

import com.ecommerce.project.ecommerce.services.exceptions.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandarError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        String error = "Resource not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandarError err = new StandarError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandarError> database(DatabaseException e, HttpServletRequest request) {
        String error = "Database error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandarError err = new StandarError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(NoSuchElementCustomException.class)
    public ResponseEntity<StandarError> noSuchElement(NoSuchElementCustomException e, HttpServletRequest request) {
        String error = "No such element";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandarError err = new StandarError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(CanceledOrderException.class)
    public ResponseEntity<StandarError> canceledOrderException(CanceledOrderException e, HttpServletRequest request) {
        String error = "Sale not canceled";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandarError err = new StandarError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(ConfirmSaleException.class)
    public ResponseEntity<StandarError> confirmSaleException(ConfirmSaleException e, HttpServletRequest request) {
        String error = "Sale not confirmed";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandarError err = new StandarError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
