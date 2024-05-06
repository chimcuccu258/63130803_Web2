package org.ngavm1.deliverysystem.service;

import org.ngavm1.deliverysystem.exception.CustomerException;
import org.ngavm1.deliverysystem.payload.request.RequestCustomerSignup;
import org.ngavm1.deliverysystem.payload.response.ResponseModel;
import org.springframework.http.ResponseEntity;

public interface CustomerService {
    ResponseEntity<ResponseModel> findAllCustomer() throws CustomerException;
    ResponseEntity<ResponseModel> loginCustomer(String email, String password) throws CustomerException;
    ResponseEntity<ResponseModel> findCustomerById(Long customerID) throws CustomerException;
    ResponseEntity<ResponseModel> createAccount(RequestCustomerSignup requestCustomerSignup) throws CustomerException;
    boolean existsByEmail(String email) throws CustomerException;
}
