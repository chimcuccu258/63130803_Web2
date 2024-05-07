package org.ngavm1.deliverysystem.exception;

public class OrderDetailsException extends Exception {
    public OrderDetailsException(String message) {
        super(message);
    }

    public OrderDetailsException(String message, Throwable cause) {
        super(message, cause);
    }
}
