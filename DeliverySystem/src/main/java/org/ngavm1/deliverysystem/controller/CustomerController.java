package org.ngavm1.deliverysystem.controller;

import lombok.RequiredArgsConstructor;
import org.ngavm1.deliverysystem.exception.CustomerException;
import org.ngavm1.deliverysystem.model.Customer;
import org.ngavm1.deliverysystem.payload.response.ResponseModel;
import org.ngavm1.deliverysystem.repository.CustomerRepository;
import org.ngavm1.deliverysystem.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;


    @PostMapping("/find-all")
    public ResponseEntity<ResponseModel> findAllCustomer() throws CustomerException {
        return customerService.findAllCustomer();
    }



}
