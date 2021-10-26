package com.bosonit.springboot.bs8.exception;


public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}