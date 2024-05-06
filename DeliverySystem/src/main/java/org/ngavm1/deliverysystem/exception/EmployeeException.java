package org.ngavm1.deliverysystem.exception;

public class EmployeeException extends Exception{
    public EmployeeException(String message) {
        super(message);
    }

    public EmployeeException(String message, Throwable cause) {
        super(message, cause);
    }
}
