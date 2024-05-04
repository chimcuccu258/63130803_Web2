package org.ngavm1.deliverysystem.controller;

import lombok.RequiredArgsConstructor;
import org.ngavm1.deliverysystem.model.Customer;
import org.ngavm1.deliverysystem.repository.CustomerRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final CustomerRepository customerRepository;

    @PostMapping("/getCustomer")
    public Customer getCustomerByUsername(String username) {
        return customerRepository.getUserByUsername(username);
    }
}
