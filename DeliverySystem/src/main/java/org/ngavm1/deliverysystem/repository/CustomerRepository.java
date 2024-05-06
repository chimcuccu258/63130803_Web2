package org.ngavm1.deliverysystem.repository;

import lombok.RequiredArgsConstructor;
import org.ngavm1.deliverysystem.exception.CustomerException;
import org.ngavm1.deliverysystem.mapper.CustomerMapper;
import org.ngavm1.deliverysystem.model.Customer;
import org.ngavm1.deliverysystem.payload.request.RequestCustomerSignup;
import org.ngavm1.deliverysystem.payload.request.RequestLogin;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomerRepository {
    private final CustomerMapper customerMapper;

    public List<Customer> findAllCustomer() throws CustomerException {
        return customerMapper.findAllCustomer();
    }

    public Customer findCustomerById(Long customerID) throws CustomerException {
        return customerMapper.findCustomerById(customerID);
    }

    public Customer findCustomerByEmail(String email) throws CustomerException {
        return customerMapper.findCustomerByEmail(email);
    }

    public int createAccount(RequestCustomerSignup requestCustomerSignup) throws CustomerException {
        return customerMapper.createAccount(requestCustomerSignup);
    }

    public boolean existsByEmail(String email) throws CustomerException {
        return customerMapper.existsByEmail(email).isPresent();
    }
}
