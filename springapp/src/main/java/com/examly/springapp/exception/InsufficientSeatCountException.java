package com.examly.springapp.exception;


    public class InsufficientSeatCountException extends Exception {
        public InsufficientSeatCountException(String message) {
            super(message);
        }
    }

