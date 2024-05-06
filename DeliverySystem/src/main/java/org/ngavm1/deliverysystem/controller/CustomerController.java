package org.ngavm1.deliverysystem.controller;

import lombok.RequiredArgsConstructor;
import org.ngavm1.deliverysystem.exception.CustomerException;
import org.ngavm1.deliverysystem.model.Customer;
import org.ngavm1.deliverysystem.payload.request.RequestCustomerSignup;
import org.ngavm1.deliverysystem.payload.request.RequestLogin;
import org.ngavm1.deliverysystem.payload.response.ResponseModel;
import org.ngavm1.deliverysystem.repository.CustomerRepository;
import org.ngavm1.deliverysystem.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;


    @PostMapping("/find-all")
    public ResponseEntity<ResponseModel> findAllCustomer() throws CustomerException {
        return customerService.findAllCustomer();
    }

    @PostMapping("/find-by-id")
    public ResponseEntity<ResponseModel> findCustomerById(@RequestParam Long customerID) throws CustomerException {
        return customerService.findCustomerById(customerID);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseModel> loginCustomer(@RequestBody RequestLogin requestLogin) throws CustomerException {
        return customerService.loginCustomer(requestLogin.getEmail(), requestLogin.getPassword());
    }

    @PostMapping("/signup")
    public ResponseEntity<ResponseModel> createAccount(@RequestBody RequestCustomerSignup requestCustomerSignup) throws CustomerException {
        return customerService.createAccount(requestCustomerSignup);
    }

}
