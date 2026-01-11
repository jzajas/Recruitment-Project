package com.jzajas.task.exception;

import org.springframework.http.HttpStatus;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(final String message) {
        super(message);
    }
}
