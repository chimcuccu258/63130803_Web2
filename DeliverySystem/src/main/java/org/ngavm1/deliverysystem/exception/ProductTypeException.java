package org.ngavm1.deliverysystem.exception;

public class ProductTypeException extends Exception {
    public ProductTypeException(String message) {
        super(message);
    }

    public ProductTypeException(String message, Throwable cause) {
        super(message, cause);
    }
}
