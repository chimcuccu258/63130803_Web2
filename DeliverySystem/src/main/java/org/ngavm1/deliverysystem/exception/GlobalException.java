package org.ngavm1.deliverysystem.exception;

import org.ngavm1.deliverysystem.payload.response.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(CustomerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseModel> handleSupplierException(SupplierException e) {
        ResponseModel responseModel = new ResponseModel("A error occur with supplier exception: " + e.getMessage(),
                null, HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.badRequest().body(responseModel);
    }

    @ExceptionHandler(EmployeeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseModel> handleEmployeeException(EmployeeException e) {
        ResponseModel responseModel = new ResponseModel("A error occur with employee exception: " + e.getMessage(),
                null, HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.badRequest().body(responseModel);
    }

    @ExceptionHandler(OrderException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseModel> handleOrderException(OrderException e) {
        ResponseModel responseModel = new ResponseModel("A error occur with order exception: " + e.getMessage(),
                null, HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.badRequest().body(responseModel);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ResponseModel> handleException(Exception e) {
        ResponseModel responseModel = new ResponseModel("A error occur with exception: " + e.getMessage(),
                null, HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResponseEntity.badRequest().body(responseModel);
    }
}